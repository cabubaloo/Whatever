package com.wolfertgames.mj54.commands;

import com.wolfertgames.tutorial.utils.Utilities;

public class Subject extends Word {
	
	private String id;
	private String[] otherNames;
	
	public Subject(String id, String[] otherNames, boolean unlocked) {
		super(id, otherNames, unlocked);
	}
	
	public Subject(String id, String[] otherNames) {
		super(id, otherNames);
	}
	
}
