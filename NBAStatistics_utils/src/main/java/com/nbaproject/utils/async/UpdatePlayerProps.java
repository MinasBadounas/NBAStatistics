package com.nbaproject.utils.async;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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

import com.nbaproject.entities.Playerprop;
import com.nbaproject.entities.PlayerpropPK;
import com.nbaproject.entities.Playerstatspergame;
import com.nbaproject.service.appconfig.AppconfigService;
import com.nbaproject.service.boxscore.BoxscoreService;
import com.nbaproject.service.player.PlayerService;
import com.nbaproject.service.playerprop.PlayerpropService;
import com.nbaproject.service.playerstatspergame.PlayerstatspergameService;
import com.nbaproject.utils.converter.ConvertPlayerpropDescriptionToInt;

@Configuration
@EnableScheduling
@EnableAsync
public class UpdatePlayerProps {

	@Autowired
	private PlayerpropService playerpropService;

	@Autowired
	private AppconfigService appconfigService;

	@Autowired
	private PlayerstatspergameService playerstatspergameService; 
	
	@Autowired
	private PlayerService playerService;
	
	@Autowired
	private BoxscoreService boxscoreService;

	@Async("threadPoolTaskExecutor")
	@Scheduled(cron = "0 26 00 * * ?")
	public void UpdatePlayerpropSchedule() {

		System.out.println("Run 'UpdateGamesSchedule()'");
		long timeNow = System.currentTimeMillis();

		List<Playerstatspergame> playerstatspergameList = playerstatspergameService.findAll();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		for (int i = 0; i < playerstatspergameList.size(); i++) {

			if(playerstatspergameList.get(i).getBoxscore().getGameid()==13905) {
				
			String url = "https://api.sportsdata.io/v3/nba/odds/json/PlayerPropsByPlayerID/"
					+ sdf.format(playerstatspergameList.get(i).getBoxscore().getDatetime()) + "/"
					+ playerstatspergameList.get(i).getPlayer().getPlayerid() + "?key="
					+ appconfigService.findKeyValue("sportsdataio.key");

			RestTemplate restTemplate = new RestTemplate();
			
			ResponseEntity<Object[]> response = restTemplate.getForEntity(url, Object[].class);
			JSONArray JArray = new JSONArray(response.getBody());
			
			for (int y = 0; y < JArray.length(); y++) {
			
				Playerprop newPlayerprop = new Playerprop();

				JSONObject JObject = JArray.getJSONObject(y);
				
				PlayerpropPK newPlayerpropPK = new PlayerpropPK(JObject.getInt("PlayerID"),JObject.getInt("GameID"),ConvertPlayerpropDescriptionToInt.PlayerpropDescriptionToInt(JObject.getString("Description")));
				
				newPlayerprop.setId(newPlayerpropPK);
				newPlayerprop.setBoxscore(boxscoreService.findBoxscorebyGameId(JObject.getInt("GameID")));
				try {
					newPlayerprop.setDatetime(sdf2.parse(JObject.getString("DateTime").replace("T", " ")));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				newPlayerprop.setBetresult(JObject.getString("BetResult"));
				newPlayerprop.setOverpayout((double)100/Math.abs(JObject.getInt("OverPayout"))+1);
				newPlayerprop.setOverunder(JObject.getDouble("OverUnder"));
				newPlayerprop.setPlayer(playerService.findPlayerByPlayerId(JObject.getInt("PlayerID")));
				newPlayerprop.setStatresult(JObject.getDouble("StatResult"));
				newPlayerprop.setUnderpayout((double)100/Math.abs(JObject.getInt("UnderPayout"))+1);
				
				System.out.println("Save to Playerprop the Playerprop with GameID: " + newPlayerprop.getBoxscore().getGameid()
				+ " and PlayerId: " + newPlayerprop.getPlayer().getPlayerid() +" and Description: " + JObject.getString("Description") );

				playerpropService.savePlayerprops(newPlayerprop);
			}
			
			}
		}
		

		/** ---------Calculate the process time for each game--------- **/
		long timeEnd = System.currentTimeMillis();
		System.out.println(
				"Total time process time of'UpdatePlayerpropSchedule()' : " + (timeEnd - timeNow) / 1000 + " sec");

	}

}
