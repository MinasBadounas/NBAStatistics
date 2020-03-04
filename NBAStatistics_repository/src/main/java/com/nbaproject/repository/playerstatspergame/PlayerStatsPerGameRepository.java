package com.nbaproject.repository.playerstatspergame;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nbaproject.entities.Playerstatspergame;

@Repository
public interface PlayerStatsPerGameRepository extends JpaRepository<Playerstatspergame,Integer> {
	
	 @Query(value = "select gameid from playerstatspergame ;", nativeQuery = true)
	    ArrayList<Integer> findAllGameId();
	 
	 @Query(value = "select Max(gameid) from playerstatspergame ;", nativeQuery = true)
	    Integer maxGameId();
	 
	 @Query(value = "select * from playerstatspergame where gameid= ?1 and minutes> ?2 ;", nativeQuery = true)
	    ArrayList<Playerstatspergame> findPlayerStatsPerGameByGameId(int gameid, int minutes);

}
