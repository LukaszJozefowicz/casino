package com.globallogic.casino.controller;

import com.globallogic.casino.model.dto.GameDto;
import com.globallogic.casino.model.dto.ItemDto;
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

import java.util.List;

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

    @PutMapping("/{gameId}/simulate")
    public String simulateGame(@PathVariable Long gameId, @RequestParam Long playerBet) {
        return gameService.simulateGame(gameId, playerBet);
    }

    @GetMapping("/{gameId}/checkItems")
    public List<ItemDto> checkItems(@PathVariable Long gameId) {
        return gameService.checkGameItems(gameId);
    }

}
