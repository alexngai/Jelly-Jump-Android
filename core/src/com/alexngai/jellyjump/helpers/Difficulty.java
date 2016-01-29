package com.alexngai.jellyjump.helpers;

public class Difficulty {
	
	public static int increaseLevel(int curLevel, float depth){
		if (curLevel == 1 && depth > 100){
			return 2;
		}
		else if (curLevel == 2 && depth > 200){
			return 3;
		}
		else if (curLevel == 3 && depth > 350){
			return 4;
		}
		else if (curLevel == 4 && depth > 500){
			return 5;
		}
		else if (curLevel == 5 && depth > 650){
			return 6;
		}
		else if (curLevel == 6 && depth > 800){
			return 7;
		}
		
		return -1;
	}
}
