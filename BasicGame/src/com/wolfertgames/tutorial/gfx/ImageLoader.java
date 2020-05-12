package com.wolfertgames.tutorial.gfx;

import java.awt.image.*;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {

	public static BufferedImage loadImage(String path) {
		try {
			return ImageIO.read(ImageLoader.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Failed to load image " + path);
			System.exit(1);
			return null;
		}
	}

}
