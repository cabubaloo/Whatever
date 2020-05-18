package com.wolfertgames.mj54.states;

import com.wolfertgames.gj54.entities.statics.ImageSEntity;
import com.wolfertgames.gj54.math.Vector2;
import com.wolfertgames.mj54.commands.Subject;
import com.wolfertgames.mj54.commands.Verb;
import com.wolfertgames.mj54.display.gfx.Assets;
import com.wolfertgames.mj54.rooms.ComputerRoom;
import com.wolfertgames.mj54.rooms.Room;

public class TextHandler {
	
	public static enum Response { WHERE, FAR, LACK, NOGO, HOW, NONEED}
	
	public static final Subject[] recognizedSubjects =  {
			new Subject("player", new String[] {"self", "me"}),
			new Subject("jam", new String[] {"spill"}),
			new Subject("computer", new String[] {"pc", "mac", "desktop", "machine"}),
			new Subject("rag", new String[] {"towel", "fabric"}),
			new Subject("breaker", new String[] {"panel"}),
			new Subject("painting", new String[] {"painting", "image"}),
			new Subject("safe", new String[] {"box"}),
			new Subject("code", new String[] {"paper", "secret"}),
			new Subject("flashlight", new String[] {"torch"}),
			new Subject("drawer", new String[] {"cupboard"}),
			new Subject("battery", new String[] {"cell", "d-cell"}),
			new Subject("book", new String[] {"tome", "bible"}),
			new Subject("key", new String[] {"brass", "unlocker"}),
			new Subject("man", new String[] {"npc", "person", "sans", "bear", "animal"}),
			new Subject("vault", new String[] {"door", "wall"}),
			new Subject("code", new String[] {"numbers", "combination", "password"}),
			new Subject("null", new String[] {"now"}),
			new Subject("right", new String[] {"r"}),
			new Subject("left", new String[] {"l"})
	};
	
	private static Verb loadGame = new Verb("load", new String[] {"bootup", "start", "run"}, new String[] {"player"}, false);
	private static Verb restart = new Verb("restart", new String[] {"respawn", "reload"}, new String[] {"null"}, false);
	private static Verb jump = new Verb("jump", new String[] {"bounce", "leap"}, new String[] {"null"}, false);
	private static Verb win = new Verb("win", new String[] {"end"}, new String[] {"null"}, false);
	
	public static final Verb[] recognizedVerbs =  {
			new Verb("restart", new String[] {"respawn", "reload"}, new String[] {"null"}),
			new Verb("grab", new String[] {"pickup", "snatch", "lift", "get"},
					new String[] {"book", "key", "battery", "flashlight", "code", "rag"}),
			new Verb("speak", new String[] {"talk", "yell"}, new String[] {"man"}),
			new Verb("enter", new String[] {"write", "submit", "spin"}, new String[] {"code"}),
			new Verb("wipe", new String[] {"clean"}, new String[] {"jam"}),
			new Verb("read", new String[] {"scan", "browse", "digest"}, new String[] {"book"}),
			new Verb("open", new String[] {"unlock"}, new String[] {"vault", "drawer", "safe"}),
			new Verb("clear", new String[] {"empty"}, new String[] {"null"}),
			new Verb("kill", new String[] {"murder", "end"}, new String[] {"player"}),
			new Verb("go", new String[] {"move", "walk"}, new String[] {"right", "left"}),
			new Verb("logout", new String[] {"move", "walk"}, new String[] {"null"}),
			new Verb("look", new String[] {"view", "observe"}, new String[] {"null"}),
			new Verb("inventory", new String[] {"items", "things", "i"}, new String[] {"null"}),
			new Verb("use", new String[] {"interact", "apply"},
					new String[] {"breaker", "battery", "flashlight", "computer", "key", "rag"}),
			new Verb("examine", new String[] {"inspect"}, new String[] {"breaker", "painting", "safe"}),
			loadGame, restart, jump, win
	};
	
	public static Room kitchen, backroom, garage;
	public static ComputerRoom computer;
	public static Room currentRoom;
	
