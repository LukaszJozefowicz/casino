package com.globallogic.casino.service.impl;

import com.globallogic.casino.exception.CannotStartGameException;
import com.globallogic.casino.exception.EntityNotFoundException;
import com.globallogic.casino.model.dto.CustomerDto;
import com.globallogic.casino.model.dto.GameDto;
import com.globallogic.casino.model.dto.ItemDto;
import com.globallogic.casino.model.entity.Customer;
import com.globallogic.casino.model.entity.Game;
import com.globallogic.casino.model.entity.Item;
import com.globallogic.casino.model.enums.GameType;
import com.globallogic.casino.repository.CustomerRepository;
import com.globallogic.casino.repository.GameRepository;
import com.globallogic.casino.repository.ItemRepository;
import com.globallogic.casino.service.GameService;
import com.globallogic.casino.service.strategy.*;
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
    public void simulateGame(Long gameId) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new EntityNotFoundException(Game.class, gameId));

        GameOperationsStrategy currentStrategy = getStrategyByGameType(game.getGameType(), game.getNecessaryItems(), game.getPlayers());
        List<Customer> gameParticipants = currentStrategy.simulateGame();
        updateGameParticipantsData(gameParticipants);
    }

    @Override
    public List<ItemDto> checkItems(Long gameId) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new EntityNotFoundException(Game.class, gameId));
        GameOperationsStrategy currentStrategy = getStrategyByGameType(game.getGameType(), game.getNecessaryItems(), game.getPlayers());

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

    private void updateGameParticipantsData(List<Customer> players) {
        customerRepository.saveAll(players);
    }

    private GameOperationsStrategy getStrategyByGameType(GameType gameType, List<Item> necessaryItems, List<Customer> players) {
        return switch (gameType) {
            case POKER -> new PokerOperationStrategy(necessaryItems, players);
            case SLOT_MACHINE -> new SlotMachineOperationStrategy(necessaryItems, players);
            case BLACKJACK -> new BlackJackOperationStrategy(necessaryItems, players);
            case ROULETTE -> new RouletteOperationStrategy(necessaryItems, players);
        };
    }
}
