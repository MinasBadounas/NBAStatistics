package com.nbaproject.repository.playerprops;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nbaproject.entities.Playerprop;

@Repository
public interface PlayerpropRepository extends JpaRepository<Playerprop, Integer> {

	@Query(value = "select * from playerprops where playerid = ?1 ;", nativeQuery = true)
	ArrayList<Playerprop> findPlayerpropsByPlayerId(int playerid);

	@Query(value = "select * from playerprops where gameid = ?1 ;", nativeQuery = true)
	ArrayList<Playerprop> findPlayerpropsByGameId(int gameid);

	@Query(value = "select * from playerprops where gameid = ?1 and playerid= ?2 ;", nativeQuery = true)
	ArrayList<Playerprop> findPlayerpropsByGameIdPlayerId(int gameid, int playerid);

	@Query(value = "select ps.playerid,ps.gameid,coalesce(pp.description, ?2 ) as description,pp.datetime,coalesce(pp.overunder,0) as overunder,coalesce(pp.overpayout,0) as overpayout,coalesce(pp.underpayout,0) as underpayout ,coalesce(pp.statresult,0) as statresult ,pp.betresult from (select gameid , playerid from playerstatspergame where  playerid = ?1 ) ps "
			+ "left join playerprops pp on  ps.gameid=pp.gameid and ps.playerid=pp.playerid "
			+ " where ps.playerid = ?1 and pp.description = ?2 or pp.gameid is null order by ps.gameid ;", nativeQuery = true)
	ArrayList<Playerprop> findPlayerpropsByPlayerIdAndDescription(int playerid, int description);
	
	@Query(value = "select * from(select ps.playerid,ps.gameid,coalesce(pp.description, ?2 ) as description,pp.datetime,coalesce(pp.overunder,0) as overunder,coalesce(pp.overpayout,0) as overpayout,coalesce(pp.underpayout,0) as underpayout ,coalesce(pp.statresult,0) as statresult ,pp.betresult from (select gameid , playerid from playerstatspergame where  playerid = ?1 ) ps "
			+ "left join playerprops pp on  ps.gameid=pp.gameid and ps.playerid=pp.playerid "
			+ " where ps.playerid = ?1 and pp.description = ?2 or pp.gameid is null order by ps.gameid desc limit ?3) newtable order by newtable.gameid asc;", nativeQuery = true)
	ArrayList<Playerprop> findLastPlayerpropsByPlayerIdAndDescription(int playerid, int description,int lastX);

}
