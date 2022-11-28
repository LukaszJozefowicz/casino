package com.globallogic.casino.service;

import com.globallogic.casino.model.dto.mongo.DailyReportDto;

public interface DailyReportService {
    DailyReportDto generateDailyReport();
}
