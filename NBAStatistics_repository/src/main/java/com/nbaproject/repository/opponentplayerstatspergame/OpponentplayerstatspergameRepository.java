package com.nbaproject.repository.opponentplayerstatspergame;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nbaproject.entities.Opponentplayerstatspergame;

@Repository
public interface OpponentplayerstatspergameRepository extends JpaRepository<Opponentplayerstatspergame, Integer> {

    @Transactional
    @Modifying
	@Query(value = "insert into opponentplayerstatspergame ("
			+ "gameid   ,playerid   ,teamid   ,opponentid   ,seasontype   ,season   ,opponentrank  ,MIN_opponentpositionrank  ,"
			+ "MAX_opponentpositionrank  ,games ,minutes ,AVG_fieldgoalsmade ,AVG_fieldgoalsattempted ,AVG_fieldgoalspercentage ,"
			+ "AVG_effectivefieldgoalspercentage ,AVG_twopointersmade ,AVG_twopointersattempted ,AVG_twopointerspercentage ,AVG_threepointersmade ,"
			+ "AVG_threepointersattempted ,AVG_threepointerspercentage ,AVG_freethrowssmade ,AVG_freethrowsattempted ,AVG_freethrowspercentage ,"
			+ "AVG_offensiverebounds ,AVG_defensiverebounds ,AVG_rebounds ,AVG_offensivereboundspercentage ,AVG_defensivereboundspercentage ,"
			+ "AVG_totalreboundspercentage ,AVG_assists ,AVG_steals ,AVG_blockedshots ,AVG_turnovers ,AVG_personalfouls ,AVG_points ,"
			+ "AVG_trueshootingattempts ,AVG_trueshootingpercentage ,AVG_playerefficiencyrating ,AVG_assistspercentage ,AVG_stealspercentage ,"
			+ "AVG_blockspercentage ,AVG_turnoverspercentage ,AVG_usageratepercentage ,AVG_plusminus)"
			+ "select dfu.gameid, dfu.playerid_1,pspg.teamid, pspg.opponentid,pspg.seasontype, pspg.season,pspg.opponentrank,"
			+ "MIN(pspg.opponentpositionrank) MINopponentpositionrank,max(pspg.opponentpositionrank) MAXopponentpositionrank,pspg.games,"
			+ "cast(AVG(pspg.minutes) as decimal(12,2)),cast(AVG(pspg.fieldgoalsmade) as decimal(12,2)),"
			+ "cast(AVG(pspg.fieldgoalsattempted) as decimal(12,2)),cast(AVG(pspg.fieldgoalspercentage) as decimal(12,2)),"
			+ "cast(AVG(pspg.effectivefieldgoalspercentage) as decimal(12,2)),cast(AVG(pspg.twopointersmade) as decimal(12,2)),"
			+ "cast(AVG(pspg.twopointersattempted)as decimal(12,2)),cast(AVG(pspg.twopointerspercentage)as decimal(12,2)),"
			+ "cast(AVG(pspg.threepointersmade)as decimal(12,2)),cast(AVG(pspg.threepointersattempted)as decimal(12,2)),"
			+ "cast(AVG(pspg.threepointerspercentage)as decimal(12,2)),cast(AVG(pspg.freethrowssmade)as decimal(12,2)),"
			+ "cast(AVG(pspg.freethrowsattempted)as decimal(12,2)),cast(AVG(pspg.freethrowspercentage)as decimal(12,2)),cast(AVG(pspg.offensiverebounds)as decimal(12,2)),"
			+ "cast(AVG(pspg.defensiverebounds)as decimal(12,2)),cast(AVG(pspg.rebounds)as decimal(12,2)),cast(AVG(pspg.offensivereboundspercentage)as decimal(12,2)),"
			+ "cast(AVG(pspg.defensivereboundspercentage)as decimal(12,2)),cast(AVG(pspg.totalreboundspercentage)as decimal(12,2)),"
			+ "cast(AVG(pspg.assists)as decimal(12,2)),cast(AVG(pspg.steals)as decimal(12,2)),cast(AVG(pspg.blockedshots)as decimal(12,2)),cast(AVG(pspg.turnovers)as decimal(12,2)),"
			+ "cast(AVG(pspg.personalfouls)as decimal(12,2)),cast(AVG(pspg.points)as decimal(12,2)),cast(AVG(pspg.trueshootingattempts)as decimal(12,2)),"
			+ "cast(AVG(pspg.trueshootingpercentage)as decimal(12,2)),cast(AVG(pspg.playerefficiencyrating)as decimal(12,2)), "
			+ "cast(AVG(pspg.assistspercentage)as decimal(12,2)),cast(AVG(pspg.stealspercentage)as decimal(12,2)),cast(AVG(pspg.blockspercentage)as decimal(12,2)),"
			+ "cast(AVG(pspg.turnoverspercentage)as decimal(12,2)), cast(AVG(pspg.usageratepercentage)as decimal(12,2)),cast(AVG(pspg.plusminus)as decimal(12,2)) "
			+ "from nbastatistics.defencematchup dfu "
			+ "left join nbastatistics.playerstatspergame  pspg  on dfu.playerid_2=pspg.playerid "
			+ "where dfu.gameid= ?1 and dfu.playerid_1= ?2 ;", nativeQuery = true)
	void InsertOpponentplayerstatspergame(int gameid, int playerid);

    @Transactional
    @Modifying
	@Query(value = "insert into opponentplayerstatspergame ("
			+ "gameid   ,playerid   ,teamid   ,opponentid   ,seasontype   ,season   ,opponentrank  ,MIN_opponentpositionrank  ,"
			+ "MAX_opponentpositionrank  ,games ,minutes ,AVG_fieldgoalsmade ,AVG_fieldgoalsattempted ,AVG_fieldgoalspercentage ,"
			+ "AVG_effectivefieldgoalspercentage ,AVG_twopointersmade ,AVG_twopointersattempted ,AVG_twopointerspercentage ,AVG_threepointersmade ,"
			+ "AVG_threepointersattempted ,AVG_threepointerspercentage ,AVG_freethrowssmade ,AVG_freethrowsattempted ,AVG_freethrowspercentage ,"
			+ "AVG_offensiverebounds ,AVG_defensiverebounds ,AVG_rebounds ,AVG_offensivereboundspercentage ,AVG_defensivereboundspercentage ,"
			+ "AVG_totalreboundspercentage ,AVG_assists ,AVG_steals ,AVG_blockedshots ,AVG_turnovers ,AVG_personalfouls ,AVG_points ,"
			+ "AVG_trueshootingattempts ,AVG_trueshootingpercentage ,AVG_playerefficiencyrating ,AVG_assistspercentage ,AVG_stealspercentage ,"
			+ "AVG_blockspercentage ,AVG_turnoverspercentage ,AVG_usageratepercentage ,AVG_plusminus)"
			+ "select dfu.gameid, dfu.playerid_1,pspg.teamid, pspg.opponentid,pspg.seasontype, pspg.season,pspg.opponentrank,"
			+ "MIN(pspg.opponentpositionrank) MINopponentpositionrank,max(pspg.opponentpositionrank) MAXopponentpositionrank,pspg.games,"
			+ "null ,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,"
			+ "null,null,null,null,null, null,null,null,null, null,null "
			+ "from nbastatistics.defencematchup dfu "
			+ "left join nbastatistics.playerstatspergame  pspg  on dfu.playerid_2=pspg.playerid"
			+ "where dfu.gameid= ?1 and dfu.playerid_1= ?2 ;", nativeQuery = true)
	void InsertNullOpponentplayerstatspergame(int gameid, int playerid);
}
