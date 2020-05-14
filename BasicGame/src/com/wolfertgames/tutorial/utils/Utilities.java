package com.wolfertgames.tutorial.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Utilities {
	
	//Reads in a file as a continuous string
	public static String loadFileAsString(String path) {
		StringBuilder builder = new StringBuilder();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line;
			while ((line = br.readLine()) != null) {
				builder.append(line + "\n");
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(3);
			return null;
		}
		
		return builder.toString();
		
	}
	
	public static int parseInt(String number) {
		try {
			return Integer.parseInt(number);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
}
