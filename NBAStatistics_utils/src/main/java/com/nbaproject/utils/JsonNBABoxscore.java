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

import com.nbaproject.entities.Boxscore;
import com.nbaproject.utils.staticInitializer.StaticContextInitializer;

public class JsonNBABoxscore {

	public static ArrayList<Boxscore> JsonNBABoxscoreRequest(LocalDate localDate ) throws IOException {

		ArrayList<Boxscore> boxscoreList = new ArrayList<Boxscore>();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
		
		URL url = null;
		try {

			url = new URL(
					"https://api.sportsdata.io/v3/nba/scores/json/GamesByDate/"+localDate+"?key=9d0dcf6acaa04131a5d9d747ec8d7825");		
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
		httpURLConnection.setRequestMethod("GET");

//		httpURLConnection.setRequestProperty("Content-Type", "application/json");

		if (httpURLConnection.getResponseCode() != 200) {
			System.out.println("The JsonNBABoxscoreRequest failed");
		} else {

			BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			JSONArray JArray = new JSONArray(response.toString());

			for (int i = 0; i < JArray.length(); i++) {
				Boxscore newBoxscore = new Boxscore();

			
				JSONObject JObject = JArray.getJSONObject(i);
			
				newBoxscore.setGameid(JObject.getInt("GameID"));
				newBoxscore.setSeason(JObject.getInt("Season"));
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
				newBoxscore.setTeam1(StaticContextInitializer.findByIdNQ(JObject.getInt("AwayTeamID")));
				newBoxscore.setTeam2(StaticContextInitializer.findByIdNQ(JObject.getInt("HomeTeamID")));
				newBoxscore.setAwayteamscore(JObject.getInt("AwayTeamScore"));
				newBoxscore.setHometeamscore(JObject.getInt("HomeTeamScore"));
				newBoxscore.setPointspread(JObject.getInt("PointSpread"));
				newBoxscore.setOverunder(JObject.getInt("OverUnder"));
				newBoxscore.setIslosed(Converters.ConvertBooleanToByte(JObject.getBoolean("IsClosed")));
				
				boxscoreList.add(newBoxscore);

			}

		}

		httpURLConnection.disconnect();

		return boxscoreList;
	}

}
