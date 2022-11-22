package com.globallogic.casino.controller;

import com.globallogic.casino.model.dto.CustomerDto;
import com.globallogic.casino.model.dto.GameDto;
import com.globallogic.casino.model.dto.ItemDto;
import com.globallogic.casino.model.entity.Game;
import com.globallogic.casino.model.games.Poker;
import com.globallogic.casino.service.CustomerService;
import com.globallogic.casino.service.GameService;
import com.globallogic.casino.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
//@RequestMapping("/games")
public class GamesController {

    private final GameService gameService;
    private final CustomerService customerService;
    private final ItemService itemService;
    private final Poker poker;
    private final ModelMapper modelMapper;

    @PostMapping("/games/addPokerGame")
    public GameDto addPokerGame() {
        GameDto newPokerGame = gameService.createGame(poker);
        itemService.saveItemsAfterAssignedToGame(modelMapper.map(newPokerGame, Game.class));
        return newPokerGame;
    }

    @PostMapping("/customer/{customerId}/joinGame/{gameId}")
    public CustomerDto joinGame(@PathVariable Long customerId, @PathVariable Long gameId) {
        return customerService.joinGame(customerId, gameId);
    }

    @PutMapping("/games/{gameId}/simulate")
    public void simulateGame(@PathVariable Long gameId) {
        gameService.simulateGame(gameId);
    }

    @GetMapping("/games/{gameId}/checkItems")
    public List<ItemDto> checkItems(@PathVariable Long gameId) {
        return gameService.checkItems(gameId);
    }

}
