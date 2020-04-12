package com.nbaproject.repository.playerseasonstats;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nbaproject.entities.Playerseasonstat;

@Repository
public interface PlayerseasonstatsRepository extends JpaRepository<Playerseasonstat, Integer> {

	@Query(value = "select * from playerseasonstats where playerid = ?1 ;", nativeQuery = true)
	ArrayList<Playerseasonstat> findPlayerseasonstatsByPlayerId(int playerid);

	@Query(value = "select * from playerseasonstats where playerid = ?1 and statid=(select max(statid) from playerseasonstats where playerid = ?1 );", nativeQuery = true)
	Playerseasonstat findLastPlayerseasonstatsByPlayerId(int playerid);
	

}
