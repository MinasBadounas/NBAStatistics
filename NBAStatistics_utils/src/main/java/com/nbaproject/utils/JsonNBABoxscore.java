package com.nbaproject.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.nbaproject.entities.Boxscore;
import com.nbaproject.utils.staticInitializer.AppconfigServiceStaticInitializer;
import com.nbaproject.utils.staticInitializer.TeamServiceStaticInitializer;
import com.nbaproject.utils.tools.Converters;

public class JsonNBABoxscore {

	public static ArrayList<Boxscore> JsonNBABoxscoreRequest(LocalDate localDate) throws IOException {

		ArrayList<Boxscore> boxscoreList = new ArrayList<Boxscore>();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String url = "https://api.sportsdata.io/v3/nba/scores/json/GamesByDate/" + localDate + "?key="
				+ AppconfigServiceStaticInitializer.getKeyValuefromAppconfig("sportsdataio.key");

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
			newBoxscore.setTeam1(TeamServiceStaticInitializer.findByIdNQ(JObject.getInt("AwayTeamID")));
			newBoxscore.setTeam2(TeamServiceStaticInitializer.findByIdNQ(JObject.getInt("HomeTeamID")));
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

			newBoxscore.setIslosed(Converters.ConvertBooleanToByte(JObject.getBoolean("IsClosed")));

			boxscoreList.add(newBoxscore);

		}

		return boxscoreList;
	}

}
