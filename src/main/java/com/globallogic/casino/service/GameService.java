package com.globallogic.casino.service;

import com.globallogic.casino.model.dto.h2.ItemsCheckResponseDto;
import com.globallogic.casino.model.entity.h2.Game;
import com.globallogic.casino.model.dto.h2.GameDto;

import java.math.BigDecimal;

public interface GameService {
    GameDto createGame(Game game);
    String simulateGame(Long gameId, BigDecimal playerBet);
    ItemsCheckResponseDto checkGameItems(Long gameId);
}
