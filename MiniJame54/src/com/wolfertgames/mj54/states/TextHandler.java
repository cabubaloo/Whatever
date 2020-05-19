package com.wolfertgames.mj54.states;

import java.awt.Color;
import java.util.ArrayList;

import com.wolfertgames.gj54.entities.statics.ImageSEntity;
import com.wolfertgames.gj54.math.Vector2;
import com.wolfertgames.mj54.commands.Subject;
import com.wolfertgames.mj54.commands.Verb;
import com.wolfertgames.mj54.display.gfx.Assets;
import com.wolfertgames.mj54.rooms.ComputerRoom;
import com.wolfertgames.mj54.rooms.Room;
import com.wolfertgames.mj54.sound.AudioPlayer;
import com.wolfertgames.mj54.ui.TextLine;

public class TextHandler {
	
	public static enum Response { WHERE, FAR, LACK, NOGO, HOW, NONEED};
	
	public static Color errorColor = Color.red;
	public static Color warnColor = Color.yellow;
	public static Color messageColor = Color.green;
	public static Color congratsColor = Color.magenta;
	
	public static final Subject[] recognizedSubjects =  {
			new Subject("player", new String[] {"player.exe", "file"}),
			new Subject("jam", new String[] {"spill"}),
			new Subject("computer", new String[] {"pc", "mac", "desktop", "machine"}),
			new Subject("rag", new String[] {"towel", "fabric", "cloth"}),
			new Subject("breaker", new String[] {"panel"}),
			new Subject("painting", new String[] {"painting", "image"}),
			new Subject("safe", new String[] {"box"}),
			new Subject("code", new String[] {"paper", "secret"}),
			new Subject("flashlight", new String[] {"torch"}),
			new Subject("drawer", new String[] {"cupboard"}),
			new Subject("battery", new String[] {"cell", "d-cell", "batteries"}),
			new Subject("book", new String[] {"tome", "bible"}),
			new Subject("key", new String[] {"brass", "unlocker"}),
			new Subject("bear", new String[] {"npc", "animal", "monster"}),
			new Subject("vault", new String[] {"door", "wall", "bank"}),
			new Subject("code", new String[] {"numbers", "combination", "password"}),
			new Subject("null", new String[] {"now"}),
			new Subject("right", new String[] {"r"}),
			new Subject("left", new String[] {"l"}),
			new Subject("kitchen", new String[] {"dining"}),
			new Subject("garage", new String[] {"shop"}),
			new Subject("backroom", new String[] {"batcave"})
	};
	
	private static Verb loadGame = new Verb("load", new String[] {"bootup", "start", "run"}, new String[] {"player"}, false);
	private static Verb restart = new Verb("restart", new String[] {"respawn", "reload"}, new String[] {"null"}, false);
	private static Verb jump = new Verb("jump", new String[] {"bounce", "leap"}, new String[] {"null"}, false);
	private static Verb win = new Verb("win", new String[] {"end"}, new String[] {"null"}, false);
	
