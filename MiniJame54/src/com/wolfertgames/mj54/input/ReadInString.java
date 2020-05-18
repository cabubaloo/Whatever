package com.wolfertgames.mj54.input;

import java.awt.event.KeyEvent;

public class ReadInString implements KeyResponder {
	
	private StringBuilder string;
	private boolean listening = false;
	private boolean submitted = false;
	
	public ReadInString() {
		string = new StringBuilder();
	}

	public String getString() {
		return string.toString();
	}

	public void setString(String string) {
		this.string = new StringBuilder(string);
	}
	
	public boolean isListening() {
		return listening;
	}

	public void setListening(boolean listening) {
		this.listening = listening;
	}

	public void removeLastChar() {
		string.deleteCharAt(string.length() - 1);
	}
	
	public void addChar(char c) {
		string.append(c);
	}
	
	public String retrieveSubmission() {
		String submission = string.toString();
		string.delete(0, string.length());
		submitted = false;
		return submission;
	}

	@Override
	public void onKeyTyped(KeyEvent e) {
		
		if (listening && !submitted) {
			if (e.getKeyChar() == KeyEvent.VK_ENTER) {
				System.out.println("Submitted!");
				submitted = true;
			} else if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
				System.out.println("Backspace!");
				if(string.length() > 0) removeLastChar();
			} else {
				if (!e.isActionKey()) {
					string.append(e.getKeyChar());
				}
			}
		}
	}

	@Override
	public void onKeyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onKeyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public boolean isSubmitted() {
		return submitted;
	}

	public void setSubmitted(boolean submitted) {
		this.submitted = submitted;
	}
	
}
