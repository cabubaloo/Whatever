package com.wolfertgames.mj54.commands;

import com.wolfertgames.tutorial.utils.Utilities;

public class Verb extends Word {
	
	private String id;
	private String[] otherNames;
	private String[] subjects;
	
	public Verb(String id, String[] otherNames, String[] subjects, Boolean unlocked) {
		super(id, otherNames, unlocked);
		this.subjects = subjects;
	}
	
	public Verb(String id, String[] otherNames, String[] subjects) {
		super(id, otherNames, true);
		this.subjects = subjects;
	}
	
	public boolean contains(String s) {
		return Utilities.containsString(subjects, s);
	}
	
}
