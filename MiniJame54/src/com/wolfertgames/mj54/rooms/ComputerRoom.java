package com.wolfertgames.mj54.rooms;

import com.wolfertgames.mj54.states.TextHandler;

public class ComputerRoom extends Room {

	public Room logoutRoom;
	
	public ComputerRoom(String description) {
		super(description);
	}

	public Room getLogoutRoom() {
		return logoutRoom;
	}

	public void setLogoutRoom(Room logoutRoom) {
		this.logoutRoom = logoutRoom;
	}

}
