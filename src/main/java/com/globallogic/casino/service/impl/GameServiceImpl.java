package com.globallogic.casino.service.impl;

import com.globallogic.casino.exception.EntityNotFoundException;
import com.globallogic.casino.model.dto.GameDto;
import com.globallogic.casino.model.dto.ItemDto;
import com.globallogic.casino.model.entity.h2.Customer;
import com.globallogic.casino.model.entity.h2.Game;
import com.globallogic.casino.model.strategy.*;
import com.globallogic.casino.repository.CustomerRepository;
import com.globallogic.casino.repository.GameRepository;
import com.globallogic.casino.service.GameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
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
    public String simulateGame(Long gameId, long playerBet) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new EntityNotFoundException(Game.class, gameId));

        GameOperationsStrategy currentStrategy = getStrategyByGameType(game);
        Game gamePlayed = currentStrategy.simulateGame(playerBet);
        customerRepository.saveAll(gamePlayed.getPlayers());
        gameRepository.save(gamePlayed);
        return gamePlayed.getGameResultMessage();
    }

    @Override
    public List<ItemDto> checkGameItems(Long gameId) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new EntityNotFoundException(Game.class, gameId));
        GameOperationsStrategy currentStrategy = getStrategyByGameType(game);

        if (!currentStrategy.checkItems()) {
            log.warn("Check the items in game with id: "
                    + gameId + ". Either at least one is missing or is in poor condition" +
                    " and needs replacing.");
        }
        log.info("All items for game with id: " + gameId + " checked.");

        return game.getNecessaryItems().stream()
                .map(item -> modelMapper.map(item, ItemDto.class))
                .collect(Collectors.toList());
    }

//    private void updateGameParticipantsData(List<Customer> players) {
//        customerRepository.saveAll(players);
//    }

    private GameOperationsStrategy getStrategyByGameType(Game game) {
        return switch (game.getGameType()) {
            case POKER -> new PokerOperationStrategy(game);
            case SLOT_MACHINE -> new SlotMachineOperationStrategy(game);
            case BLACKJACK -> new BlackJackOperationStrategy(game);
            case ROULETTE -> new RouletteOperationStrategy(game);
        };
    }
}
