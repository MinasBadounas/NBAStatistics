package com.nbaproject.repository.quarterscore;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nbaproject.entities.Quarter;

@Repository
public interface QuarterscoreRepository extends JpaRepository<Quarter,Integer> {

}