	public static boolean openedDrawer = false;
	public static boolean hasBattery = false;
	public static boolean hasFlashlight = false;
	public static boolean loadedFlashlight = false;
	public static boolean flippedBreaker = false;
	public static boolean hasLight = false;
	public static boolean hasKey = false;
	public static boolean hasBook = false;
	public static boolean hasCode = false;
	public static boolean foundSafe = false;
	public static boolean safeOpen = false;
	public static boolean hasRag = false;
	public static boolean hasStickyRag = false;
	
	
	public static void testDriver() {
		currentRoom = computer;
		hasLight = true;
		hasCode = true;
		hasStickyRag = true;
		flippedBreaker = true;
		hasKey = true;
		loadGame.setUnlocked(true);
		restart.setUnlocked(true);
		jump.setUnlocked(true);
		win.setUnlocked(true);
	}
	
	public static void initTextGame() {
		kitchen = new Room("KITCHEN");
		
		backroom = new Room("BACKROOM");
		backroom.setLeftRoom(kitchen);
		
		garage = new Room("GARAGE");
		garage.setRightRoom(kitchen);
		
		computer = new ComputerRoom("COMPUTER");
		computer.setLogoutRoom(backroom);
		
		kitchen.setLeftRoom(garage);
		kitchen.setRightRoom(backroom);
	}
	
	public static void startTextGame() {
		initTextGame();
		currentRoom = kitchen;
		//testDriver(); //DELETE THIS LATER
		intro();
		announceRoom();
	}
	
	public static void announceRoom() {
		logUser("You entered the " + currentRoom.getName() + ".");
	}
	
	public static void intro() {
		logUser("Welcome.");
		logUser("Type \"help\" for guidance.");
	}
	
	public static void lookAround() {
		if (currentRoom == kitchen) {
			if (!openedDrawer) {
				logUser("You notice drawer slighty ajar.");
			} else if (!hasBattery){
				logUser("You see a D-Cell Battery inside the drawer.");
			}
			if (hasStickyRag) {
				logUser("The table is spotless");
			} else {
				logUser("Somebody spilled some jam on the table");
			}
		} else if (currentRoom == garage) {
			if (hasFlashlight) {
				logUser("You notice a breaker panel in the back.");
			} else {
				logUser("You see a flashlight on the workbench.");
				logUser("You also notice a breaker panel in the back.");
			}
		} else if (currentRoom == backroom) {
			if (hasLight) {
				logUser("There is a computer in the corner with");
				logUser("some cobwebs all over it.");
				if (foundSafe) {
					if (safeOpen) {
						logUser("There's a opened safe in the wall.");
					} else {
						logUser("There's a locked safe in the wall.");
					}
				} else {
					logUser("There's a strange painting on the wall.");
				}
			} else {
				logUser("It's too dark! You can't see anything.");
			}
		} else if (currentRoom == computer) {
			logUser("There is one file titled PLAYER.exe");
		}
		currentRoom.printOptions();
	}
	
	public static void logUser(String s) {
		GameState.console.addNewLine(s);
		GameState.console.updateRender();
		if (!GameState.console.onScreen()) {
			GameState.popup.popUp(5000, s);
		}
	}
	
	static void inventory() {
		logUser("Inventory:");
		if (loadedFlashlight) logUser("Flashlight");
		else {
			if (hasFlashlight) logUser("Flashlight (Dead)");
			if (hasBattery) logUser("Battery");
		}
		if (hasKey) logUser ("Key");
		if (hasBook) logUser ("Book");
		if (hasCode) logUser ("Combination");
		if (hasStickyRag) logUser("Jam-Soaked Rag");
		else if (hasRag) logUser("Rag");
	}
	
	public static void parseCommand(String command) {
		String[] tokens = command.split("\\s+");
		if (tokens.length < 1 || tokens.length > 2) {
			System.out.println("Not two words! " + tokens.length);
			badGrammer();
			return;
		}
		String verbWord = tokens[0];
		Verb verb = getVerb(verbWord);
		String subjectWord = (tokens.length == 1) ? "null" : tokens[1];
		Subject subject = getSubject(subjectWord);
		if (verb == null) {
			logUser("How does one \"" + verbWord + "\"?");
		} else if (subject == null) {
			logUser("What is a \"" + subjectWord + "\"?");
		} else if (!isGoodGrammer(verb, subject)) {
			logUser("Thats just silly!");
		} else {
			GameState.console.addNewLine("-->" + command);
			GameState.console.updateRender();
			runCommand(verb, subject, verbWord, subjectWord);
		}
	}
	
	private static boolean isGoodGrammer(Verb v, Subject s) {
		return v.contains(s.getId());
	}
	
