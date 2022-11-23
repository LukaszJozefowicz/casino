package com.globallogic.casino.service;

import com.globallogic.casino.model.dto.ItemDto;
import com.globallogic.casino.model.entity.h2.Game;
import com.globallogic.casino.model.dto.GameDto;

import java.util.List;

public interface GameService {
    GameDto createGame(Game game);
    String simulateGame(Long gameId, long playerBet);
    List<ItemDto> checkGameItems(Long gameId);
}
