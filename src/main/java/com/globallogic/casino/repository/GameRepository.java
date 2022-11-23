package com.globallogic.casino.repository;

import com.globallogic.casino.model.entity.h2.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
}