	private static Verb getVerb(String verb) {
		for (int i = 0; i < recognizedVerbs.length; i++) {
			if (recognizedVerbs[i].knownAs(verb) && recognizedVerbs[i].isUnlocked())
				return recognizedVerbs[i];
		}
		return null;
	}
	
	private static Subject getSubject(String subject) {
		for (int i = 0; i < recognizedSubjects.length; i++) {
			if (recognizedSubjects[i].knownAs(subject) && recognizedSubjects[i].isUnlocked())
				return recognizedSubjects[i];
		}
		return null;
	}
	

	private static void runCommand(Verb verb, Subject subject, String verbWord, String subjectWord) {
		System.out.println("Running Command...");
		
		if (verb.getId().contentEquals("go")) {
			if (subject.getId().contentEquals("left")) {
				if (currentRoom.goLeft() != null) {
					currentRoom = currentRoom.goLeft();
					announceRoom();
				} else {
					logCommon(subjectWord, Response.NOGO);
				}
			} else if (subject.getId().contentEquals("right")) {
				if (currentRoom.goRight() != null) {
					currentRoom = currentRoom.goRight();
					announceRoom();
				} else {
					logCommon(subjectWord, Response.NOGO);
				}
			}
			
		} else if (verb.getId().contentEquals("win")) {
			GameState.win();
			
		} else if (verb.getId().contentEquals("look")) {
			lookAround();
			
		} else if (verb.getId().contentEquals("inventory")) {
			inventory();
			
		} else if (verb.getId().contentEquals("examine")) {
			if (subject.getId().contentEquals("breaker")) {
				if (flippedBreaker) {
					logUser("The power is now on!");
				} else {
					logUser("The main breaker has been tripped.");
				}
			} else if (subject.getId().contentEquals("painting")) {
				if (currentRoom == backroom && hasLight) {
					logUser("Theres a safe behind here!");
					foundSafe = true;
				} else {
					logCommon(subjectWord, Response.WHERE);
				}
			} else if (subject.getId().contentEquals("safe")) {
				examineSafe(subjectWord);
			}
			
		} else if (verb.getId().contentEquals("use")) {
			if (subject.getId().contentEquals("breaker")) {
				flipBreaker(subjectWord);
			} else if (subject.getId().contentEquals("battery")) {
				loadBattery(subjectWord);
			} else if (subject.getId().contentEquals("flashlight")) {
				useFlashlight(subjectWord);
			} else if (subject.getId().contentEquals("computer")) {
				useComputer(subjectWord);
			} else if (subject.getId().contentEquals("key")) {
				openSafe(subjectWord);
			} else if (subject.getId().contentEquals("rag")) {
				useRag(subjectWord);
			}
			
		} else if (verb.getId().contentEquals("kill")) {
			if (subject.getId().contentEquals("player")) {
				GameState.killPlayer();
			}
			
		} else if (verb.getId().contentEquals("restart")) {
			GameState.restart();
			
		} else if (verb.getId().contentEquals("logout")) {
			if (currentRoom == computer) {
				GameState.backToConsole();
				currentRoom = computer.getLogoutRoom();
				announceRoom();
			}
			
		} else if (verb.getId().contentEquals("load")) {
			if (subject.getId().contentEquals("player")) {
				if (currentRoom == computer) {
					GameState.loadPlayer();
					logUser("Player ready.");
					jump.setUnlocked(true);
				}
			}
			
		} else if (verb.getId().contentEquals("grab")) {
			if (subject.getId().contentEquals("key")) {
				grabKey(subjectWord);
			} else if (subject.getId().contentEquals("book")) {
				grabBook(subjectWord);
			} else if (subject.getId().contentEquals("battery")) {
				grabBattery(subjectWord);
			} else if (subject.getId().contentEquals("flashlight")) {
				grabFlashlight(subjectWord);
			} else if (subject.getId().contentEquals("code")) {
				grabCode(subjectWord);
			} else if (subject.getId().contentEquals("rag")) {
				grabRag(subjectWord);
			}
		
		} else if (verb.getId().contentEquals("enter")) {
			if (subject.getId().contentEquals("code")) {
				openVault(subjectWord);
			}
			
		} else if (verb.getId().contentEquals("wipe")) {
			if (subject.getId().contentEquals("jam")) {
				cleanJam(subjectWord);
			}
			
		} else if (verb.getId().contentEquals("speak")) {
			if (subject.getId().contentEquals("man")) {
				talkMan(subjectWord);
			}
			
		} else if (verb.getId().contentEquals("read")) {
			if (subject.getId().contentEquals("book")) {
				if (hasBook) {
					GameState.player.setWallJumpEnabled(true);
					logUser("You learned to wall jump!");
				} else {
					logCommon(subjectWord, Response.LACK);
				}
			}
			
		} else if (verb.getId().contentEquals("open")) {
			if (subject.getId().contentEquals("vault")) {
				openVault(subjectWord);
			} else if (subject.getId().contentEquals("drawer")) {
				openDrawer(subjectWord);
			} else if (subject.getId().contentEquals("safe")) {
				openSafe(subjectWord);
			}
			
		} else if (verb.getId().contentEquals("jump")) {
			if (subject.getId().contentEquals("null")) {
				if (GameState.player != null) {
					GameState.player.setJumpEnabled(true);
					GameState.player.setVelocity(new Vector2(0,-10));
					logUser("JUMP unlocked!");
				}
			}
			
		} else {
			logUser("Something went wrong...");
		}
	}

