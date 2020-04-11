package com.nbaproject.repository.playerprops;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nbaproject.entities.Playerprop;

@Repository
public interface PlayerpropRepository extends JpaRepository<Playerprop,Integer>{

	  @Query(value = "select * from playerprops where playerid = ?1 ;", nativeQuery = true)
	    ArrayList<Playerprop> findPlayerpropsByPlayerId(int playerid);
	  
	  @Query(value = "select * from playerprops where gameid = ?1 ;", nativeQuery = true)
	    ArrayList<Playerprop> findPlayerpropsByGameId(int gameid);
	  
	  @Query(value = "select * from playerprops where gameid = ?1 and playerid= ?2 ;", nativeQuery = true)
	    ArrayList<Playerprop> findPlayerpropsByGameIdPlayerId(int gameid, int playerid);
	  
	  @Query(value = "select * from playerprops where playerid = ?1 and description = ?2 ;", nativeQuery = true)
	    ArrayList<Playerprop> findPlayerpropsByPlayerIdAndDescription(int playerid, int description);
	  
}
