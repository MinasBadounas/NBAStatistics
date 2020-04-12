package com.nbaproject.utils.update;

import java.util.ArrayList;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.nbaproject.entities.Playerseasonstat;
import com.nbaproject.entities.PlayerseasonstatPK;
import com.nbaproject.entities.Playerstatspergame;
import com.nbaproject.utils.staticInitializer.AppconfigServiceStaticInitializer;
import com.nbaproject.utils.staticInitializer.PlayerseasonstatsServiceStaticInitializer;
import com.nbaproject.utils.staticInitializer.PlayerstatspergameServiceStaticInitializer;

@Component
public class UpdatePlayerseasonstats {

	public static void UpdatePlayerseasonstatsSchedule(int gameid) {

		System.out.println("Run 'UpdatePlayerseasonstatsSchedule()'");
		long timeNow = System.currentTimeMillis();

		ArrayList<Playerstatspergame> playerstatspergameList = PlayerstatspergameServiceStaticInitializer
				.findAllPlayerStatsPerGameByGameId(gameid);

		for (Playerstatspergame playerstatspergame : playerstatspergameList) {

			String url = "https://api.sportsdata.io/v3/nba/stats/json/PlayerSeasonStatsByPlayer/"
					+ AppconfigServiceStaticInitializer.getKeyValuefromAppconfig("season") + "/"
					+ playerstatspergame.getPlayer().getPlayerid() + "?key="
					+ AppconfigServiceStaticInitializer.getKeyValuefromAppconfig("sportsdataio.key");

			RestTemplate restTemplate = new RestTemplate();

			ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

			JSONObject JObject = new JSONObject(response.getBody());

			Playerseasonstat newPlayerseasonstats = new Playerseasonstat();

			PlayerseasonstatPK newPlayerseasonstatsPK = new PlayerseasonstatPK(JObject.getInt("StatID"),
					JObject.getInt("TeamID"),	JObject.getInt("PlayerID"));

			newPlayerseasonstats.setId(newPlayerseasonstatsPK);
			newPlayerseasonstats.setSeasontype(JObject.getInt("SeasonType"));
			newPlayerseasonstats.setSeason(JObject.getInt("Season"));
			newPlayerseasonstats.setStarted(JObject.getInt("Started"));
			newPlayerseasonstats.setGames(JObject.getInt("Games"));
			newPlayerseasonstats.setMinutes(JObject.getInt("Minutes"));
			newPlayerseasonstats.setSeconds(JObject.getInt("Seconds"));
			newPlayerseasonstats.setFieldgoalsmade(JObject.getDouble("FieldGoalsMade"));
			newPlayerseasonstats.setFieldgoalsattempted(JObject.getDouble("FieldGoalsAttempted"));
			newPlayerseasonstats.setFieldgoalspercentage(JObject.getDouble("FieldGoalsPercentage"));
			newPlayerseasonstats.setEffectivefieldgoalspercentage(JObject.getDouble("EffectiveFieldGoalsPercentage"));
			newPlayerseasonstats.setTwopointersmade(JObject.getDouble("TwoPointersMade"));
			newPlayerseasonstats.setTwopointersattempted(JObject.getDouble("TwoPointersAttempted"));
			newPlayerseasonstats.setTwopointerspercentage(JObject.getDouble("TwoPointersPercentage"));
			newPlayerseasonstats.setThreepointersmade(JObject.getDouble("ThreePointersMade"));
			newPlayerseasonstats.setThreepointersattempted(JObject.getDouble("ThreePointersAttempted"));
			newPlayerseasonstats.setThreepointerspercentage(JObject.getDouble("ThreePointersPercentage"));
			newPlayerseasonstats.setFreethrowssmade(JObject.getDouble("FreeThrowsMade"));
			newPlayerseasonstats.setFreethrowsattempted(JObject.getDouble("FreeThrowsAttempted"));
			newPlayerseasonstats.setFreethrowspercentage(JObject.getDouble("FreeThrowsPercentage"));
			newPlayerseasonstats.setOffensiverebounds(JObject.getDouble("OffensiveRebounds"));
			newPlayerseasonstats.setDefensiverebounds(JObject.getDouble("DefensiveRebounds"));
			newPlayerseasonstats.setRebounds(JObject.getDouble("Rebounds"));
			newPlayerseasonstats.setOffensivereboundspercentage(JObject.getDouble("OffensiveReboundsPercentage"));
			newPlayerseasonstats.setDefensivereboundspercentage(JObject.getDouble("DefensiveReboundsPercentage"));
			newPlayerseasonstats.setTotalreboundspercentage(JObject.getDouble("TotalReboundsPercentage"));
			newPlayerseasonstats.setAssists(JObject.getDouble("Assists"));
			newPlayerseasonstats.setSteals(JObject.getDouble("Steals"));
			newPlayerseasonstats.setBlockedshots(JObject.getDouble("BlockedShots"));
			newPlayerseasonstats.setTurnovers(JObject.getDouble("Turnovers"));
			newPlayerseasonstats.setPersonalfouls(JObject.getDouble("PersonalFouls"));
			newPlayerseasonstats.setPoints(JObject.getDouble("Points"));
			newPlayerseasonstats.setTrueshootingpercentage(JObject.getDouble("TrueShootingPercentage"));
			newPlayerseasonstats.setTrueshootingattempts(JObject.getDouble("TrueShootingAttempts"));
			newPlayerseasonstats.setPlayerefficiencyrating(JObject.getDouble("PlayerEfficiencyRating"));
			newPlayerseasonstats.setAssistspercentage(JObject.getDouble("AssistsPercentage"));
			newPlayerseasonstats.setStealspercentage(JObject.getDouble("StealsPercentage"));
			newPlayerseasonstats.setBlockspercentage(JObject.getDouble("BlocksPercentage"));
			newPlayerseasonstats.setTurnoverspercentage(JObject.getDouble("TurnOversPercentage"));
			newPlayerseasonstats.setUsageratepercentage(JObject.getDouble("UsageRatePercentage"));
			newPlayerseasonstats.setPlusminus(JObject.getDouble("PlusMinus"));
			newPlayerseasonstats.setDoubledoubles(JObject.getDouble("DoubleDoubles"));
			newPlayerseasonstats.setTripledoubles(JObject.getDouble("TripleDoubles"));
			newPlayerseasonstats.setLineupconfirmed(JObject.getString("LineupConfirmed"));
			newPlayerseasonstats.setLineupstatus(JObject.getString("LineupStatus"));

			System.out.println("Save to Playerseasonstats PlayerId: " + JObject.getInt("PlayerID"));

			PlayerseasonstatsServiceStaticInitializer.addPlayerseasonstat(newPlayerseasonstats);
			
		}
		
		/** ---------Calculate the process time for each game--------- **/
		long timeEnd = System.currentTimeMillis();
		System.out.println(
				"Total time process time 'UpdatePlayerseasonstatsSchedule()' : " + (timeEnd - timeNow) / 1000 + " sec");
	}

}