	public static final Verb[] recognizedVerbs =  {
			new Verb("restart", new String[] {"respawn", "reload"}, new String[] {"null"}),
			new Verb("grab", new String[] {"pickup", "snatch", "lift", "get", "remove", "take"},
					new String[] {"book", "key", "battery", "flashlight", "code", "rag", "jam"}),
			new Verb("speak", new String[] {"talk", "yell", "pet", "touch", "hug"}, new String[] {"bear"}),
			new Verb("enter", new String[] {"write", "submit", "spin"}, new String[] {"code", "garage", "kitchen", "backroom"}),
			new Verb("wipe", new String[] {"clean"}, new String[] {"jam"}),
			new Verb("read", new String[] {"scan", "browse", "digest"}, new String[] {"book"}),
			new Verb("open", new String[] {"unlock"}, new String[] {"vault", "drawer", "safe", "breaker", "player"}),
			new Verb("help", new String[] {"h", "?"}, new String[] {"null"}),
			new Verb("grammer", new String[] {"g", "rules"}, new String[] {"null"}),
			new Verb("clear", new String[] {"empty"}, new String[] {"null"}),
			new Verb("hurt", new String[] {"attack", "abuse", "hit"}, new String[] {"bear"}),
			new Verb("kill", new String[] {"murder", "end"}, new String[] {"player"}),
			new Verb("replace", new String[] {"change"}, new String[] {"battery"}),
			new Verb("go", new String[] {"move", "walk"}, new String[] {"right", "left", "garage", "kitchen", "backroom"}),
			new Verb("logout", new String[] {"shutdown"}, new String[] {"null"}),
			new Verb("flip", new String[] {"invert"}, new String[] {"breaker"}),
			new Verb("look", new String[] {"view", "observe"}, new String[] {"null", "breaker", "painting", "safe"}),
			new Verb("inventory", new String[] {"items", "things", "i"}, new String[] {"null"}),
			new Verb("use", new String[] {"interact", "apply"},
					new String[] {"breaker", "battery", "flashlight", "computer", "key", "rag", "code"}),
			new Verb("examine", new String[] {"inspect", "investigate"}, new String[] {"breaker", "painting", "safe"}),
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
		messageUser("You entered the " + currentRoom.getName() + ".");
	}
	
	public static void intro() {
		messageUser("Welcome.");
		messageUser("Type \"help\" for guidance.");
	}
	
	public static void lookAround() {
		if (currentRoom == kitchen) {
			if (!openedDrawer) {
				messageUser("You notice a drawer slighty ajar.");
			} else if (!hasBattery){
				messageUser("You see a D-Cell Battery inside the drawer.");
			}
			if (hasStickyRag) {
				messageUser("The table is spotless");
			} else {
				messageUser("Somebody spilled some jam on the table");
			}
		} else if (currentRoom == garage) {
			if (hasFlashlight) {
				messageUser("You notice a breaker panel in the back.");
			} else {
				messageUser("You see a flashlight on the workbench.");
				messageUser("You also notice a breaker panel in the back.");
			}
		} else if (currentRoom == backroom) {
			if (hasLight) {
				messageUser("There is a computer in the corner with");
				messageUser("some cobwebs all over it.");
				if (foundSafe) {
					if (safeOpen) {
						messageUser("There's a opened safe in the wall.");
					} else {
						messageUser("There's a locked safe in the wall.");
					}
				} else {
					messageUser("There's a strange painting on the wall.");
				}
			} else {
				messageUser("It's too dark! You can't see anything.");
			}
		} else if (currentRoom == computer) {
			messageUser("There is one file titled PLAYER.exe");
		}
		currentRoom.printOptions();
	}
	
	public static void messageUser(String s) {
		new AudioPlayer(Assets.type);
		logUser(s, messageColor);
	}
	
	public static void messageUser(String[] s) {
		new AudioPlayer(Assets.type);
		logUser(s, messageColor);
	}
	
	public static void errorUser(String s) {
		new AudioPlayer(Assets.error);
		logUser(s, errorColor);
	}
	
	public static void warnUser(String s) {
		new AudioPlayer(Assets.slapSound);
		logUser(s, warnColor);
	}
	
	public static void warnUser(String[] s) {
		new AudioPlayer(Assets.slapSound);
		logUser(s, warnColor);
	}
	
	public static void rewardUser(String s) {
		logUser(s, congratsColor);
	}
	
	public static void rewardUser(String[] s) {
		logUser(s, congratsColor);
	}
	
	public static void logUser(String s, Color c) {
		GameState.console.addNewLine(new TextLine(s, c));
		GameState.console.updateRender();
		if (!GameState.console.onScreen()) {
			GameState.popup.popUp(5000, new TextLine(s, c));
		}
	}
	
