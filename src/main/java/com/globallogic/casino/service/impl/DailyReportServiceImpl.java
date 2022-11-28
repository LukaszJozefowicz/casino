package com.globallogic.casino.service.impl;

import com.globallogic.casino.model.dto.mongo.DailyReportDto;
import com.globallogic.casino.model.entity.h2.Game;
import com.globallogic.casino.model.entity.mongo.DailyReport;
import com.globallogic.casino.model.entity.mongo.GameReport;
import com.globallogic.casino.model.enums.GameType;
import com.globallogic.casino.repository.DailyReportRepository;
import com.globallogic.casino.repository.GameRepository;
import com.globallogic.casino.service.DailyReportService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DailyReportServiceImpl implements DailyReportService {

    private final GameRepository gameRepository;
    private final DailyReportRepository dailyReportRepository;
    private final ModelMapper modelMapper;

    @Override
    public DailyReportDto generateDailyReport() {
        List<Game> allGames = gameRepository.findAll();
        BigDecimal totalIncome = allGames.stream()
                .map(Game::getTotalIncome)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        Integer totalGamesPlayed = allGames.stream()
                .map(Game::getTimesPlayed)
                .reduce(0, Integer::sum);

        DailyReport reportToSave = DailyReport.builder()
                .totalIncome(totalIncome.toPlainString())
                .totalGamesPlayed(totalGamesPlayed)
                .pokerReport(GameReport.builder()
                        .timesPlayed(getTimesPlayedByGameType(allGames, GameType.POKER))
                        .totalIncome(getTotalIncomeByGameType(allGames, GameType.POKER).toPlainString())
                        .averageIncomePerGame(getAverageIncomePerGameByGameType(allGames, GameType.POKER).toPlainString())
                        .build())
                .rouletteReport(GameReport.builder()
                        .timesPlayed(getTimesPlayedByGameType(allGames, GameType.ROULETTE))
                        .totalIncome(getTotalIncomeByGameType(allGames, GameType.ROULETTE).toPlainString())
                        .averageIncomePerGame(getAverageIncomePerGameByGameType(allGames, GameType.ROULETTE).toPlainString())
                        .build())
                .blackJackReport(GameReport.builder()
                        .timesPlayed(getTimesPlayedByGameType(allGames, GameType.BLACKJACK))
                        .totalIncome(getTotalIncomeByGameType(allGames, GameType.BLACKJACK).toPlainString())
                        .averageIncomePerGame(getAverageIncomePerGameByGameType(allGames, GameType.BLACKJACK).toPlainString())
                        .build())
                .slotMachinesReport(GameReport.builder()
                        .timesPlayed(getTimesPlayedByGameType(allGames, GameType.SLOT_MACHINE))
                        .totalIncome(getTotalIncomeByGameType(allGames, GameType.SLOT_MACHINE).toPlainString())
                        .averageIncomePerGame(getAverageIncomePerGameByGameType(allGames, GameType.SLOT_MACHINE).toPlainString())
                        .build())
                .build();
        dailyReportRepository.save(reportToSave);
        return modelMapper.map(reportToSave, DailyReportDto.class);
    }

    private Integer getTimesPlayedByGameType(List<Game> allGames, GameType gameType) {
        return allGames.stream()
                .filter(game -> game.getGameType().equals(gameType))
                .map(Game::getTimesPlayed)
                .reduce(0, Integer::sum);
    }

    private BigDecimal getTotalIncomeByGameType(List<Game> allGames, GameType gameType) {
        return allGames.stream()
                .filter(game -> game.getGameType().equals(gameType))
                .map(Game::getTotalIncome)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal getAverageIncomePerGameByGameType(List<Game> allGames, GameType gameType) {
        BigDecimal totalIncome = getTotalIncomeByGameType(allGames, gameType);
        Integer timesPlayed = getTimesPlayedByGameType(allGames, gameType);
        BigDecimal averageIncomesDivisor = timesPlayed == 0
                ? BigDecimal.ONE
                : BigDecimal.valueOf(timesPlayed);
        return totalIncome
                .divide(averageIncomesDivisor, new MathContext(15))
                .setScale(2, RoundingMode.CEILING);
    }
}
