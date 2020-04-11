package com.nbaproject.utils.async;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import com.nbaproject.entities.Playerstatspergame;
import com.nbaproject.entities.PlayerstatspergamePK;
import com.nbaproject.service.playerstatspergame.PlayerstatspergameService;
import com.nbaproject.utils.checker.CheckPlayer;
import com.nbaproject.utils.converter.ConvertPositionToInt;
import com.nbaproject.utils.defenceMatchup.DefenceMatchUp;
import com.nbaproject.utils.opponentplayerstatspergame.OpponentPlayerStatsPerGame;
import com.nbaproject.utils.staticInitializer.AppconfigServiceStaticInitializer;
import com.nbaproject.utils.teamstatsbypositionpergame.Teamstatsbypositionpergame;

@Configuration
@EnableScheduling
@EnableAsync
public class UpdatePlayerGames {

	@Autowired
	private PlayerstatspergameService playerstatspergameService;

	@Async("threadPoolTaskExecutor")
	@Scheduled(cron = "0 22 21 * * ?")
	public void UpdatePlayerGamesStas() throws IOException {

//		int maxGameId = playerstatspergameService.maxGameIdInPlayerStatsPerGame();
//		ArrayList<Integer> gameIdList = boxscoreService.findAllGameIdBoxscore();
		System.out.println("Run 'UpadatePlayerGamesStas()'");
		long timeNow = System.currentTimeMillis();
//		for (Integer gameId : gameIdList) {
//			System.out.println(gameId);
//
//			if (gameId > maxGameId) {
//				int gameId=13905;
		ArrayList<Integer> gameIdList = new ArrayList<Integer>();	
		
		for(int gameId:gameIdList) {
//		for (int gameId = 14000; gameId < 14000; gameId++) {

			String url = "https://api.sportsdata.io/v3/nba/stats/json/BoxScore/" + gameId + "?key="
					+ AppconfigServiceStaticInitializer.getKeyValuefromAppconfig("sportsdataio.key");

			RestTemplate restTemplate = new RestTemplate();

			ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

			JSONObject Jobj = new JSONObject(response.getBody());

			JSONArray JArray = Jobj.getJSONArray("PlayerGames");

			for (int i = 0; i < JArray.length(); i++) {

				JSONObject JObject = JArray.getJSONObject(i);
				Playerstatspergame newPlayerstatspergame = new Playerstatspergame();
				CheckPlayer.CheckPlayerInDB(JObject.getInt("PlayerID"));
				PlayerstatspergamePK newPlayerstatspergamePK = new PlayerstatspergamePK(JObject.getInt("GameID"),
						JObject.getInt("PlayerID"));

				newPlayerstatspergame.setId(newPlayerstatspergamePK);
				newPlayerstatspergame.setTeamid(JObject.getInt("TeamID"));
				newPlayerstatspergame.setOpponentid(JObject.getInt("OpponentID"));
				newPlayerstatspergame.setSeasontype(JObject.getInt("SeasonType"));
				newPlayerstatspergame.setSeason(JObject.getInt("Season"));
				newPlayerstatspergame.setInjurestatus(JObject.getString("InjuryStatus"));
				newPlayerstatspergame
						.setFanduelposition(ConvertPositionToInt.PositionToInt(JObject.getString("FanDuelPosition")));
				newPlayerstatspergame.setDraftkingsposition(
						ConvertPositionToInt.PositionToInt(JObject.getString("DraftKingsPosition")));
				newPlayerstatspergame.setStarted(JObject.getInt("Started"));

				if (JObject.getString("OpponentRank") == "null") {
					newPlayerstatspergame.setOpponentrank(0);
				} else {
					newPlayerstatspergame.setOpponentrank(JObject.getInt("OpponentRank"));
				}

				if (JObject.getString("OpponentPositionRank") == "null") {
					newPlayerstatspergame.setOpponentpositionrank(0);
				} else {
					newPlayerstatspergame.setOpponentpositionrank(JObject.getInt("OpponentPositionRank"));
				}

				newPlayerstatspergame.setGames(JObject.getInt("Games"));
				newPlayerstatspergame.setMinutes(JObject.getInt("Minutes"));
				newPlayerstatspergame.setSeconds(JObject.getInt("Seconds"));
				newPlayerstatspergame.setFieldgoalsmade(JObject.getDouble("FieldGoalsMade"));
				newPlayerstatspergame.setFieldgoalsattempted(JObject.getDouble("FieldGoalsAttempted"));
				newPlayerstatspergame.setFieldgoalspercentage(JObject.getDouble("FieldGoalsPercentage"));
				newPlayerstatspergame
						.setEffectivefieldgoalspercentage(JObject.getDouble("EffectiveFieldGoalsPercentage"));
				newPlayerstatspergame.setTwopointersmade(JObject.getDouble("TwoPointersMade"));
				newPlayerstatspergame.setTwopointersattempted(JObject.getDouble("TwoPointersAttempted"));
				newPlayerstatspergame.setTwopointerspercentage(JObject.getDouble("TwoPointersPercentage"));
				newPlayerstatspergame.setThreepointersmade(JObject.getDouble("ThreePointersMade"));
				newPlayerstatspergame.setThreepointersattempted(JObject.getDouble("ThreePointersAttempted"));
				newPlayerstatspergame.setThreepointerspercentage(JObject.getDouble("ThreePointersPercentage"));
				newPlayerstatspergame.setFreethrowssmade(JObject.getDouble("FreeThrowsMade"));
				newPlayerstatspergame.setFreethrowsattempted(JObject.getDouble("FreeThrowsAttempted"));
				newPlayerstatspergame.setFreethrowspercentage(JObject.getDouble("FreeThrowsPercentage"));
				newPlayerstatspergame.setOffensiverebounds(JObject.getDouble("OffensiveRebounds"));
				newPlayerstatspergame.setDefensiverebounds(JObject.getDouble("DefensiveRebounds"));
				newPlayerstatspergame.setRebounds(JObject.getDouble("Rebounds"));
				newPlayerstatspergame.setOffensivereboundspercentage(JObject.getDouble("OffensiveReboundsPercentage"));
				newPlayerstatspergame.setDefensivereboundspercentage(JObject.getDouble("DefensiveReboundsPercentage"));
				newPlayerstatspergame.setTotalreboundspercentage(JObject.getDouble("TotalReboundsPercentage"));
				newPlayerstatspergame.setAssists(JObject.getDouble("Assists"));
				newPlayerstatspergame.setSteals(JObject.getDouble("Steals"));
				newPlayerstatspergame.setBlockedshots(JObject.getDouble("BlockedShots"));
				newPlayerstatspergame.setTurnovers(JObject.getDouble("Turnovers"));
				newPlayerstatspergame.setPersonalfouls(JObject.getDouble("PersonalFouls"));
				newPlayerstatspergame.setPoints(JObject.getDouble("Points"));
				newPlayerstatspergame.setTrueshootingpercentage(JObject.getDouble("TrueShootingPercentage"));
				newPlayerstatspergame.setTrueshootingattempts(JObject.getDouble("TrueShootingAttempts"));
				newPlayerstatspergame.setPlayerefficiencyrating(JObject.getDouble("PlayerEfficiencyRating"));
				newPlayerstatspergame.setAssistspercentage(JObject.getDouble("AssistsPercentage"));
				newPlayerstatspergame.setStealspercentage(JObject.getDouble("StealsPercentage"));
				newPlayerstatspergame.setBlockspercentage(JObject.getDouble("BlocksPercentage"));
				newPlayerstatspergame.setTurnoverspercentage(JObject.getDouble("TurnOversPercentage"));
				newPlayerstatspergame.setUsageratepercentage(JObject.getDouble("UsageRatePercentage"));
				newPlayerstatspergame.setPlusminus(JObject.getDouble("PlusMinus"));
				newPlayerstatspergame.setDoubledoubles(JObject.getDouble("DoubleDoubles"));
				newPlayerstatspergame.setTripledoubles(JObject.getDouble("TripleDoubles"));
				newPlayerstatspergame.setLineupconfirmed(JObject.getString("LineupConfirmed"));
				newPlayerstatspergame.setLineupstatus(JObject.getString("LineupStatus"));

				System.out.println("Save to Playerstatspergame the Game with GameID: " + JObject.getInt("GameID")
						+ " and PlayerId: " + JObject.getInt("PlayerID"));

				playerstatspergameService.savePlayerstatspergame(newPlayerstatspergame);

			}

//				}

			/** ---------Method Description--------- **/
			// - Compare Every player(a list with players which had played for than 25
			// minutes) for the specific gameid.
			// - The comparison is been accordingly with the position of each player
			DefenceMatchUp.calculateDefenceMatchUp(gameId);

			/** ---------Method Description--------- **/
			// - Create an entry in table "OpponentPlayerStatsPerGame" for every player for
			// the specific gameId
			// - If the player have played less than 25 minutes create an null entry to the
			// table
			// - If the player have played more than 25 minutes create an entry which
			// calculate the average of
			// his opponents with the same position
			OpponentPlayerStatsPerGame.calulateOpponentPlayerStatsPerGame(gameId);

			Teamstatsbypositionpergame.calculateTeamstatsbypositionpergame(gameId);

			/** ---------Calculate the process time for each game--------- **/
			long timeEnd = System.currentTimeMillis();
			System.out.println("Total time process time of match with gameId " + gameId + " : "
					+ (timeEnd - timeNow) / 1000 + " sec");

		}
	}
}
