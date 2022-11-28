package com.globallogic.casino.controller;

import com.globallogic.casino.model.dto.h2.GameDto;
import com.globallogic.casino.model.dto.h2.ItemsCheckResponseDto;
import com.globallogic.casino.model.entity.h2.Game;
import com.globallogic.casino.model.factory.BlackJackCreator;
import com.globallogic.casino.model.factory.PokerCreator;
import com.globallogic.casino.model.factory.RouletteCreator;
import com.globallogic.casino.model.factory.SlotMachineCreator;
import com.globallogic.casino.service.GameService;
import com.globallogic.casino.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/games")
public class GameController {

    private final GameService gameService;
    private final ItemService itemService;
    private final PokerCreator pokerCreator;
    private final BlackJackCreator blackJackCreator;
    private final RouletteCreator rouletteCreator;
    private final SlotMachineCreator slotMachineCreator;
    private final ModelMapper modelMapper;

    @PostMapping("/addPokerGame")
    public GameDto addPokerGame() {
        GameDto newPokerGame = gameService.createGame(pokerCreator);
        itemService.saveItemsAfterAssignedToGame(modelMapper.map(newPokerGame, Game.class));
        return newPokerGame;
    }

    @PostMapping("/addRouletteGame")
    public GameDto addRouletteGame() {
        GameDto newRouletteGame = gameService.createGame(rouletteCreator);
        itemService.saveItemsAfterAssignedToGame(modelMapper.map(newRouletteGame, Game.class));
        return newRouletteGame;
    }

    @PostMapping("/addBlackJackGame")
    public GameDto addBlackJackGame() {
        GameDto newBlackJackGame = gameService.createGame(blackJackCreator);
        itemService.saveItemsAfterAssignedToGame(modelMapper.map(newBlackJackGame, Game.class));
        return newBlackJackGame;
    }

    @PostMapping("/addSlotMachineGame")
    public GameDto addSlotMachineGame() {
        GameDto newSlotMachineGame = gameService.createGame(slotMachineCreator);
        itemService.saveItemsAfterAssignedToGame(modelMapper.map(newSlotMachineGame, Game.class));
        return newSlotMachineGame;
    }

    @PutMapping("/{gameId}/simulate")
    public String simulateGame(@PathVariable Long gameId, @RequestParam BigDecimal playerBet) {
        return gameService.simulateGame(gameId, playerBet);
    }

    @GetMapping("/{gameId}/checkItems")
    public ItemsCheckResponseDto checkItems(@PathVariable Long gameId) {
        return gameService.checkGameItems(gameId);
    }

}
