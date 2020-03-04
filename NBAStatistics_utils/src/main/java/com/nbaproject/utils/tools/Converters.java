package com.nbaproject.utils.tools;

public class Converters {
	
	public static byte ConvertBooleanToByte(Boolean truefalse) {
		
		byte byteTrueFlase;
		
		if(truefalse){
			byteTrueFlase=1;
		}
		else {
			byteTrueFlase=0;
		}		
		
		return byteTrueFlase;
		
	}

}
