package com.nbaproject.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.nbaproject.entities.Team;

public class JsonNBATeams {

	public static ArrayList<Team> JsonNBATeamsRequest() throws IOException {

		ArrayList<Team> teamList = new ArrayList<Team>();
		URL url = null;
		try {

			url = new URL("https://api.sportsdata.io/v3/nba/scores/json/AllTeams?key=9d0dcf6acaa04131a5d9d747ec8d7825");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
		httpURLConnection.setRequestMethod("GET");

//		httpURLConnection.setRequestProperty("Content-Type", "application/json");

		if (httpURLConnection.getResponseCode() != 200) {
			System.out.println("The JsonNBATeamsRequest failed");
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
				Team newTeam = new Team();
				
				JSONObject JObject = JArray.getJSONObject(i);
				System.out.println(JObject.getString("Key"));
				newTeam.setTeamid(JObject.getInt("TeamID"));
				newTeam.setTeamkey(JObject.getString("Key"));
				newTeam.setCity(JObject.getString("City"));
				newTeam.setConference(JObject.getString("Conference"));
				newTeam.setDivision(JObject.getString("Division"));
				newTeam.setTeamname(JObject.getString("Name"));
				newTeam.setWikipedialogourl(JObject.getString("WikipediaLogoUrl"));
				
				teamList.add(newTeam);
			}
			
		}

		httpURLConnection.disconnect();
		System.out.println(teamList.isEmpty());
		return teamList;

	}

}
