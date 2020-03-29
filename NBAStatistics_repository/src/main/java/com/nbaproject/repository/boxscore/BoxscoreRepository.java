package com.nbaproject.repository.boxscore;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nbaproject.entities.Boxscore;


@Repository
public interface BoxscoreRepository  extends JpaRepository<Boxscore,Integer>{
	
	 @Query(value = "select MAX(gameid) from boxscore ;", nativeQuery = true)
	    Integer findMaxGameId();
	 
	 @Query(value = "select gameid from boxscore order by gameid;", nativeQuery = true)
	    ArrayList<Integer> findAllGameId();
	
	 @Query(value = "select * from boxscore where gameid= ?1 ;", nativeQuery = true)
	    Boxscore findBoxscorebyGameId(int gameid);
	 
	 @Query(value = "select awayteamid, hometeamid from boxscore where gameid= ?1 ;", nativeQuery = true)
	    ArrayList<Integer> findTeamsIdbyGameId(int gameid);
	 

}
