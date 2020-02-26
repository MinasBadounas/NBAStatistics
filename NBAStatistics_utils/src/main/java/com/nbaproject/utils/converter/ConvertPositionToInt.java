package com.nbaproject.utils.converter;

public class ConvertPositionToInt {

	public static int PositionToInt(String playerPosition) {

		switch (playerPosition) {
		case "PG/SF":
			return 1;
		case "PG/SG":
			return 2;
		case "PG":
			return 3;
		case "SG/SF":
			return 4;
		case "SG":
			return 5;
		case "SF/PF":
			return 6;
		case "SF":
			return 7;
		case "PF/C":
			return 8;
		case "PF":
			return 9;
		case "C":
			return 10;
		default:
			return 0;
		}

	}
}
