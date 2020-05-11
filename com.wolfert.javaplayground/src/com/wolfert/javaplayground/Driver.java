package com.wolfert.javaplayground;

import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Ready to echo...");
		String echo = in.nextLine();
		System.out.println(echo);
		in.close();
	}

}