	private static void grabRag(String subjectWord) {
		if(GameState.rag.onScreen()) {
			if (GameState.player.intersecting(GameState.rag)) {
				if (GameState.rag != null) {
					hasRag = true;
					GameState.rag.remove();
				}
			} else {
				logCommon(subjectWord, Response.FAR);
			}
		} else {
			logCommon(subjectWord, Response.WHERE);
		}
	}

	private static void useRag(String subjectWord) {
		if (hasRag) {
			if (currentRoom == kitchen) {
				if (hasStickyRag) {
					logUser("You already cleaned up the jam.");
				} else if (hasRag) {
					hasStickyRag = true;
					logUser("You cleaned up the jam.");
					logUser("You're rag is now covered in it. So sticky!");
				}
			} else {
				logUser("Nothing to clean here.");
			}
		} else {
			logCommon(subjectWord, Response.LACK);
		}
	}

	private static void talkMan(String subjectWord) {
		if (GameState.npc.onScreen()) {
			if (GameState.npc.intersecting(GameState.player)) {
				if (hasStickyRag) {
					logUser("Delicious! He taught you to double jump.");
					GameState.entityManager.removeEntity(GameState.npc);
					GameState.npc = new ImageSEntity(GameState.handler, Assets.bearJam, new Vector2(400,-550), 400, 200);
					GameState.entityManager.addEntity(GameState.npc);
					GameState.player.setDoubleJumpEnabled(true);
				} else {
					logUser("If you bring me jam, I teach you something.");
				}
			} else {
				logUser("He can't hear you.");
			}
		} else {
			logCommon(subjectWord, Response.WHERE);
		}
	}

	private static void cleanJam(String subjectWord) {
		if (currentRoom == kitchen) {
			if (hasStickyRag) {
				logUser("You already cleanedup the " + subjectWord + ".");
			} else if (hasRag) {
				hasStickyRag = true;
				logUser("You cleaned up the " + subjectWord + ".");
				logUser("You're rag is now covered in it. So sticky!");
			} else {
				logUser("I need something to clean it up with.");
			}
		} else {
			logCommon(subjectWord, Response.WHERE);
		}
	}

	private static void openVault(String subjectWord) {
		if(GameState.vault.onScreen()) {
			if (hasCode) {
				if (GameState.vault != null) {
					GameState.vault.remove();
				}
			} else {
				logUser("The " + subjectWord + " is locked.");
				logUser("It needs a special code.");
			}
		} else {
			logCommon(subjectWord, Response.WHERE);
		}
	}

	private static void grabCode(String subjectWord) {
		if (currentRoom == backroom && hasLight && safeOpen) {
			if (hasCode) {
				logUser("You already have the " + subjectWord + ".");
			} else {
				hasCode = true;
				logUser("You grab the " + subjectWord + ".");
				logUser("Its a combination!");
			}
		} else {
			logCommon(subjectWord, Response.WHERE);
		}
	}

	private static void examineSafe(String subjectWord) {
		if (currentRoom == backroom && hasLight && foundSafe) {
			if (safeOpen) {
				if (hasCode) {
					logUser("Just some green rags.");
				} else {
					logUser("There's a slip of paper in here.");
				}
			} else {
				logUser("It looks like it needs a special key...");
			}
		} else {
			logCommon(subjectWord, Response.WHERE);
		}
	}

