package com.wolfertgames.mj54.rooms;

import com.wolfertgames.mj54.states.TextHandler;

public class Room {
	
	public String name;
	public Room leftRoom, rightRoom;
	
	public Room(String name) {
		this.name = name;
	}
	
	public void printOptions() {
		if (goRight() != null) TextHandler.logUser("The " + goRight().getName() + " is to your right.");
		if (goLeft() != null) TextHandler.logUser("The " + goLeft().getName() + " is to your left.");
	}
	
	public Room goRight() {
		return rightRoom;
	}
	
	public Room goLeft() {
		return leftRoom;
	}

	public void setLeftRoom(Room leftRoom) {
		this.leftRoom = leftRoom;
	}

	public void setRightRoom(Room rightRoom) {
		this.rightRoom = rightRoom;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
