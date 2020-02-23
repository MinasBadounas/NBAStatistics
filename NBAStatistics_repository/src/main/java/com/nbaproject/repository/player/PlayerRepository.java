package com.nbaproject.repository.player;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nbaproject.entities.Player;
import com.nbaproject.entities.Team;

@Repository
public interface PlayerRepository extends JpaRepository<Player,Integer>  {

	  @Query(value = "select playerid from players where playerid = ?1 ;", nativeQuery = true)
	    Integer findPlayerById(int playerid);
}
