package com.windyhendra;

public class Main extends Thread {
	
	public static void main(String[] args) {
		PianoThread pt = new PianoThread();
		pt.start();
	}
	
}
