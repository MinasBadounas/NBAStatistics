package com.nbaproject.utils.checker;

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
import com.nbaproject.service.player.PlayerService;
import com.nbaproject.utils.staticInitializer.AppconfigServiceStaticInitializer;
import com.nbaproject.utils.staticInitializer.PlayerServiceStaticInitializer;
import com.nbaproject.utils.staticInitializer.TeamServiceStaticInitializer;

public class CheckPlayer {

	public static void CheckPlayerInDB(int playerid) throws IOException {
		
		/**---------Method Description---------**/
		// - Check the player if exists in Database: 
	    // 		* If not then create an entry with TeamID(null) 
		// 		* Else nothing

		int playerId = PlayerServiceStaticInitializer.findPlayerById(playerid);

		if (playerId == 0) {
			URL url = null;
			try {

				url = new URL("https://api.sportsdata.io/v3/nba/scores/json/Player/" + playerid
						+ "?key="+AppconfigServiceStaticInitializer.getKeyValuefromAppconfig("sportsdataio.key"));
			} catch (MalformedURLException e) {

				e.printStackTrace();
			}

			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.setRequestMethod("GET");

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

				JSONObject JObject = new JSONObject(response.toString());

				Player newPlayer = new Player();

				newPlayer.setPlayerid(JObject.getInt("PlayerID"));
				newPlayer.setFirstname(JObject.getString("FirstName"));
				newPlayer.setLastname(JObject.getString("LastName"));
				newPlayer.setPosition(JObject.getString("Position"));
				newPlayer.setPositionCategory(JObject.getString("PositionCategory"));
				//set TeamID ==33 (null) by default
				newPlayer.setTeam(TeamServiceStaticInitializer.findByIdNQ(33));
				newPlayer.setPhotoUrl(JObject.getString("PhotoUrl"));


				PlayerServiceStaticInitializer.addPlayer(newPlayer);
				System.out.println("New player added with ID: "+JObject.getInt("PlayerID"));
			}

			httpURLConnection.disconnect();

		} else {
			System.out.println("CheckPlayerInDB: The player already exists");
		}
	}

}
