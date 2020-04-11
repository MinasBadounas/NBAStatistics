package com.nbaproject.utils.async;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import com.nbaproject.entities.Boxscore;
import com.nbaproject.service.appconfig.AppconfigService;
import com.nbaproject.service.boxscore.BoxscoreService;
import com.nbaproject.service.team.TeamService;
import com.nbaproject.utils.tools.Converters;

@Configuration
@EnableScheduling
@EnableAsync
public class UpdateGames {

	@Autowired
	private BoxscoreService boxscoreService;

	@Autowired
	private AppconfigService appconfigService;
	
	@Autowired
	private TeamService teamService;

	@Async("threadPoolTaskExecutor")
	@Scheduled(cron = "0 54 23 * * ?")
	public void UpdateGamesSchedule() throws IOException {

		System.out.println("Run 'UpdateGamesSchedule()'");
		long timeNow = System.currentTimeMillis();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String url = "https://api.sportsdata.io/v3/nba/scores/json/Games/" + appconfigService.findKeyValue("season")
				+ "?key=" + appconfigService.findKeyValue("sportsdataio.key");

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<Object[]> response = restTemplate.getForEntity(url, Object[].class);

		JSONArray JArray = new JSONArray(response.getBody());

		for (int i = 0; i < JArray.length(); i++) {
			Boxscore newBoxscore = new Boxscore();

			JSONObject JObject = JArray.getJSONObject(i);

			newBoxscore.setGameid(JObject.getInt("GameID"));
			newBoxscore.setSeason(JObject.getInt("Season"));
			newBoxscore.setStatus(JObject.getString("Status"));
			try {
				newBoxscore.setDatetime(sdf.parse(JObject.getString("DateTime").replace("T", " ")));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			newBoxscore.setAwayteam(JObject.getString("AwayTeam"));
			newBoxscore.setHometeam(JObject.getString("HomeTeam"));
			newBoxscore.setTeam1(teamService.findTeamById(JObject.getInt("AwayTeamID")));
			newBoxscore.setTeam2(teamService.findTeamById(JObject.getInt("HomeTeamID")));
			if (JObject.getString("Status").contentEquals("Final")
					|| JObject.getString("Status").contentEquals("F/OT")) {
				newBoxscore.setAwayteamscore(JObject.getInt("AwayTeamScore"));
				newBoxscore.setHometeamscore(JObject.getInt("HomeTeamScore"));
				newBoxscore.setPointspread(JObject.getInt("PointSpread"));
				newBoxscore.setOverunder(JObject.getInt("OverUnder"));
			} else {
				newBoxscore.setAwayteamscore(0);
				newBoxscore.setHometeamscore(0);
				newBoxscore.setPointspread(0);
				newBoxscore.setOverunder(0);
			}
			newBoxscore.setIsclosed(Converters.ConvertBooleanToByte(JObject.getBoolean("IsClosed")));
			System.out.println("Save to boxscore the Game with GameID: " + JObject.getInt("GameID"));

			boxscoreService.saveBoxscore(newBoxscore);

		}

		/** ---------Calculate the process time for each game--------- **/
		long timeEnd = System.currentTimeMillis();
		System.out
				.println("Total time process time of'UpdateGamesSchedule()' : " + (timeEnd - timeNow) / 1000 + " sec");

	}

}
