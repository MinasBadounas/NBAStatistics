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

import com.nbaproject.entities.Player;
import com.nbaproject.entities.Team;
import com.nbaproject.service.team.TeamService;

public class JsonNBAPlayers {

	public static ArrayList<Player> JsonNBAPlayersRequest() throws IOException {
		

		ArrayList<Player> playerList = new ArrayList<Player>();
		URL url = null;
		try {

			url = new URL("https://api.sportsdata.io/v3/nba/scores/json/Players?key=9d0dcf6acaa04131a5d9d747ec8d7825");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
		httpURLConnection.setRequestMethod("GET");

//		httpURLConnection.setRequestProperty("Content-Type", "application/json");

		if (httpURLConnection.getResponseCode() != 200) {
			System.out.println("The JsonNBAPlayersRequest failed");
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
				Player newPlayer = new Player();

				JSONObject JObject = JArray.getJSONObject(i);
				System.out.println(JObject.getString("FirstName"));
				System.out.println(JObject.getInt("PlayerID"));
				System.out.println(JObject.getString("LastName"));
				System.out.println(JObject.getString("Position"));
				System.out.println(JObject.getString("PositionCategory"));
				System.out.println(StaticContextInitializer.findByIdNQ(JObject.getInt("TeamID")));
				System.out.println(JObject.getString("PhotoUrl"));
				

				newPlayer.setPlayerid(JObject.getInt("PlayerID"));
				newPlayer.setFirstname(JObject.getString("FirstName"));
				newPlayer.setLastname(JObject.getString("LastName"));
				newPlayer.setPosition(JObject.getString("Position"));
				newPlayer.setPositionCategory(JObject.getString("PositionCategory"));
				newPlayer.setTeam(StaticContextInitializer.findByIdNQ(JObject.getInt("TeamID")));
				newPlayer.setPhotoUrl(JObject.getString("PhotoUrl"));

				playerList.add(newPlayer);
			}

		}

		httpURLConnection.disconnect();
		System.out.println(playerList.isEmpty());
		return playerList;

	}

}
