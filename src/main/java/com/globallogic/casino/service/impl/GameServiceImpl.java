package com.globallogic.casino.service.impl;

import com.globallogic.casino.exception.EntityNotFoundException;
import com.globallogic.casino.model.dto.h2.GameDto;
import com.globallogic.casino.model.dto.h2.ItemDto;
import com.globallogic.casino.model.dto.h2.ItemsCheckResponseDto;
import com.globallogic.casino.model.entity.h2.Game;
import com.globallogic.casino.model.strategy.*;
import com.globallogic.casino.repository.CustomerRepository;
import com.globallogic.casino.repository.GameRepository;
import com.globallogic.casino.service.GameService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    @Override
    public GameDto createGame(Game game) {
        Game newGame = gameRepository.save(game.createGame());
        return modelMapper.map(newGame, GameDto.class);
    }

    @Override
    public String simulateGame(Long gameId, BigDecimal playerBet) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new EntityNotFoundException(Game.class, gameId));

        GameOperationsStrategy currentStrategy = getStrategyByGameType(game);
        Game gamePlayed = currentStrategy.simulateGame(playerBet);
        customerRepository.saveAll(gamePlayed.getPlayers());
        gameRepository.save(gamePlayed);
        return gamePlayed.getGameResultMessage();
    }

    @Override
    public ItemsCheckResponseDto checkGameItems(Long gameId) {
        final String itemsCheckWrongMsg = "Check the items in game with id: %d. " +
                "Either at least one is missing or is in poor condition and needs replacing.";
        final String itemsCheckOkMsg = "All necessary items for game with id: %d checked." +
                " All are present and in sufficient condition.";

        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new EntityNotFoundException(Game.class, gameId));
        GameOperationsStrategy currentStrategy = getStrategyByGameType(game);

        return ItemsCheckResponseDto.builder()
                .message(currentStrategy.checkItems()
                        ? String.format(itemsCheckOkMsg, gameId)
                        : String.format(itemsCheckWrongMsg, gameId))
                .items(game.getNecessaryItems().stream()
                        .map(item -> modelMapper.map(item, ItemDto.class))
                        .collect(Collectors.toList()))
                .build();
    }

    private GameOperationsStrategy getStrategyByGameType(Game game) {
        return switch (game.getGameType()) {
            case POKER -> new PokerOperationStrategy(game);
            case SLOT_MACHINE -> new SlotMachineOperationStrategy(game);
            case BLACKJACK -> new BlackJackOperationStrategy(game);
            case ROULETTE -> new RouletteOperationStrategy(game);
        };
    }
}
