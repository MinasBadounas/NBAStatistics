package com.nbaproject.utils.tools;

public class EditPath {

	public static int EditPathToGetGameID(String url) {
		
		String[] splitList= url.split("=");
		int gameid= Integer.parseInt(splitList[1]);
		
		return gameid;
	}
}
