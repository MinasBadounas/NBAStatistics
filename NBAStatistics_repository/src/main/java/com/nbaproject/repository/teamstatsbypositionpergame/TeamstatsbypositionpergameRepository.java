package com.nbaproject.repository.teamstatsbypositionpergame;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nbaproject.entities.Opponentplayerstatspergame;

@Repository
public interface TeamstatsbypositionpergameRepository extends JpaRepository<Opponentplayerstatspergame, Integer> {

	
    @Transactional
    @Modifying
	@Query(value = "insert into teamstatsbypositionpergame(" + 
			"gameid ,teamid ,opponentteamid ,seasontype ,season ,categoryposition ,opponentrank ,isclosed ,games , fieldgoalsmade ," + 
			"fieldgoalsattempted ,fieldgoalspercentage ,effectivefieldgoalspercentage ,twopointersmade ,twopointersattempted ," + 
			"twopointerspercentage ,threepointersmade ,threepointersattempted ,threepointerspercentage ,freethrowssmade ," + 
			"freethrowsattempted ,freethrowspercentage ,offensiverebounds ,defensiverebounds ,rebounds ,offensivereboundspercentage ," + 
			"defensivereboundspercentage ,totalreboundspercentage ,assists ,steals ,blockedshots ,turnovers ,personalfouls ," + 
			"points ,trueshootingattempts ,trueshootingpercentage ,playerefficiencyrating ,assistspercentage ,stealspercentage ," + 
			"blockspercentage ,turnoverspercentage ,usageratepercentage ) " + 
			"select pspg.gameid, pspg.teamid, pspg.opponentid,pspg.seasontype, pspg.season, ?1 , pspg.opponentrank," +
			"pspg.isclosed , pspg.games, "+
			"cast(SUM(pspg.fieldgoalsmade) as decimal(12,2))," + 
			"cast(SUM(pspg.fieldgoalsattempted) as decimal(12,2)),cast(AVG(pspg.fieldgoalspercentage) as decimal(12,2))," + 
			"cast(AVG(pspg.effectivefieldgoalspercentage) as decimal(12,2)),cast(SUM(pspg.twopointersmade) as decimal(12,2))," + 
			"cast(SUM(pspg.twopointersattempted)as decimal(12,2)),cast(AVG(pspg.twopointerspercentage)as decimal(12,2))," + 
			"cast(SUM(pspg.threepointersmade)as decimal(12,2)),cast(SUM(pspg.threepointersattempted)as decimal(12,2))," + 
			"cast(AVG(pspg.threepointerspercentage)as decimal(12,2)),cast(SUM(pspg.freethrowssmade)as decimal(12,2))," + 
			"cast(SUM(pspg.freethrowsattempted)as decimal(12,2)),cast(AVG(pspg.freethrowspercentage)as decimal(12,2)),cast(SUM(pspg.offensiverebounds)as decimal(12,2))," + 
			"cast(SUM(pspg.defensiverebounds)as decimal(12,2)),cast(SUM(pspg.rebounds)as decimal(12,2)),cast(AVG(pspg.offensivereboundspercentage)as decimal(12,2))," + 
			"cast(AVG(pspg.defensivereboundspercentage)as decimal(12,2)),cast(AVG(pspg.totalreboundspercentage)as decimal(12,2))," + 
			"cast(SUM(pspg.assists)as decimal(12,2)),cast(SUM(pspg.steals)as decimal(12,2)),cast(SUM(pspg.blockedshots)as decimal(12,2)),cast(SUM(pspg.turnovers)as decimal(12,2))," + 
			"cast(SUM(pspg.personalfouls)as decimal(12,2)),cast(SUM(pspg.points)as decimal(12,2)),cast(SUM(pspg.trueshootingattempts)as decimal(12,2))," + 
			"cast(AVG(pspg.trueshootingpercentage)as decimal(12,2)),cast(AVG(pspg.playerefficiencyrating)as decimal(12,2)), " + 
			"cast(AVG(pspg.assistspercentage)as decimal(12,2)),cast(AVG(pspg.stealspercentage)as decimal(12,2)),cast(AVG(pspg.blockspercentage)as decimal(12,2))," + 
			"cast(AVG(pspg.turnoverspercentage)as decimal(12,2)), cast(SUM(pspg.usageratepercentage)as decimal(12,2)) " + 
			"from nbastatistics.playerstatspergame  pspg  " + 
			"where pspg.gameid= ?2 and pspg.teamid= ?3 and ((pspg.fanduelposition>= ?4 AND pspg.fanduelposition<= ?5 )) ;", nativeQuery = true)
	void InsertTeamstatsbypositionpergame(int categoryposition, int gameid,int teamid, int minposition, int maxposition);
}
