package com.nbaproject.utils.async;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.nbaproject.entities.Boxscore;
import com.nbaproject.entities.Playerstatspergame;
import com.nbaproject.entities.PlayerstatspergamePK;
import com.nbaproject.service.boxscore.BoxscoreService;
import com.nbaproject.service.playerstatspergame.PlayerstatspergameService;
import com.nbaproject.utils.Converters;
import com.nbaproject.utils.checker.CheckPlayer;
import com.nbaproject.utils.staticInitializer.StaticContextInitializer;

@Configuration
@EnableScheduling
@EnableAsync
public class UpdatePlayerGames {

	@Autowired
	private BoxscoreService boxscoreService;

	@Autowired
	private PlayerstatspergameService playerstatspergameService;

	@Async("threadPoolTaskExecutor")
	@Scheduled(cron = "0 52 23 * * ?")
	public void UpdatePlayerGamesStas() throws IOException {

		int maxGameId = playerstatspergameService.maxGameIdInPlayerStatsPerGame();
		ArrayList<Integer> gameIdList = boxscoreService.findAllGameIdBoxscore();
		System.out.println("Run 'UpadatePlayerGamesStas()'");

		for (Integer gameId : gameIdList) {
			System.out.println(gameId);

			if (gameId > maxGameId) {

				URL url = null;
				try {

					url = new URL("https://api.sportsdata.io/v3/nba/stats/json/BoxScore/" + gameId
							+ "?key=9d0dcf6acaa04131a5d9d747ec8d7825");
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
				httpURLConnection.setRequestMethod("GET");

				if (httpURLConnection.getResponseCode() != 200) {
					System.out.println("The UpdatePlayerGamesStas failed");
				} else {

					BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
					String inputLine;
					StringBuffer response = new StringBuffer();
					while ((inputLine = in.readLine()) != null) {
						response.append(inputLine);
					}
					in.close();

					JSONObject JArray = new JSONObject(response.toString());
					JSONArray JArray1=JArray.getJSONArray("PlayerGames");

					for (int i = 0; i < JArray.length(); i++) {

						JSONObject JObject = JArray1.getJSONObject(i);
						Playerstatspergame newPlayerstatspergame = new Playerstatspergame();
						CheckPlayer.CheckPlayerInDB(JObject.getInt("PlayerID"));
						PlayerstatspergamePK newPlayerstatspergamePK = new PlayerstatspergamePK(JObject.getInt("GameID"),JObject.getInt("PlayerID"));

						newPlayerstatspergame.setId(newPlayerstatspergamePK);
						newPlayerstatspergame.setTeamid(JObject.getInt("TeamID"));
						newPlayerstatspergame.setOpponentid(JObject.getInt("OpponentID"));
						newPlayerstatspergame.setSeasontype(JObject.getInt("SeasonType"));
						newPlayerstatspergame.setSeason(JObject.getInt("Season"));
						newPlayerstatspergame.setInjurestatus(JObject.getString("InjuryStatus"));
						newPlayerstatspergame.setFanduelposition(JObject.getString("FanDuelPosition"));
						newPlayerstatspergame.setDraftkingsposition(JObject.getString("DraftKingsPosition"));
						newPlayerstatspergame.setStarted(JObject.getInt("Started"));
						

						if(JObject.getString("OpponentRank")=="null") {
							System.out.println("Opponent Rankkkkkk");
							newPlayerstatspergame.setOpponentrank(0);
						}else {
							newPlayerstatspergame.setOpponentrank(JObject.getInt("OpponentRank"));
						}
						
						if(JObject.getString("OpponentPositionRank")=="null") {
							newPlayerstatspergame.setOpponentpositionrank(0);
						}else {
							newPlayerstatspergame.setOpponentpositionrank(JObject.getInt("OpponentPositionRank"));
						}

						newPlayerstatspergame.setGames(JObject.getInt("Games"));
						newPlayerstatspergame.setMinutes(JObject.getInt("Minutes"));
						newPlayerstatspergame.setSeconds(JObject.getInt("Seconds"));
						newPlayerstatspergame.setFieldgoalsmade(JObject.getDouble("FieldGoalsMade"));
						newPlayerstatspergame.setFieldgoalsattempted(JObject.getDouble("FieldGoalsAttempted"));
						newPlayerstatspergame.setFieldgoalspercentage(JObject.getDouble("FieldGoalsPercentage"));
						newPlayerstatspergame.setEffectivefieldgoalspercentage(JObject.getDouble("EffectiveFieldGoalsPercentage"));
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
						
						
						System.out.println("Save to Playerstatspergame the Game with GameID: " + JObject.getInt("GameID"));

						playerstatspergameService.savePlayerstatspergame(newPlayerstatspergame);

					}

				}

				httpURLConnection.disconnect();
			}
		}
	}
}