	public static void logUser(String[] s, Color c) {
		TextLine[] t = new TextLine[s.length];
		for (int i = 0; i < s.length; i++) t[i] = new TextLine(s[i], c);
		GameState.console.addNewLines(t);
		GameState.console.updateRender();
		if (!GameState.console.onScreen()) {
			GameState.popup.popUpMany(5000, t);
		}
	}
	
	static void inventory() {
		ArrayList<String> inventory = new ArrayList<String>();
		inventory.add("Inventory:");
		if (loadedFlashlight) inventory.add("Flashlight");
		else {
			if (hasFlashlight) inventory.add("Flashlight (Dead)");
			if (hasBattery) inventory.add("Battery");
		}
		if (hasKey) inventory.add ("Key");
		if (hasBook) inventory.add ("Book");
		if (hasCode) inventory.add ("Combination");
		if (hasStickyRag) inventory.add("Jam-Soaked Rag");
		else if (hasRag) inventory.add("Rag");
		messageUser(inventory.toArray(new String[inventory.size()]));
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
			errorUser("How does one \"" + verbWord + "\"?");
		} else if (subject == null) {
			errorUser("What is a \"" + subjectWord + "\"?");
		} else if (!isGoodGrammer(verb, subject)) {
			errorUser("Thats just silly!");
		} else {
			logUser("--" + command, Color.cyan);
			runCommand(verb, subject, verbWord, subjectWord);
		}
	}
	
	private static boolean isGoodGrammer(Verb v, Subject s) {
		return v.contains(s.getId());
	}
	
	private static Verb getVerb(String verb) {
		verb = verb.toLowerCase();
		for (int i = 0; i < recognizedVerbs.length; i++) {
			if (recognizedVerbs[i].knownAs(verb) && recognizedVerbs[i].isUnlocked())
				return recognizedVerbs[i];
		}
		return null;
	}
	
	private static Subject getSubject(String subject) {
		subject = subject.toLowerCase();
		for (int i = 0; i < recognizedSubjects.length; i++) {
			if (recognizedSubjects[i].knownAs(subject) && recognizedSubjects[i].isUnlocked())
				return recognizedSubjects[i];
		}
		return null;
	}
	
	private static void changeRooms(Room room) {
		currentRoom = room;
		new AudioPlayer(Assets.walk);
		announceRoom();
	}

	private static void runCommand(Verb verb, Subject subject, String verbWord, String subjectWord) {
		System.out.println("Running Command...");
		
		if (verb.getId().contentEquals("go")) {
			if (subject.getId().contentEquals("left")) {
				if (currentRoom.goLeft() != null) {
					changeRooms(currentRoom.goLeft());
				} else {
					logCommon(subjectWord, Response.NOGO);
				}
			} else if (subject.getId().contentEquals("right")) {
				if (currentRoom.goRight() != null) {
					changeRooms(currentRoom.goRight());
				} else {
					logCommon(subjectWord, Response.NOGO);
				}
			} else if (subject.getId().contentEquals("backroom")) {
				checkIfValidMove(backroom);
			} else if (subject.getId().contentEquals("kitchen")) {
				checkIfValidMove(kitchen);
			} else if (subject.getId().contentEquals("garage")) {
				checkIfValidMove(garage);
			}
			
		} else if (verb.getId().contentEquals("win")) {
			GameState.win();
			
		} else if (verb.getId().contentEquals("help")) {
			warnUser(new String[] {"Enter commands to explore and interact",
			"with the world. Type \"grammer\" to",
			"learn the command format.",
			"Try using \"look\" to investigate your",
			"surroundings!"});
			
		} else if (verb.getId().contentEquals("grammer")) {
			warnUser(new String[] {"Example Command: [verb] [subject]",
			"The subject can be blank but you can",
			"only ever use one or two words."});
			
		} else if (verb.getId().contentEquals("look")) {
			if (subject.getId().contentEquals("null")) {
				lookAround();
			} else if (subject.getId().contentEquals("breaker")){
				examineBreaker();
			} else if (subject.getId().contentEquals("painting")){
				examinePainting(subjectWord);
			} else if (subject.getId().contentEquals("safe")){
				examineSafe(subjectWord);
			}
			
		} else if (verb.getId().contentEquals("inventory")) {
			inventory();
			
		} else if (verb.getId().contentEquals("replace")) {
			if (subject.getId().contentEquals("battery")) {
				loadBattery(subjectWord);
			}
			
		} else if (verb.getId().contentEquals("examine")) {
			if (subject.getId().contentEquals("breaker")) {
				examineBreaker();
			} else if (subject.getId().contentEquals("painting")) {
				examinePainting(subjectWord);
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
				useKey(subjectWord);
			} else if (subject.getId().contentEquals("rag")) {
				useRag(subjectWord);
			} else if (subject.getId().contentEquals("code")) {
				openVault(subjectWord);
			}
			
		} else if (verb.getId().contentEquals("hurt")) {
			if (subject.getId().contentEquals("bear")) {
				messageUser("Maybe you should talk to him instead.");
			}
			
		} else if (verb.getId().contentEquals("flip")) {
			if (subject.getId().contentEquals("breaker")) {
				flipBreaker(subjectWord);
			}
			
		} else if (verb.getId().contentEquals("kill")) {
			if (subject.getId().contentEquals("player")) {
				GameState.killPlayer();//if (GameState.player != null) GameState.killPlayer();
			} else {
				logCommon(subjectWord, Response.HOW);
			}
			
		} else if (verb.getId().contentEquals("restart")) {
			GameState.backToConsole();
			GameState.loadPlayer();
			rewardUser("Player ready.");
			
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
					rewardUser("Player ready.");
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
			} else if (subject.getId().contentEquals("jam")) {
				cleanJam(subjectWord);
			}
		
		} else if (verb.getId().contentEquals("enter")) {
			if (subject.getId().contentEquals("code")) {
				openVault(subjectWord);
			} else if (subject.getId().contentEquals("backroom")) {
				checkIfValidMove(backroom);
			} else if (subject.getId().contentEquals("kitchen")) {
				checkIfValidMove(kitchen);
			} else if (subject.getId().contentEquals("garage")) {
				checkIfValidMove(garage);
			}
			
		} else if (verb.getId().contentEquals("wipe")) {
			if (subject.getId().contentEquals("jam")) {
				cleanJam(subjectWord);
			}
			
		} else if (verb.getId().contentEquals("speak")) {
			if (subject.getId().contentEquals("bear")) {
				talkBear(subjectWord);
			}
			
		} else if (verb.getId().contentEquals("read")) {
			if (subject.getId().contentEquals("book")) {
				if (hasBook) {
					new AudioPlayer(Assets.paper);
					GameState.player.setWallJumpEnabled(true);
					rewardUser("You learned to wall jump!");
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
			} else if (subject.getId().contentEquals("breaker")) {
				examineBreaker();
			} else if (subject.getId().contentEquals("player")) {
				if (currentRoom == computer) {
					GameState.loadPlayer();
					rewardUser("Player ready.");
					jump.setUnlocked(true);
				} else {
					logCommon(subjectWord, Response.WHERE);
				}
			}
			
		} else if (verb.getId().contentEquals("jump")) {
			if (subject.getId().contentEquals("null")) {
				if (GameState.player != null && currentRoom == computer) {
					if (!GameState.player.isJumpEnabled()) {
						new AudioPlayer(Assets.goodSound);
						GameState.player.setVelocity(new Vector2(0,-10));
						GameState.player.setJumpEnabled(true);
						rewardUser("JUMP unlocked!");
					}
				}
			}
			
		} else {
			errorUser("Something went wrong...");
		}
	}

	private static void useKey(String subjectWord) {
		if (currentRoom == backroom && hasLight && foundSafe) {
			openSafe("safe");
		} else {
			warnUser("What would I use it for?");
		}
		
	}

	private static void checkIfValidMove(Room room) {
		if (currentRoom == room && currentRoom != computer) {
			warnUser("You are already there.");
		} else if (currentRoom == computer) {
			warnUser("You must logout!");
		} else {
			changeRooms(room);
		}
	}

	private static void examinePainting(String subjectWord) {
		if (currentRoom == backroom && hasLight) {
			new AudioPlayer(Assets.scrape);
			rewardUser("Theres a safe behind here!");
			foundSafe = true;
		} else {
			logCommon(subjectWord, Response.WHERE);
		}
	}

	private static void examineBreaker() {
		if (flippedBreaker) {
			messageUser("The power is now on!");
		} else {
			messageUser("The main breaker has been tripped.");
		}
	}

	private static void grabRag(String subjectWord) {
		if(GameState.rag.onScreen()) {
			if (GameState.player.intersecting(GameState.rag)) {
				if (GameState.rag != null) {
					new AudioPlayer(Assets.grabSound);
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
					warnUser("You already cleaned up the jam.");
				} else if (hasRag) {
					hasStickyRag = true;
					new AudioPlayer(Assets.grabSound);
					rewardUser("You cleaned up the jam.");
					rewardUser("You're rag is now covered in it. So sticky!");
				}
			} else {
				warnUser("Nothing to clean here.");
			}
		} else {
			logCommon(subjectWord, Response.LACK);
		}
	}

	private static void talkBear(String subjectWord) {
		if (GameState.npc.onScreen()) {
			if (GameState.npc.intersecting(GameState.player)) {
				if (hasStickyRag) {
					new AudioPlayer(Assets.bearRoar);
					rewardUser(new String[] {"Marvelous! If you try to jump again",
							"after you leave the ground, you might just",
							"find you can! Believe in yourself!"});
					GameState.entityManager.removeEntity(GameState.npc);
					GameState.makeNewBear(Assets.bearJam);
					GameState.player.setDoubleJumpEnabled(true);
				} else {
					new AudioPlayer(Assets.bearRoar);
					rewardUser(new String[] {"If you bring me something sweet, I'll",
							"teach you an advanced jumping technique.",
							"Do we have a deal?"});
				}
			} else {
				warnUser("He can't hear you.");
			}
		} else {
			logCommon(subjectWord, Response.WHERE);
		}
	}

	private static void cleanJam(String subjectWord) {
		if (currentRoom == kitchen) {
			if (hasStickyRag) {
				warnUser("You already cleanedup the " + subjectWord + ".");
			} else if (hasRag) {
				hasStickyRag = true;
				new AudioPlayer(Assets.grabSound);
				rewardUser("You cleaned up the " + subjectWord + ".");
				rewardUser("You're rag is now covered in it. So sticky!");
			} else {
				warnUser("I need something to clean it up with.");
			}
		} else {
			logCommon(subjectWord, Response.WHERE);
		}
	}

	private static void openVault(String subjectWord) {
		if(GameState.vault.onScreen()) {
			if (hasCode) {
				if (GameState.vault != null) {
					new AudioPlayer(Assets.bigThumpSound);
					GameState.vault.remove();
				}
			} else {
				warnUser("The " + subjectWord + " is locked.");
				warnUser("It needs a special code.");
			}
		} else {
			logCommon(subjectWord, Response.WHERE);
		}
	}

	private static void grabCode(String subjectWord) {
		if (currentRoom == backroom && hasLight && safeOpen) {
			if (hasCode) {
				warnUser("You already have the " + subjectWord + ".");
			} else {
				new AudioPlayer(Assets.paper);
				hasCode = true;
				rewardUser("You grab the " + subjectWord + ".");
				rewardUser("Its a combination!");
			}
		} else {
			logCommon(subjectWord, Response.WHERE);
		}
	}

	private static void examineSafe(String subjectWord) {
		if (currentRoom == backroom && hasLight && foundSafe) {
			if (safeOpen) {
				if (hasCode) {
					warnUser("Just some green rags.");
				} else {
					messageUser("There's a slip of paper in here.");
				}
			} else {
				warnUser("It looks like it needs a special key...");
			}
		} else {
			logCommon(subjectWord, Response.WHERE);
		}
	}

	private static void grabBattery(String subjectWord) {
		if (currentRoom == kitchen && openedDrawer) {
			if (hasBattery) {
				warnUser("You already have the " + subjectWord + ".");
			} else {
				new AudioPlayer(Assets.grabSound);
				hasBattery = true;
				rewardUser("You grab the " + subjectWord + ".");
			}
		} else {
			logCommon(subjectWord, Response.WHERE);
		}
	}
	
	private static void grabFlashlight(String subjectWord) {
		if (currentRoom == garage) {
			if (hasFlashlight) {
				warnUser("You already have the " + subjectWord + ".");
			} else {
				new AudioPlayer(Assets.grabSound);
				hasFlashlight = true;
				rewardUser("You grab the " + subjectWord + ".");
			}
		} else {
			logCommon(subjectWord, Response.WHERE);
		}
	}

	private static void grabBook(String subjectWord) {
		if (GameState.book.onScreen()) {
			if (GameState.player.intersecting(GameState.book)) {
				if (GameState.book != null) {
					new AudioPlayer(Assets.grabSound);
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
					new AudioPlayer(Assets.grabSound);
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
				new AudioPlayer(Assets.buttonSound);
				if (hasLight) {
					warnUser("The light winks out.");
				} else {
					rewardUser("Light fills the room!");
				}
				hasLight = !hasLight;
			} else {
				warnUser("The batteries are dead.");
			}
		} else {
			logCommon(subjectWord, Response.LACK);
		}
	}
	
	private static void useComputer(String subjectWord) {
		if (currentRoom == backroom && hasLight) {
			if (!flippedBreaker) {
				warnUser("The power is out.");
			} else {
				new AudioPlayer(Assets.computerBeep);
				rewardUser("The computer light glows.");
				currentRoom = computer;
				announceRoom();
				messageUser("You may \"logout\" at any time.");
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
					new AudioPlayer(Assets.goodSound);
					rewardUser("You changed the flashlight's batteries.");
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
			new AudioPlayer(Assets.switchSound);
			flippedBreaker = !flippedBreaker;
			rewardUser("You flipped the main breaker.");
		} else {
			logCommon(subjectWord, Response.WHERE);
		}
	}

	private static void openDrawer(String subjectWord) {
		if (currentRoom == kitchen) {
			if (openedDrawer) {
				warnUser("Its already open.");
			} else {
				openedDrawer = true;
				new AudioPlayer(Assets.drawerOpening);
				rewardUser("You opened the drawer");
			}
		} else {
			logCommon(subjectWord, Response.WHERE);
		}
	}
	
	private static void openSafe(String subjectWord) {
		if (currentRoom == backroom && hasLight && foundSafe) {
			if (safeOpen) {
				warnUser("Its already open.");
			} else {
				if (hasKey) {
					safeOpen = true;
					new AudioPlayer(Assets.keySound);
					rewardUser("You opened the safe with the key.");
				} else {
					warnUser("It's locked tight.");
				}
			}
		} else {
			logCommon(subjectWord, Response.WHERE);
		}
	}
	
	private static void logCommon(String s, Response n) {
		switch(n) {
		case WHERE:
			warnUser("I don't see a " + s + ".");
			break;
		case FAR:
			warnUser("The " + s + " is too far away.");
			break;
		case LACK:
			warnUser("You don't have  a " + s + ".");
			break;
		case NOGO:
			warnUser("You can't go " + s + ".");
			break;
		case HOW:
			warnUser("How would I do that?");
			break;
		case NONEED:
			warnUser("You've already done that.");
			break;
		default:
			warnUser(s + "? What are you saying bub?");
			break;
		}
	}

	private static void badGrammer() {
		errorUser("Who taught you to write?");
	}
}
