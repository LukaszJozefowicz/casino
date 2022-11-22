package com.globallogic.casino.service;

import com.globallogic.casino.model.dto.ItemDto;
import com.globallogic.casino.model.entity.Game;
import com.globallogic.casino.model.dto.GameDto;

import java.util.List;

public interface GameService {
    GameDto createGame(Game game);
    void simulateGame(Long gameId);
    List<ItemDto> checkItems(Long gameId);
}