	private static void grabBattery(String subjectWord) {
		if (currentRoom == kitchen && openedDrawer) {
			if (hasBattery) {
				logUser("You already have the " + subjectWord + ".");
			} else {
				hasBattery = true;
				logUser("You grab the " + subjectWord + ".");
			}
		} else {
			logCommon(subjectWord, Response.WHERE);
		}
	}
	
	private static void grabFlashlight(String subjectWord) {
		if (currentRoom == garage) {
			if (hasFlashlight) {
				logUser("You already have the " + subjectWord + ".");
			} else {
				hasFlashlight = true;
				logUser("You grab the " + subjectWord + ".");
			}
		} else {
			logCommon(subjectWord, Response.WHERE);
		}
	}

	private static void grabBook(String subjectWord) {
		if (GameState.book.onScreen()) {
			if (GameState.player.intersecting(GameState.book)) {
				if (GameState.book != null) {
					hasBook = true;
					GameState.book.remove();
				}
			} else {
				logCommon(subjectWord, Response.FAR);
			}
		} else {
			logCommon(subjectWord, Response.WHERE);
		}
	}

	private static void grabKey(String subjectWord) {
		if (GameState.key.onScreen()) {
			if (GameState.player.intersecting(GameState.key)) {
				if (GameState.key != null) {
					hasKey = true;
					GameState.key.remove();
				}
			} else {
				logCommon(subjectWord, Response.FAR);
			}
		} else {
			logCommon(subjectWord, Response.WHERE);
		}
	}

	private static void useFlashlight(String subjectWord) {
		if (hasFlashlight) {
			if (loadedFlashlight) {
				if (hasLight) {
					logUser("The light winks out.");
				} else {
					logUser("Light fills the room!");
				}
				hasLight = !hasLight;
			} else {
				logUser("The batteries are dead.");
			}
		} else {
			logCommon(subjectWord, Response.LACK);
		}
	}
	
	private static void useComputer(String subjectWord) {
		if (currentRoom == backroom && hasLight) {
			if (!flippedBreaker) {
				logUser("The power is out.");
			} else {
				logUser("The computer light glows.");
				currentRoom = computer;
				announceRoom();
				logUser("You may \"logout\" at any time.");
				loadGame.setUnlocked(true);
				restart.setUnlocked(true);
			}
		} else {
			logCommon(subjectWord, Response.WHERE);
		}
	}

	private static void loadBattery(String subjectWord) {
		if (hasBattery) {
			if (hasFlashlight) {
				if (loadedFlashlight) {
					logCommon(null, Response.NONEED);
				} else {
					loadedFlashlight = true;
					logUser("You changed the flashlight's batteries.");
				}
			} else {
				logCommon(null, Response.HOW);
			}
		} else {
			logCommon(subjectWord, Response.LACK);
		}
	}

	private static void flipBreaker(String subjectWord) {
		if (currentRoom == garage) {
			flippedBreaker = !flippedBreaker;
			logUser("You flipped the main breaker.");
		} else {
			logCommon(subjectWord, Response.WHERE);
		}
	}

	private static void openDrawer(String subjectWord) {
		if (currentRoom == kitchen) {
			if (openedDrawer) {
				logUser("Its already open.");
			} else {
				openedDrawer = true;
				logUser("You opened the drawer");
			}
		} else {
			logCommon(subjectWord, Response.WHERE);
		}
	}
	
	private static void openSafe(String subjectWord) {
		if (currentRoom == backroom && hasLight && foundSafe) {
			if (safeOpen) {
				logUser("Its already open.");
			} else {
				safeOpen = true;
				logUser("You opened the safe with the key.");
			}
		} else {
			logCommon(subjectWord, Response.WHERE);
		}
	}
	
	private static void logCommon(String s, Response n) {
		switch(n) {
		case WHERE:
			logUser("I don't see a " + s + ".");
			break;
		case FAR:
			logUser("The " + s + " is too far away.");
			break;
		case LACK:
			logUser("You don't have  a " + s + ".");
			break;
		case NOGO:
			logUser("You can't go " + s + ".");
			break;
		case HOW:
			logUser("How would I do that?");
			break;
		case NONEED:
			logUser("You've already done that.");
			break;
		default:
			logUser(s + "? What are you saying bub?");
			break;
		}
	}

	private static void badGrammer() {
		GameState.console.addNewLine("ERROR");
		GameState.console.updateRender();
	}
}
