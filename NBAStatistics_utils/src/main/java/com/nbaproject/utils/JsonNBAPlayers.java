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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.nbaproject.entities.Player;
import com.nbaproject.entities.Team;
import com.nbaproject.service.team.TeamService;
import com.nbaproject.utils.staticInitializer.AppconfigServiceStaticInitializer;
import com.nbaproject.utils.staticInitializer.TeamServiceStaticInitializer;

public class JsonNBAPlayers {

	public static ArrayList<Player> JsonNBAPlayersRequest() throws IOException {

		ArrayList<Player> playerList = new ArrayList<Player>();

		String url = "https://api.sportsdata.io/v3/nba/scores/json/Players?key="
				+ AppconfigServiceStaticInitializer.getKeyValuefromAppconfig("sportsdataio.key");

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<Object[]> response = restTemplate.getForEntity(url, Object[].class);

		JSONArray JArray = new JSONArray(response.getBody());

		for (int i = 0; i < JArray.length(); i++) {
			Player newPlayer = new Player();

			JSONObject JObject = JArray.getJSONObject(i);

			newPlayer.setPlayerid(JObject.getInt("PlayerID"));
			newPlayer.setFirstname(JObject.getString("FirstName"));
			newPlayer.setLastname(JObject.getString("LastName"));
			newPlayer.setPosition(JObject.getString("Position"));
			newPlayer.setPositionCategory(JObject.getString("PositionCategory"));
			newPlayer.setTeam(TeamServiceStaticInitializer.findByIdNQ(JObject.getInt("TeamID")));
			newPlayer.setPhotoUrl(JObject.getString("PhotoUrl"));

			playerList.add(newPlayer);
		}

		return playerList;

	}

}
