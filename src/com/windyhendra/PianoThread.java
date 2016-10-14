package com.windyhendra;

public class PianoThread extends Thread {
	private PianoGUI piano = null;
	private Thread t;
	
	public PianoThread() {
		// TODO Auto-generated method stub
		piano = new PianoGUI();
		if(piano != null) {
			piano.setVisible(true);
			piano.initialize();
			System.out.println("Berhasil Menampilkan GUI..");
		}
		else {
			System.out.println("Gagal Menampilkan GUI..");
		}
	}
	
	@Override
	public void run() {
		if(piano != null) {
			synchronized(piano) {
				for(;;) {
					piano.run();
				}
			}
//			for(;;) {
//				piano.run();
//				
//				try {
//					Thread.sleep(10);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
	
		}
	}
}
