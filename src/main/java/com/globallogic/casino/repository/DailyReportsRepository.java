package com.globallogic.casino.repository;

import com.globallogic.casino.model.entity.DailyReport;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyReportsRepository extends MongoRepository<DailyReport, Long> {
}
