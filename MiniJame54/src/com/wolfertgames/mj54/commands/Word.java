package com.wolfertgames.mj54.commands;

import com.wolfertgames.tutorial.utils.Utilities;

public abstract class Word {
	
	protected String id;
	protected String[] otherNames;
	
	protected boolean unlocked;
	
	public Word(String id, String[] otherNames, boolean unlocked) {
		this.id = id;
		this.otherNames = otherNames;
		this.unlocked = unlocked;
	}
	
	public Word(String id, String[] otherNames) {
		this.id = id;
		this.otherNames = otherNames;
		this.unlocked = true;
	}
	
	public boolean knownAs(String n) {
		return getId().contentEquals(n) || Utilities.containsString(otherNames, n);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String[] getOtherNames() {
		return otherNames;
	}

	public void setOtherNames(String[] otherNames) {
		this.otherNames = otherNames;
	}

	public boolean isUnlocked() {
		return unlocked;
	}

	public void setUnlocked(boolean unlocked) {
		this.unlocked = unlocked;
	}
	
	
}
