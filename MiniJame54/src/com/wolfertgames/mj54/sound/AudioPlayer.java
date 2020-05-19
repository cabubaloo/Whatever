package com.wolfertgames.mj54.sound;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.wolfertgames.tutorial.utils.Utilities;

public class AudioPlayer implements LineListener, Runnable {
	
	//Once object is created, it will generate thread, play sound, and then kill its thread.
	
	private boolean playCompleted;
	private boolean loop = false;
	private String audioPath;
	
	//Thread Management
	private Thread thread;
	public boolean running;
	
	public AudioPlayer(String audioPath) {
		playCompleted = false;
		this.audioPath = audioPath;
		start();
	}
	
	public AudioPlayer(String audioPath, boolean loop) {
		playCompleted = false;
		this.audioPath = audioPath;
		this.loop = loop;
		start();
	}
	
	public void playSound() {
		try {
			AudioInputStream audioStream = Utilities.loadSound(audioPath);
			AudioFormat format = audioStream.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			Clip audioClip = (Clip) AudioSystem.getLine(info);
			audioClip.addLineListener(this);
			audioClip.open(audioStream);
			if (loop) audioClip.loop(Clip.LOOP_CONTINUOUSLY);
			else audioClip.start();
			
			while (!playCompleted) {
	            // wait for the playback completes
	            try {
	            	Thread.sleep(1000);
	            } catch (InterruptedException ex) {
	                ex.printStackTrace();
	            }
			}
			
			audioClip.close();
			audioStream.close();
			
		} catch (LineUnavailableException ex) {
            System.out.println("Audio line for playing back is unavailable.");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Error playing the audio file.");
            ex.printStackTrace();
        }
	}
	
	@Override
    public void update(LineEvent event) {
        LineEvent.Type type = event.getType();
         
        if (type == LineEvent.Type.START) {
            System.out.println("Playback started.");
             
        } else if (type == LineEvent.Type.STOP || type == LineEvent.Type.CLOSE) {
            playCompleted = true;
            System.out.println("Playback completed.");
        }
 
    }

	@Override
	public void run() {
		System.out.println("Generated Thread!");
		playSound();
		thread.interrupt();
	}
	
	public synchronized void start() {
		if (running) return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	//Kills the Games's thread
	public synchronized void stop() {
		if (!running) return;
		running = false;
		thread.interrupt();
		System.out.println("Closed Thread!");
	}
} 

