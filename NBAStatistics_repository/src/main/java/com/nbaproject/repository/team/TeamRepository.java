package com.nbaproject.repository.team;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nbaproject.entities.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team,Integer> {

}
