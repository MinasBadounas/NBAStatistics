package com.nbaproject.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonNBATeams {

	public static void JsonNBATeamsRequest() throws IOException {

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
			
			JSONArray myResponse = new JSONArray(response.toString());
			System.out.println(myResponse.toString());
			
		}

		httpURLConnection.disconnect();

	}

}
