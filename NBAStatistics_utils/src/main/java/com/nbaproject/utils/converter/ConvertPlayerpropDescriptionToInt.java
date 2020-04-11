package com.nbaproject.utils.converter;

public class ConvertPlayerpropDescriptionToInt {

	public static int PlayerpropDescriptionToInt(String playerpropDescription) {

		switch (playerpropDescription) {
		case "Fantasy Points":
			return 1;
		case "Points":
			return 2;
		case "Field Goals Made":
			return 3;
		case "Field Goals Attempted":
			return 4;
		case "Three Pointers Made":
			return 5;
		case "Three Pointers Attempted":
			return 6;
		case "Assists":
			return 7;
		case "Steals":
			return 8;
		case "Rebounds":
			return 9;
		case "Points + Rebounds + Assists":
			return 10;
		case "Points + Rebounds":
			return 11;
		case "Points + Assists":
			return 12;
		default:
			return 0;
		}

	}
	
}
