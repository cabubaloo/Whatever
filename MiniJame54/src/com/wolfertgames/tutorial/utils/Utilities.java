package com.wolfertgames.tutorial.utils;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.wolfertgames.mj54.display.gfx.ImageLoader;
import com.wolfertgames.mj54.sound.AudioPlayer;

public class Utilities {
	
	//Reads in a file as a continuous string
	public static String loadFileAsString(String path) {
		String contents;
		
		try {
			
			InputStream inputStream = Utilities.class.getResourceAsStream(path);
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			contents = reader.lines().collect(Collectors.joining(System.lineSeparator()));
			reader.close();
			/*File file = new File(Utilities.class.getResource(path).getFile());
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			while ((line = br.readLine()) != null) {
				builder.append(line + "\n");
			}
			br.close();*/
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(3);
			return null;
		}
		
		return contents;
		
	}
	
	public static int parseInt(String number) {
		try {
			return Integer.parseInt(number);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public static boolean containsString(String[] list, String s) {
		for (int i = 0; i < list.length; i++) {
			if (s.equals(list[i])) return true;
		}
		return false;
	}
	
	public static Font loadFont(String path, float size) {
		try {
		    //create the font to use. Specify the size!
			InputStream fontFile = new BufferedInputStream(Utilities.class.getResourceAsStream(path));
		    Font customFont = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(size);
		    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		    //register the font
		    ge.registerFont(customFont);
		    return customFont;
		} catch (IOException e) {
		    e.printStackTrace();
		    return null;
		} catch(FontFormatException e) {
		    e.printStackTrace();
		    return null;
		}
	}
	
	public static AudioInputStream loadSound(String audioPath) {
			try {
				InputStream audioInput = AudioPlayer.class.getResourceAsStream(audioPath);
				BufferedInputStream audioBuffer = new BufferedInputStream(audioInput);
				AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioBuffer);;
				return audioStream;
			} catch (UnsupportedAudioFileException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
	}
	
}
