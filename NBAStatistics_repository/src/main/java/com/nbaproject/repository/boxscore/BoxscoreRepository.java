package com.nbaproject.repository.boxscore;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nbaproject.entities.Boxscore;

@Repository
public interface BoxscoreRepository  extends JpaRepository<Boxscore,Integer>{

}
