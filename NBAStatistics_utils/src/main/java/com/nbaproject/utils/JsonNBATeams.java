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
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.nbaproject.entities.Team;
import com.nbaproject.utils.staticInitializer.AppconfigServiceStaticInitializer;

public class JsonNBATeams {

	public static ArrayList<Team> JsonNBATeamsRequest() throws IOException {

		ArrayList<Team> teamList = new ArrayList<Team>();

		String url = "https://api.sportsdata.io/v3/nba/scores/json/AllTeams?key="
				+ AppconfigServiceStaticInitializer.getKeyValuefromAppconfig("sportsdataio.key");

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<Object[]> response = restTemplate.getForEntity(url, Object[].class);

		JSONArray JArray = new JSONArray(response.getBody());

		for (int i = 0; i < JArray.length(); i++) {
			Team newTeam = new Team();

			JSONObject JObject = JArray.getJSONObject(i);

			newTeam.setTeamid(JObject.getInt("TeamID"));
			newTeam.setTeamkey(JObject.getString("Key"));
			newTeam.setCity(JObject.getString("City"));
			newTeam.setConference(JObject.getString("Conference"));
			newTeam.setDivision(JObject.getString("Division"));
			newTeam.setTeamname(JObject.getString("Name"));
			newTeam.setWikipedialogourl(JObject.getString("WikipediaLogoUrl"));

			teamList.add(newTeam);
		}

		return teamList;

	}

}
