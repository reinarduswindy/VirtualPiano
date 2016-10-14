package com.windyhendra;

import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Window;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.sound.midi.Synthesizer;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiUnavailableException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfInt;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;

public class PianoGUI extends JFrame {
	static {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}
	
	private double MIN_HUE = 0.0;
	private double MAX_HUE = 50.0;
	private double MIN_SATURATION = 0.20;
	private double MAX_SATURATION = 0.68;
	private double MIN_VALUE = 0.35;
	private double MAX_VALUE = 1.0;
	private double MIN_WHITE = 0.0;
	private double MAX_WHITE = 100.0;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9128366892504421555L;
	private JPanel contentPane;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private JPanel panel4;
	private JPanel panel5;
	private JPanel panel6;
	private JButton btnStart;
	
	private VideoCapture videoCap;
	private VideoCapture videoCap2;
	
	private int camera1 = 0;
	private int camera2 = 1;
	
	private Mat matVertical;
	private Mat matHorizontal;
	private Mat matBinaryVertical;
	private Mat matBinaryHorizontal;
	
	private Mat hierarchy;
	
	private MatOfInt hullVertical;
	private MatOfInt hullHorizontal;
	
	private Scalar lineColor = new Scalar(255, 0, 0);
	
	private Size newSize = new Size(256, 192);
	
	private boolean startPiano = false;
	private boolean pianoHorizontal = false;
	private boolean pianoVertical = false;
	private long startTime = System.currentTimeMillis();
	private int counterFPS;
	private static ArrayList<Integer> counterSound = new ArrayList<Integer>(24);
	private static Synthesizer syn = null;
	private static MidiChannel[] mc = null;

	/* TUTS COORDINATES */
	private Point doAwal1 = new Point(-1, -1);
	private Point reAwal1 = new Point(-1, -1);
	private Point miAwal1 = new Point(-1, -1);
	private Point faAwal1 = new Point(-1, -1);
	private Point solAwal1 = new Point(-1, -1);
	private Point laAwal1 = new Point(-1, -1);
	private Point siAwal1 = new Point(-1, -1);
	private Point doAwal2 = new Point(-1, -1);
	private Point reAwal2 = new Point(-1, -1);
	private Point miAwal2 = new Point(-1, -1);
	private Point faAwal2 = new Point(-1, -1);
	private Point solAwal2 = new Point(-1, -1);
	private Point laAwal2 = new Point(-1, -1);
	private Point siAwal2 = new Point(-1, -1);
	
	private Point doAkhir1 = new Point(-1, -1);
	private Point reAkhir1 = new Point(-1, -1);
	private Point miAkhir1 = new Point(-1, -1);
	private Point faAkhir1 = new Point(-1, -1);
	private Point solAkhir1 = new Point(-1, -1);
	private Point laAkhir1 = new Point(-1, -1);
	private Point siAkhir1 = new Point(-1, -1);
	private Point doAkhir2 = new Point(-1, -1);
	private Point reAkhir2 = new Point(-1, -1);
	private Point miAkhir2 = new Point(-1, -1);
	private Point faAkhir2 = new Point(-1, -1);
	private Point solAkhir2 = new Point(-1, -1);
	private Point laAkhir2 = new Point(-1, -1);
	private Point siAkhir2 = new Point(-1, -1);
	
	private Point diAwal1 = new Point(-1, -1);
	private Point riAwal1 = new Point(-1, -1);
	private Point fiAwal1 = new Point(-1, -1);
	private Point selAwal1 = new Point(-1, -1);
	private Point saAwal1 = new Point(-1, -1);
	private Point diAwal2 = new Point(-1, -1);
	private Point riAwal2 = new Point(-1, -1);
	private Point fiAwal2 = new Point(-1, -1);
	private Point selAwal2 = new Point(-1, -1);
	private Point saAwal2 = new Point(-1, -1);

	private Point diAkhir1 = new Point(-1, -1);
	private Point riAkhir1 = new Point(-1, -1);
	private Point fiAkhir1 = new Point(-1, -1);
	private Point selAkhir1 = new Point(-1, -1);
	private Point saAkhir1 = new Point(-1, -1);
	private Point diAkhir2 = new Point(-1, -1);
	private Point riAkhir2 = new Point(-1, -1);
	private Point fiAkhir2 = new Point(-1, -1);
	private Point selAkhir2 = new Point(-1, -1);
	private Point saAkhir2 = new Point(-1, -1);
	
	/* TUTS COORDINATES */
	private Point doAwal1H = new Point(-1, -1);
	private Point reAwal1H = new Point(-1, -1);
	private Point miAwal1H = new Point(-1, -1);
	private Point faAwal1H = new Point(-1, -1);
	private Point solAwal1H = new Point(-1, -1);
	private Point laAwal1H = new Point(-1, -1);
	private Point siAwal1H = new Point(-1, -1);
	private Point doAwal2H = new Point(-1, -1);
	private Point reAwal2H = new Point(-1, -1);
	private Point miAwal2H = new Point(-1, -1);
	private Point faAwal2H = new Point(-1, -1);
	private Point solAwal2H = new Point(-1, -1);
	private Point laAwal2H = new Point(-1, -1);
	private Point siAwal2H = new Point(-1, -1);
	
	private Point doAkhir1H = new Point(-1, -1);
	private Point reAkhir1H = new Point(-1, -1);
	private Point miAkhir1H = new Point(-1, -1);
	private Point faAkhir1H = new Point(-1, -1);
	private Point solAkhir1H = new Point(-1, -1);
	private Point laAkhir1H = new Point(-1, -1);
	private Point siAkhir1H = new Point(-1, -1);
	private Point doAkhir2H = new Point(-1, -1);
	private Point reAkhir2H = new Point(-1, -1);
	private Point miAkhir2H = new Point(-1, -1);
	private Point faAkhir2H = new Point(-1, -1);
	private Point solAkhir2H = new Point(-1, -1);
	private Point laAkhir2H = new Point(-1, -1);
	private Point siAkhir2H = new Point(-1, -1);
	
	private Point diAwal1H = new Point(-1, -1);
	private Point riAwal1H = new Point(-1, -1);
	private Point fiAwal1H = new Point(-1, -1);
	private Point selAwal1H = new Point(-1, -1);
	private Point saAwal1H = new Point(-1, -1);
	private Point diAwal2H = new Point(-1, -1);
	private Point riAwal2H = new Point(-1, -1);
	private Point fiAwal2H = new Point(-1, -1);
	private Point selAwal2H = new Point(-1, -1);
	private Point saAwal2H = new Point(-1, -1);

	private Point diAkhir1H = new Point(-1, -1);
	private Point riAkhir1H = new Point(-1, -1);
	private Point fiAkhir1H = new Point(-1, -1);
	private Point selAkhir1H = new Point(-1, -1);
	private Point saAkhir1H = new Point(-1, -1);
	private Point diAkhir2H = new Point(-1, -1);
	private Point riAkhir2H = new Point(-1, -1);
	private Point fiAkhir2H = new Point(-1, -1);
	private Point selAkhir2H = new Point(-1, -1);
	private Point saAkhir2H = new Point(-1, -1);

	private JLabel lblFPS;
	private JLabel lblFps;
	
	private double minHue = MIN_HUE;
	private double maxHue = MAX_HUE;
	private double minSaturation = MIN_SATURATION;
	private double maxSaturation = MAX_SATURATION;
	private double minValue = MIN_VALUE;
	private double maxValue = MAX_VALUE;
	private double minWhite = MIN_WHITE;
	private double maxWhite = MAX_WHITE;
	private double minWhite2 = MIN_WHITE;
	private double maxWhite2 = MAX_WHITE;
	
	private ArrayList<ArrayList<Point> > noteCoordVertical = new ArrayList<ArrayList<Point> >(24);
	private ArrayList<ArrayList<Point> > noteCoordCressVertical = new ArrayList<ArrayList<Point> >();
	private ArrayList<ArrayList<Point> > noteCoordHorizontal = new ArrayList<ArrayList<Point> >(24);
	private ArrayList<ArrayList<Point> > noteCoordCressHorizontal = new ArrayList<ArrayList<Point> >();
	private ArrayList<Integer> noteOn = new ArrayList<Integer>(84);
	private ArrayList<Point> points = new ArrayList<Point> (4);
//	private JLabel lblSaturationMin;
//	private JSlider sliderSaturationMin;
//	private JLabel lblSaturationMax;
//	private JSlider sliderSaturationMax;
//	private JLabel lblValueMin;
//	private JSlider sliderValueMin;
//	private JLabel lblValueMax;
//	private JSlider sliderValueMax;
//	private JButton btnSet;
//	private JButton btnReset;
//	private JLabel lblSetSaturationMin;
//	private JLabel lblSetSaturationMax;
//	private JLabel lblSetValueMin;
//	private JLabel lblSetValueMax;
//	private JLabel lblSetHueMax;
//	private JLabel lblSetHueMin;
//	private JSlider sliderHueMin;
//	private JSlider sliderHueMax;
	private JPanel panelHSV;
	private JPanel panelWhiteThreshold;
//	private JLabel lblWhiteMin;
//	private JLabel lblWhiteMax;
//	private JLabel lblSetWhiteMin;
//	private JLabel lblSetWhiteMax;
	private Point pianoAwal;
	private Point pianoAkhir;
	private JPanel panel61;
	private JPanel panel63;
	private JPanel panel68;
	private JPanel panel66;
	private JPanel panel70;
	private JPanel panel60;
	private JPanel panel62;
	private JPanel panel64;
	private JPanel panel65;
	private JPanel panel67;
	private JPanel panel69;
	private JPanel panel71;
	private JPanel panel73;
	private JPanel panel72;
	private JPanel panel75;
	private JPanel panel74;
	private JPanel panel76;
	private Component panel78;
	private JPanel panel77;
	private JPanel panel80;
	private Component panel81;
	private JPanel panel83;
	private Component panel79;
	private JPanel panel82;
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					PianoGUI frame = new PianoGUI();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	public PianoGUI() {
		this.piano();
	}
	
	/**
	 * Create the frame.
	 */
	public void piano() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1020, 588);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel1 = new JPanel();
		panel1.setBounds(5, 109, 256, 192);
		contentPane.add(panel1);
		
		panel2 = new JPanel();
		panel2.setBounds(271, 109, 256, 192);
		contentPane.add(panel2);
		
		panel3 = new JPanel();
		panel3.setBounds(537, 109, 256, 192);
		contentPane.add(panel3);
		
		panel4 = new JPanel();
		panel4.setBounds(5, 312, 256, 192);
		contentPane.add(panel4);
		
		panel5 = new JPanel();
		panel5.setBounds(271, 312, 256, 192);
		contentPane.add(panel5);
		
		panel6 = new JPanel();
		panel6.setBounds(537, 312, 256, 192);
		contentPane.add(panel6);
		
		btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!startPiano) {
					startPiano = true;
					btnStart.setText("Stop");
				}
				else {
					startPiano = false;
					pianoHorizontal = false;
					pianoVertical = false;
					panelHSV.setVisible(false);
					panelWhiteThreshold.setVisible(true);
					btnStart.setText("Start");
				}
			}
		});
		btnStart.setBounds(5, 515, 89, 23);
		contentPane.add(btnStart);
		
		lblFPS = new JLabel("0");
		lblFPS.setBounds(203, 519, 25, 14);
		contentPane.add(lblFPS);
		
		lblFps = new JLabel("FPS");
		lblFps.setBounds(226, 519, 46, 14);
		contentPane.add(lblFps);
		
		JButton btnSwitch = new JButton("Switch");
		btnSwitch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(camera1 == 0) camera1 = 1;
				else camera1 = 0;
				
				if(camera2 == 1) camera2 = 0;
				else camera2 = 1;
			}
		});
		btnSwitch.setBounds(104, 515, 89, 23);
		contentPane.add(btnSwitch);
		
		panelHSV = new JPanel();
		panelHSV.setBounds(803, 5, 191, 395);
		contentPane.add(panelHSV);
		panelHSV.setLayout(null);
		panelHSV.setVisible(false);
		
		panelWhiteThreshold = new JPanel();
		panelWhiteThreshold.setBounds(803, 5, 191, 395);
		contentPane.add(panelWhiteThreshold);
		panelWhiteThreshold.setLayout(null);
		
		JLabel lblSetHueMin = new JLabel("0.0");
		lblSetHueMin.setBounds(114, 11, 67, 14);
		panelHSV.add(lblSetHueMin);
		
		JLabel lblSetSaturationMin = new JLabel("0.2");
		lblSetSaturationMin.setBounds(114, 134, 67, 14);
		panelHSV.add(lblSetSaturationMin);
		
		JLabel lblSetSaturationMax = new JLabel("0.68");
		lblSetSaturationMax.setBounds(114, 196, 67, 14);
		panelHSV.add(lblSetSaturationMax);
		
		JLabel lblSetValueMin = new JLabel("0.35");
		lblSetValueMin.setBounds(114, 258, 67, 14);
		panelHSV.add(lblSetValueMin);
		
		JLabel lblSetValueMax = new JLabel("1.0");
		lblSetValueMax.setBounds(114, 320, 67, 14);
		panelHSV.add(lblSetValueMax);
		
		JLabel lblSetHueMax = new JLabel("50.0");
		lblSetHueMax.setBounds(114, 72, 67, 14);
		panelHSV.add(lblSetHueMax);
		
		JLabel lblWhiteMin = new JLabel("White Min 1");
		lblWhiteMin.setBounds(10, 11, 81, 14);
		panelWhiteThreshold.add(lblWhiteMin);
		
		JLabel lblWhiteMax = new JLabel("White Max 1");
		lblWhiteMax.setBounds(10, 73, 81, 14);
		panelWhiteThreshold.add(lblWhiteMax);
		
		JLabel lblSetWhiteMin = new JLabel("0.0");
		lblSetWhiteMin.setBounds(101, 11, 80, 14);
		panelWhiteThreshold.add(lblSetWhiteMin);
		
		JLabel lblSetWhiteMax = new JLabel("100.0");
		lblSetWhiteMax.setBounds(101, 73, 80, 14);
		panelWhiteThreshold.add(lblSetWhiteMax);
		
		JSlider sliderWhiteMin = new JSlider();
		sliderWhiteMin.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				lblSetWhiteMin.setText("" + sliderWhiteMin.getValue());
				minWhite = sliderWhiteMin.getValue();
			}
		});
		sliderWhiteMin.setMaximum(255);
		sliderWhiteMin.setValue(0);
		sliderWhiteMin.setBounds(0, 36, 191, 26);
		panelWhiteThreshold.add(sliderWhiteMin);
		
		JSlider sliderWhiteMax = new JSlider();
		sliderWhiteMax.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				lblSetWhiteMax.setText("" + sliderWhiteMax.getValue());
				maxWhite = sliderWhiteMax.getValue();
			}
		});
		sliderWhiteMax.setMaximum(255);
		sliderWhiteMax.setValue(100);
		sliderWhiteMax.setBounds(0, 98, 191, 26);
		panelWhiteThreshold.add(sliderWhiteMax);
		
		JLabel lblWhiteMin2 = new JLabel("White Min 2");
		lblWhiteMin2.setBounds(10, 135, 81, 14);
		panelWhiteThreshold.add(lblWhiteMin2);
		
		JLabel lblSetWhiteMin2 = new JLabel("0.0");
		lblSetWhiteMin2.setBounds(101, 135, 80, 14);
		panelWhiteThreshold.add(lblSetWhiteMin2);
		
		JLabel lblSetWhiteMax2 = new JLabel("100.0");
		lblSetWhiteMax2.setBounds(101, 197, 80, 14);
		panelWhiteThreshold.add(lblSetWhiteMax2);
		
		JLabel lblWhiteMax2 = new JLabel("White Max 2");
		lblWhiteMax2.setBounds(10, 197, 81, 14);
		panelWhiteThreshold.add(lblWhiteMax2);
		
		JSlider sliderWhiteMin2 = new JSlider();
		sliderWhiteMin2.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				lblSetWhiteMin2.setText("" + sliderWhiteMin2.getValue());
				minWhite2 = sliderWhiteMin2.getValue();
			}
		});
		sliderWhiteMin2.setMaximum(255);
		sliderWhiteMin2.setValue(0);
		sliderWhiteMin2.setBounds(0, 160, 191, 26);
		panelWhiteThreshold.add(sliderWhiteMin2);
		
		JSlider sliderWhiteMax2 = new JSlider();
		sliderWhiteMax2.setMaximum(255);
		sliderWhiteMax2.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				lblSetWhiteMax2.setText("" + sliderWhiteMax2.getValue());
				maxWhite2 = sliderWhiteMax2.getValue();
			}
		});
		sliderWhiteMax2.setValue(100);
		sliderWhiteMax2.setBounds(0, 222, 191, 26);
		panelWhiteThreshold.add(sliderWhiteMax2);
		panelWhiteThreshold.setVisible(true);
		
		JLabel lblHueMin = new JLabel("Hue Min");
		lblHueMin.setBounds(10, 11, 83, 14);
		panelHSV.add(lblHueMin);
		
		JSlider sliderHueMin = new JSlider();
		sliderHueMin.setValue(0);
		sliderHueMin.setMaximum(255);
		sliderHueMin.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				lblSetHueMin.setText("" + sliderHueMin.getValue());
				minHue = sliderHueMin.getValue();
			}
		});
		sliderHueMin.setBounds(-4, 35, 200, 26);
		panelHSV.add(sliderHueMin);
		
		JLabel lblHueMax = new JLabel("Hue Max");
		lblHueMax.setBounds(10, 72, 83, 14);
		panelHSV.add(lblHueMax);
		
		JSlider sliderHueMax = new JSlider();
		sliderHueMax.setMaximum(255);
		sliderHueMax.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				lblSetHueMax.setText("" + sliderHueMax.getValue());
				maxHue = sliderHueMax.getValue();
			}
		});
		sliderHueMax.setBounds(-4, 97, 200, 26);
		panelHSV.add(sliderHueMax);
		
		JLabel lblSaturationMin = new JLabel("Saturation Min");
		lblSaturationMin.setBounds(10, 134, 83, 14);
		panelHSV.add(lblSaturationMin);
		
		JSlider sliderSaturationMin = new JSlider();
		sliderSaturationMin.setValue(20);
		sliderSaturationMin.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				double temp = (double) sliderSaturationMin.getValue() / 100;
				lblSetSaturationMin.setText("" + temp);
				minSaturation = temp;
			}
		});
		sliderSaturationMin.setBounds(-4, 159, 200, 26);
		panelHSV.add(sliderSaturationMin);
		
		JLabel lblSaturationMax = new JLabel("Saturation Max");
		lblSaturationMax.setBounds(10, 196, 83, 14);
		panelHSV.add(lblSaturationMax);
		
		JSlider sliderSaturationMax = new JSlider();
		sliderSaturationMax.setValue(68);
		sliderSaturationMax.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				double temp = (double) sliderSaturationMax.getValue() / 100;
				lblSetSaturationMax.setText("" + temp);
				maxSaturation = temp;
			}
		});
		sliderSaturationMax.setBounds(-4, 221, 200, 26);
		panelHSV.add(sliderSaturationMax);
		
		JLabel lblValueMin = new JLabel("Value Min");
		lblValueMin.setBounds(10, 258, 83, 14);
		panelHSV.add(lblValueMin);
		
		JSlider sliderValueMin = new JSlider();
		sliderValueMin.setValue(35);
		sliderValueMin.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				double temp = (double) sliderValueMin.getValue() / 100;
				lblSetValueMin.setText("" + temp);
				minValue = temp;
			}
		});
		sliderValueMin.setBounds(-4, 283, 200, 26);
		panelHSV.add(sliderValueMin);
		
		JLabel lblValueMax = new JLabel("Value Max");
		lblValueMax.setBounds(10, 320, 83, 14);
		panelHSV.add(lblValueMax);
		
		JSlider sliderValueMax = new JSlider();
		sliderValueMax.setValue(100);
		sliderValueMax.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				double temp = (double) sliderValueMax.getValue() / 100;
				lblSetValueMax.setText("" + temp);
				maxValue = temp;
			}
		});
		sliderValueMax.setBounds(-4, 345, 200, 26);
		panelHSV.add(sliderValueMax);
		
		JButton btnSet = new JButton("Set");
		btnSet.setBounds(803, 415, 89, 23);
		contentPane.add(btnSet);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				minHue = MIN_HUE;
				maxHue = MAX_HUE;
				minSaturation = MIN_SATURATION;
				maxSaturation = MAX_SATURATION;
				minValue = MIN_VALUE;
				maxValue = MAX_VALUE;
				minWhite = MIN_WHITE;
				maxWhite = MAX_WHITE;
				minWhite2 = MIN_WHITE;
				maxWhite2 = MAX_WHITE;
				
				sliderHueMin.setValue((int) MIN_HUE);
				sliderHueMax.setValue((int) MAX_HUE);
				sliderSaturationMin.setValue((int) (MIN_SATURATION * 100));
				sliderSaturationMax.setValue((int) (MAX_SATURATION * 100));
				sliderValueMin.setValue((int) (MIN_VALUE * 100));
				sliderValueMax.setValue((int) (MAX_VALUE * 100));
				sliderWhiteMin.setValue((int) MIN_WHITE);
				sliderWhiteMax.setValue((int) MAX_WHITE);
				sliderWhiteMin2.setValue((int) MIN_WHITE);
				sliderWhiteMax2.setValue((int) MAX_WHITE);
				
				lblSetHueMin.setText("" + MIN_HUE);
				lblSetHueMax.setText("" + MAX_HUE);
				lblSetSaturationMin.setText("" + MIN_SATURATION);
				lblSetSaturationMax.setText("" + MAX_SATURATION);
				lblSetValueMin.setText("" + MIN_VALUE);
				lblSetValueMax.setText("" + MAX_VALUE);
				lblSetWhiteMin.setText("" + MIN_WHITE);
				lblSetWhiteMax.setText("" + MAX_WHITE);
				lblSetWhiteMin2.setText("" + MIN_WHITE);
				lblSetWhiteMax2.setText("" + MAX_WHITE);
			}
		});
		btnReset.setBounds(905, 415, 89, 23);
		contentPane.add(btnReset);
		
		panel61 = new JPanel();
		panel61.setForeground(Color.WHITE);
		panel61.setBackground(Color.BLACK);
		panel61.setBounds(124, 5, 25, 58);
		contentPane.add(panel61);
		
		panel63 = new JPanel();
		panel63.setForeground(Color.WHITE);
		panel63.setBackground(Color.BLACK);
		panel63.setBounds(185, 5, 25, 58);
		contentPane.add(panel63);
		
		panel68 = new JPanel();
		panel68.setForeground(Color.WHITE);
		panel68.setBackground(Color.BLACK);
		panel68.setBounds(328, 5, 25, 58);
		contentPane.add(panel68);
		
		panel66 = new JPanel();
		panel66.setBounds(271, 5, 25, 58);
		contentPane.add(panel66);
		panel66.setForeground(Color.WHITE);
		panel66.setBackground(Color.BLACK);
		
		panel70 = new JPanel();
		panel70.setForeground(Color.WHITE);
		panel70.setBackground(Color.BLACK);
		panel70.setBounds(386, 5, 25, 58);
		contentPane.add(panel70);
		
		panel60 = new JPanel();
		panel60.setBackground(Color.WHITE);
		panel60.setForeground(Color.WHITE);
		panel60.setBounds(90, 5, 32, 93);
		contentPane.add(panel60);
		panel60.setLayout(null);
		
		panel62 = new JPanel();
		panel62.setForeground(Color.WHITE);
		panel62.setBackground(Color.WHITE);
		panel62.setBounds(151, 5, 32, 93);
		contentPane.add(panel62);
		
		panel64 = new JPanel();
		panel64.setForeground(Color.WHITE);
		panel64.setBackground(Color.WHITE);
		panel64.setBounds(210, 5, 32, 93);
		contentPane.add(panel64);
		
		panel65 = new JPanel();
		panel65.setForeground(Color.WHITE);
		panel65.setBackground(Color.WHITE);
		panel65.setBounds(240, 5, 32, 93);
		contentPane.add(panel65);
		panel65.setLayout(null);
		
		panel67 = new JPanel();
		panel67.setForeground(Color.WHITE);
		panel67.setBackground(Color.WHITE);
		panel67.setBounds(296, 5, 32, 93);
		contentPane.add(panel67);
		
		panel69 = new JPanel();
		panel69.setForeground(Color.WHITE);
		panel69.setBackground(Color.WHITE);
		panel69.setBounds(353, 5, 32, 93);
		contentPane.add(panel69);
		
		panel71 = new JPanel();
		panel71.setForeground(Color.WHITE);
		panel71.setBackground(Color.WHITE);
		panel71.setBounds(411, 5, 32, 93);
		contentPane.add(panel71);
		
		panel73 = new JPanel();
		panel73.setForeground(Color.WHITE);
		panel73.setBackground(Color.BLACK);
		panel73.setBounds(475, 5, 25, 58);
		contentPane.add(panel73);
		
		panel72 = new JPanel();
		panel72.setLayout(null);
		panel72.setForeground(Color.WHITE);
		panel72.setBackground(Color.WHITE);
		panel72.setBounds(443, 5, 32, 93);
		contentPane.add(panel72);
		
		panel75 = new JPanel();
		panel75.setForeground(Color.WHITE);
		panel75.setBackground(Color.BLACK);
		panel75.setBounds(533, 5, 25, 58);
		contentPane.add(panel75);
		
		panel74 = new JPanel();
		panel74.setForeground(Color.WHITE);
		panel74.setBackground(Color.WHITE);
		panel74.setBounds(500, 5, 32, 93);
		contentPane.add(panel74);
		
		panel76 = new JPanel();
		panel76.setForeground(Color.WHITE);
		panel76.setBackground(Color.WHITE);
		panel76.setBounds(558, 5, 32, 93);
		contentPane.add(panel76);
		
		panel78 = new JPanel();
		panel78.setForeground(Color.WHITE);
		panel78.setBackground(Color.BLACK);
		panel78.setBounds(622, 5, 25, 58);
		contentPane.add(panel78);
		
		panel77 = new JPanel();
		panel77.setLayout(null);
		panel77.setForeground(Color.WHITE);
		panel77.setBackground(Color.WHITE);
		panel77.setBounds(590, 5, 32, 93);
		contentPane.add(panel77);
		
		panel80 = new JPanel();
		panel80.setForeground(Color.WHITE);
		panel80.setBackground(Color.BLACK);
		panel80.setBounds(680, 5, 25, 58);
		contentPane.add(panel80);
		
		panel79 = new JPanel();
		panel79.setForeground(Color.WHITE);
		panel79.setBackground(Color.WHITE);
		panel79.setBounds(647, 5, 32, 93);
		contentPane.add(panel79);
		
		panel82 = new JPanel();
		panel82.setForeground(Color.WHITE);
		panel82.setBackground(Color.BLACK);
		panel82.setBounds(736, 5, 25, 58);
		contentPane.add(panel82);
		
		panel81 = new JPanel();
		panel81.setForeground(Color.WHITE);
		panel81.setBackground(Color.WHITE);
		panel81.setBounds(704, 5, 32, 93);
		contentPane.add(panel81);
		
		panel83 = new JPanel();
		panel83.setForeground(Color.WHITE);
		panel83.setBackground(Color.WHITE);
		panel83.setBounds(761, 5, 32, 93);
		contentPane.add(panel83);
	}
	
	public void initialize() {
		startTime = System.currentTimeMillis();
		counterFPS = 0;
		
		try {
			syn = MidiSystem.getSynthesizer();
			syn.open();
		} catch (MidiUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mc = syn.getChannels();
		
		counterSound = new ArrayList<Integer>(24);
		noteOn = new ArrayList<Integer>(24);
		for(int i=0; i<24; i++) {
			noteOn.add(0);
			counterSound.add(0);
		}
		
		noteCoordVertical = new ArrayList<ArrayList<Point> >(24);
		noteCoordCressVertical = new ArrayList<ArrayList<Point> >();
		noteCoordHorizontal = new ArrayList<ArrayList<Point> >(24);
		noteCoordCressHorizontal = new ArrayList<ArrayList<Point> >();

		matVertical = new Mat(newSize, CvType.CV_8UC3);
		matHorizontal = new Mat(newSize, CvType.CV_8UC3);
		
		hierarchy = new Mat(matVertical.cols(), matVertical.rows(), CvType.CV_8UC1);
		
		hullVertical = new MatOfInt();
		hullHorizontal = new MatOfInt();
		
		// get video capture for vertical webcam
		videoCap = new VideoCapture();
		videoCap.open(0);
		
		// get video capture for horizontal webcam
		videoCap2 = new VideoCapture();
		videoCap2.open(1);
		
		if(videoCap.isOpened() && videoCap2.isOpened()) {
			this.addWindowListener(new WindowListener() {

				@Override
				public void windowActivated(WindowEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowClosed(WindowEvent arg0) {
					// TODO Auto-generated method stub
//					videoCap.release();
//					videoCap2.release();
				}

				@Override
				public void windowClosing(WindowEvent arg0) {
					// TODO Auto-generated method stub
					videoCap.release();
					videoCap2.release();
				}

				@Override
				public void windowDeactivated(WindowEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowDeiconified(WindowEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowIconified(WindowEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowOpened(WindowEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
			});
		}
	}
	
	public void run() {
		if(videoCap.isOpened() && videoCap2.isOpened()) {
			if(System.currentTimeMillis() - startTime >= 2000.0) {
				lblFPS.setText("" + counterFPS / 2);
				counterFPS = 0;
				startTime = System.currentTimeMillis();
			}
			else {
				counterFPS++;
			}
			
			videoCap.read(matVertical);
			videoCap2.read(matHorizontal);
			if(pianoVertical && pianoHorizontal) {
				Imgproc.resize(matVertical, matVertical, newSize);
				Imgproc.resize(matHorizontal, matHorizontal, newSize);
				
				Core.flip(matVertical, matVertical, 1);
				Core.flip(matHorizontal, matHorizontal, 1);
				
				matBinaryVertical = this.colorDetectHand(matVertical);
				matBinaryHorizontal = this.colorDetectHand(matHorizontal);
				
				Graphics g2 = panel2.getGraphics();
				g2.drawImage(this.getImage(matBinaryVertical), 0, 0, contentPane);
				Graphics g5 = panel5.getGraphics();
				g5.drawImage(this.getImage(matBinaryHorizontal), 0, 0, contentPane);
				
				ArrayList<MatOfPoint> contoursVertical = new ArrayList<MatOfPoint>();
				Imgproc.findContours(matBinaryVertical, contoursVertical, hierarchy, Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);
				
				if(contoursVertical.size() > 0) {
					int index1 = -1;
					int index2 = -1;
					double maxArea1 = 0.0;
					double maxArea2 = 0.0;

					for(int i=0; i<contoursVertical.size(); i++) {
						if(Imgproc.contourArea(contoursVertical.get(i)) >= 10.0) {
							if(maxArea1 < Imgproc.contourArea(contoursVertical.get(i))) {
								maxArea1 = Imgproc.contourArea(contoursVertical.get(i));
								index1 = i;
							}
							else if(maxArea1 != 0.0) {
								if(maxArea2 < Imgproc.contourArea(contoursVertical.get(i))) {
									maxArea2 = Imgproc.contourArea(contoursVertical.get(i));
									index2 = i;
								}
							}
						}
					}
					
					ArrayList<MatOfPoint> hulls = new ArrayList<MatOfPoint>(2);
					if(index1 != -1) {
						MatOfPoint hullPoint = this.convexHull(contoursVertical.get(index1));
 						hulls.add(hullPoint);
					
 						Imgproc.drawContours(matVertical, hulls, 0, lineColor);
					}

					if(index2 != -1) {
						MatOfPoint hullPoint = this.convexHull(contoursVertical.get(index2));
						// Imgproc.convexHull(contoursVertical.get(index2), hullVertical, false);
						// MatOfPoint hullPoint = hull2Points(hullVertical, contoursVertical.get(index2));
						hulls.add(hullPoint);
						
						Imgproc.drawContours(matVertical, hulls, 1, lineColor);
					}
					
					for(int i=0; i<hulls.size(); i++) {
						for(int j=0; j<hulls.get(i).rows(); j++) {
							for(int k=0; k<hulls.get(i).cols(); k++) {
								for(int l=0; l<noteCoordVertical.size(); l++) {
									double dd[] = hulls.get(i).get(j, k);
									
									ArrayList<Point> coordPoints = noteCoordVertical.get(l);
									Point a = coordPoints.get(0);
									Point b = coordPoints.get(1);
									Point c = coordPoints.get(2);
									Point d = coordPoints.get(3);
									
//									System.out.println(a.x + " " + a.y + " " + b.x + " " + b.y + " " + c.x + " " + c.y + " " + d.x + " " + d.y);
									// Imgproc.line(matVertical, a, b, new Scalar(0, 0, 255));
									// Imgproc.line(matVertical, c, d, new Scalar(0, 0, 255));
									if(checkInPolygon(a, b, c, d, dd)) {
										// System.out.println("masuk");
										
										if(l == 0) {
											// System.out.println("61");
											noteOn.set(1, 1);
										}
										else if(l == 1) {
											// System.out.println("63");
											noteOn.set(3, 1);
										}
										else if(l == 2) {
											// System.out.println("66");
											noteOn.set(6, 1);
										}
										else if(l == 3) {
											// System.out.println("68");
											noteOn.set(8, 1);
										}
										else if(l == 4) {
											// System.out.println("70");
											noteOn.set(10, 1);
										}
										else if(l == 5) {
											// System.out.println("73");
											noteOn.set(13, 1);
										}
										else if(l == 6) {
											// System.out.println("75");
											noteOn.set(15, 1);
										}
										else if(l == 7) {
											// System.out.println("78");
											noteOn.set(18, 1);
										}
										else if(l == 8) {
											// System.out.println("80");
											noteOn.set(20, 1);
										}
										else if(l == 9) {
											// System.out.println("82");
											noteOn.set(22, 1);
										}
										else if(l == 10) {
											// System.out.println("60");
											noteOn.set(0, 1);
										}
										else if(l == 11) {
											// System.out.println("62");
											noteOn.set(2, 1);
										}
										else if(l == 12) {
											// System.out.println("64");
											noteOn.set(4, 1);
										}
										else if(l == 13) {
											// System.out.println("65");
											noteOn.set(5, 1);
										}
										else if(l == 14) {
											// System.out.println("67");
											noteOn.set(7, 1);
										}
										else if(l == 15) {
											// System.out.println("69");
											noteOn.set(9, 1);
										}
										else if(l == 16) {
											// System.out.println("71");
											noteOn.set(11, 1);
										}
										else if(l == 17) {
											// System.out.println("72");
											noteOn.set(12, 1);
										}
										else if(l == 18) {
											// System.out.println("74");
											noteOn.set(14, 1);
										}
										else if(l == 19) {
											// System.out.println("76");
											noteOn.set(16, 1);
										}
										else if(l == 20) {
											// System.out.println("77");
											noteOn.set(17, 1);
										}
										else if(l == 21) {
											// System.out.println("79");
											noteOn.set(19, 1);
										}
										else if(l == 22) {
											// System.out.println("81");
											noteOn.set(21, 1);
										}
										else if(l == 23) {
											// System.out.println("83");
											noteOn.set(23, 1);
										}
									}
								}
							}
						}
					}
					
					BufferedImage img = this.getImage(matVertical);
					Graphics g1 = panel1.getGraphics();
					g1.drawImage(img, 0, 0, this);
				}
				
				MidiChannel ch = mc[0];
				ArrayList<MatOfPoint> contoursHorizontal = new ArrayList<MatOfPoint>();
				Imgproc.findContours(matBinaryHorizontal, contoursHorizontal, hierarchy, Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);
				
				if(contoursHorizontal.size() > 0) {
					MatOfPoint hullPoint;	
					int index1 = -1;
					int index2 = -1;
					double maxArea1 = 0.0;
					double maxArea2 = 0.0;
					ArrayList<MatOfPoint> hulls = new ArrayList<MatOfPoint>();
					for(int i=0; i<contoursHorizontal.size(); i++) {
//						if(Imgproc.contourArea(contoursHorizontal.get(i)) > 10.0) {
//							if(maxArea1 < Imgproc.contourArea(contoursHorizontal.get(i))) {
//								index1 = i;
//								maxArea1 = Imgproc.contourArea(contoursHorizontal.get(i));
//							}
//							else if(maxArea1 != 0.0) {
//								if(maxArea2 < Imgproc.contourArea(contoursHorizontal.get(i))) {
//									index2 = i;
//									maxArea2 = Imgproc.contourArea(contoursHorizontal.get(i));
//								}
//							}
//						}
//						Imgproc.convexHull(contoursHorizontal.get(i), hullHorizontal, false);
//						hullPoint = hull2Points(hullHorizontal, contoursHorizontal.get(i));
//						
						if(Imgproc.contourArea(contoursHorizontal.get(i)) > 10.0) {
							hullPoint = this.convexHull(contoursHorizontal.get(i));
							hulls.add(hullPoint);
//							Imgproc.drawContours(matHorizontal, hulls, i, lineColor);
						}
					}
					
//					if(index1 != -1) {
//						Imgproc.convexHull(contoursHorizontal.get(index1), hullHorizontal, false);
//						hullPoint = hull2Points(hullHorizontal, contoursHorizontal.get(index1));
//						
//						hulls.add(hullPoint);
//						Imgproc.drawContours(matHorizontal, hulls, 0, lineColor);
//					}
//					
//					if(index2 != -1) {
//						Imgproc.convexHull(contoursHorizontal.get(index2), hullHorizontal, false);
//						hullPoint = hull2Points(hullHorizontal, contoursHorizontal.get(index2));
//						
//						hulls.add(hullPoint);
//						Imgproc.drawContours(matHorizontal, hulls, 0, lineColor);
//					}
					
					for(int i=0; i<hulls.size(); i++) {
						for(int j=0; j<hulls.get(i).rows(); j++) {
							for(int k=0; k<hulls.get(i).cols(); k++) {
								for(int l=0; l<noteCoordHorizontal.size(); l++) {
									double dd[] = hulls.get(i).get(j, k);
									Imgproc.drawContours(matHorizontal, hulls, i, lineColor);
									ArrayList<Point> coordPoints = noteCoordHorizontal.get(l);
									Point a = coordPoints.get(0);
									Point b = coordPoints.get(1);
									Point c = coordPoints.get(2);
									Point d = coordPoints.get(3);
									
//									System.out.println(a.x + " " + a.y + " " + b.x + " " + b.y + " " + c.x + " " + c.y + " " + d.x + " " + d.y);
									// Imgproc.line(matHorizontal, a, b, new Scalar(0, 0, 255));
									// Imgproc.line(matHorizontal, c, d, new Scalar(0, 0, 255));
									if(checkInPolygon(a, b, c, d, dd)) {
										// System.out.println("masuk");
										
										if(l == 0) {
											if(noteOn.get(0) == 1) {
												noteOn.set(0, 0);
												if(counterSound.get(0) > 0) {
													counterSound.set(0, 1);
												}
												else {
													ch.noteOn(60, 600);
													counterSound.set(0, 1);
												}
												panel60.setBackground(Color.MAGENTA);
											}
											if(noteOn.get(1) == 1) {
												noteOn.set(1, 0);
												if(counterSound.get(1) > 0) {
													counterSound.set(1, 1);
												}
												else {
													ch.noteOn(61, 600);
													counterSound.set(1, 1);
												}
												panel61.setBackground(Color.MAGENTA);
											}
										}
										else if(l == 1) {
											if(noteOn.get(1) == 1) {
												noteOn.set(1, 0);
												if(counterSound.get(1) > 0) {
													counterSound.set(1, 1);
												}
												else {
													ch.noteOn(61, 600);
													counterSound.set(1, 1);
												}
												panel61.setBackground(Color.MAGENTA);
											}
											if(noteOn.get(2) == 1) {
												noteOn.set(2, 1);
												if(counterSound.get(2) > 0) {
													counterSound.set(2, 1);
												}
												else {
													ch.noteOn(62, 600);
													counterSound.set(2, 1);
												}
												panel62.setBackground(Color.MAGENTA);
											}
											if(noteOn.get(3) == 1) {
												noteOn.set(3, 0);
												if(counterSound.get(3) > 0) {
													counterSound.set(3, 1);
												}
												else {
													ch.noteOn(63, 600);
													counterSound.set(3, 1);
												}
												panel63.setBackground(Color.MAGENTA);
											}
										}
										else if(l == 2) {
											if(noteOn.get(3) == 1) {
												noteOn.set(3, 0);
												if(counterSound.get(3) > 0) {
													counterSound.set(3, 1);
												}
												else {
													ch.noteOn(63, 600);
													counterSound.set(3, 1);
												}
												panel63.setBackground(Color.MAGENTA);
											}
											if(noteOn.get(4) == 1) {
												noteOn.set(4, 0);
												if(counterSound.get(4) > 0) {
													counterSound.set(4, 1);
												}
												else {
													ch.noteOn(64, 600);
													counterSound.set(4, 1);
												}
												panel64.setBackground(Color.MAGENTA);
											}
										}
										else if(l == 3) {
											if(noteOn.get(5) == 1) {
												noteOn.set(5, 0);
												if(counterSound.get(5) > 0) {
													counterSound.set(5, 1);
												}
												else {
													ch.noteOn(65, 600);
													counterSound.set(5, 1);
												}
												panel65.setBackground(Color.MAGENTA);
											}
											if(noteOn.get(6) == 1) {
												noteOn.set(6, 0);
												if(counterSound.get(6) > 0) {
													counterSound.set(6, 1);
												}
												else {
													ch.noteOn(66, 600);
													counterSound.set(6, 1);
												}
												panel66.setBackground(Color.MAGENTA);
											}
										}
										else if(l == 4) {
											if(noteOn.get(6) == 1) {
												noteOn.set(6, 0);
												if(counterSound.get(6) > 0) {
													counterSound.set(6, 1);
												}
												else {
													ch.noteOn(66, 600);
													counterSound.set(6, 1);
												}
												panel66.setBackground(Color.MAGENTA);
											}
											if(noteOn.get(7) == 1) {
												noteOn.set(7, 0);
												if(counterSound.get(7) > 0) {
													counterSound.set(7, 1);
												}
												else {
													ch.noteOn(67, 600);
													counterSound.set(7, 1);
												}
												panel67.setBackground(Color.MAGENTA);
											}
											if(noteOn.get(8) == 1) {
												noteOn.set(8, 0);
												if(counterSound.get(8) > 0) {
													counterSound.set(8, 1);
												}
												else {
													ch.noteOn(68, 600);
													counterSound.set(8, 1);
												}
												panel68.setBackground(Color.MAGENTA);
											}
										}
										else if(l == 5) {
											if(noteOn.get(8) == 1) {
												noteOn.set(8, 0);
												if(counterSound.get(8) > 0) {
													counterSound.set(8, 1);
												}
												else {
													ch.noteOn(68, 600);
													counterSound.set(8, 1);
												}
												panel68.setBackground(Color.MAGENTA);
											}
											if(noteOn.get(9) == 1) {
												noteOn.set(9, 0);
												if(counterSound.get(9) > 0) {
													counterSound.set(9, 1);
												}
												else {
													ch.noteOn(69, 600);
													counterSound.set(9, 1);
												}
												panel69.setBackground(Color.MAGENTA);
											}
											if(noteOn.get(10) == 1) {
												noteOn.set(10, 0);
												if(counterSound.get(10) > 0) {
													counterSound.set(10, 1);
												}
												else {
													ch.noteOn(70, 600);
													counterSound.set(10, 1);
												}
												panel70.setBackground(Color.MAGENTA);
											}
										}
										else if(l == 6) {
											if(noteOn.get(10) == 1) {
												noteOn.set(10, 0);
												if(counterSound.get(10) > 0) {
													counterSound.set(10, 1);
												}
												else {
													ch.noteOn(70, 600);
													counterSound.set(10, 1);
												}
												panel70.setBackground(Color.MAGENTA);
											}
											if(noteOn.get(11) == 1) {
												noteOn.set(11, 0);
												if(counterSound.get(11) > 0) {
													counterSound.set(11, 1);
												}
												else {
													ch.noteOn(71, 600);
													counterSound.set(11, 1);
												}
												panel71.setBackground(Color.MAGENTA);
											}
										}
										else if(l == 7) {
											if(noteOn.get(12) == 1) {
												noteOn.set(12, 0);
												if(counterSound.get(12) > 0) {
													counterSound.set(12, 1);
												}
												else {
													ch.noteOn(72, 600);
													counterSound.set(12, 1);
												}
												panel72.setBackground(Color.MAGENTA);
											}
											if(noteOn.get(13) == 1) {
												noteOn.set(13, 0);
												if(counterSound.get(13) > 0) {
													counterSound.set(13, 1);
												}
												else {
													ch.noteOn(73, 600);
													counterSound.set(13, 1);
												}
												panel73.setBackground(Color.MAGENTA);
											}
										}
										else if(l == 8) {
											if(noteOn.get(13) == 1) {
												noteOn.set(13, 0);
												if(counterSound.get(13) > 0) {
													counterSound.set(13, 1);
												}
												else {
													ch.noteOn(73, 600);
													counterSound.set(13, 1);
												}
												panel73.setBackground(Color.MAGENTA);
											}
											if(noteOn.get(14) == 1) {
												noteOn.set(14, 0);
												if(counterSound.get(14) > 0) {
													counterSound.set(14, 1);
												}
												else {
													ch.noteOn(74, 600);
													counterSound.set(14, 1);
												}
												panel74.setBackground(Color.MAGENTA);
											}
											if(noteOn.get(15) == 1) {
												noteOn.set(15, 0);
												if(counterSound.get(15) > 0) {
													counterSound.set(15, 1);
												}
												else {
													ch.noteOn(75, 600);
													counterSound.set(15, 1);
												}
												panel75.setBackground(Color.MAGENTA);
											}
										}
										else if(l == 9) {
											if(noteOn.get(15) == 1) {
												noteOn.set(15, 0);
												if(counterSound.get(15) > 0) {
													counterSound.set(15, 1);
												}
												else {
													ch.noteOn(75, 600);
													counterSound.set(15, 1);
												}
												panel75.setBackground(Color.MAGENTA);
											}
											if(noteOn.get(16) == 1) {
												noteOn.set(16, 0);
												if(counterSound.get(16) > 0) {
													counterSound.set(16, 1);
												}
												else {
													ch.noteOn(76, 600);
													counterSound.set(16, 1);
												}
												panel76.setBackground(Color.MAGENTA);
											}
										}
										else if(l == 10) {
											if(noteOn.get(17) == 1) {
												noteOn.set(17, 0);
												if(counterSound.get(17) > 0) {
													counterSound.set(17, 1);
												}
												else {
													ch.noteOn(77, 600);
													counterSound.set(17, 1);
												}
												panel77.setBackground(Color.MAGENTA);
											}
											if(noteOn.get(18) == 1) {
												noteOn.set(18, 0);
												if(counterSound.get(18) > 0) {
													counterSound.set(18, 1);
												}
												else {
													ch.noteOn(78, 600);
													counterSound.set(18, 1);
												}
												panel78.setBackground(Color.MAGENTA);
											}
										}
										else if(l == 11) {
											if(noteOn.get(18) == 1) {
												noteOn.set(18, 0);
												if(counterSound.get(18) > 0) {
													counterSound.set(18, 1);
												}
												else {
													ch.noteOn(78, 600);
													counterSound.set(18, 1);
												}
												panel78.setBackground(Color.MAGENTA);
											}
											if(noteOn.get(19) == 1) {
												noteOn.set(19, 0);
												if(counterSound.get(19) > 0) {
													counterSound.set(19, 1);
												}
												else {
													ch.noteOn(79, 600);
													counterSound.set(19, 1);
												}
												panel79.setBackground(Color.MAGENTA);
											}
											if(noteOn.get(20) == 1) {
												noteOn.set(20, 0);
												if(counterSound.get(20) > 0) {
													counterSound.set(20, 1);
												}
												else {
													ch.noteOn(80, 600);
													counterSound.set(20, 1);
												}
												panel80.setBackground(Color.MAGENTA);
											}
										}
										else if(l == 12) {
											if(noteOn.get(20) == 1) {
												noteOn.set(20, 0);
												if(counterSound.get(20) > 0) {
													counterSound.set(20, 1);
												}
												else {
													ch.noteOn(80, 600);
													counterSound.set(20, 1);
												}
												panel80.setBackground(Color.MAGENTA);
											}
											if(noteOn.get(21) == 1) {
												noteOn.set(21, 0);
												if(counterSound.get(21) > 0) {
													counterSound.set(21, 1);
												}
												else {
													ch.noteOn(81, 600);
													counterSound.set(21, 1);
												}
												panel81.setBackground(Color.MAGENTA);
											}
											if(noteOn.get(22) == 1) {
												noteOn.set(22, 0);
												if(counterSound.get(22) > 0) {
													counterSound.set(22, 1);
												}
												else {
													ch.noteOn(82, 600);
													counterSound.set(22, 1);
												}
												panel82.setBackground(Color.MAGENTA);
											}
										}
										else if(l == 13) {
											if(noteOn.get(22) == 1) {
												noteOn.set(22, 0);
												if(counterSound.get(22) > 0) {
													counterSound.set(22, 1);
												}
												else {
													ch.noteOn(82, 600);
													counterSound.set(22, 1);
												}
												panel82.setBackground(Color.MAGENTA);
											}
											if(noteOn.get(23) == 1) {
												noteOn.set(23, 0);
												if(counterSound.get(23) > 0) {
													counterSound.set(23, 1);
												}
												else {
													ch.noteOn(83, 600);
													counterSound.set(23, 1);
												}
												panel83.setBackground(Color.MAGENTA);
											}
										}
									}
								}
							}
						}
					}
					
					Graphics g4 = panel4.getGraphics();
					g4.drawImage(this.getImage(matHorizontal), 0, 0, contentPane);
				}
				
				for(int i=0; i<24; i++) {
					if(counterSound.get(i) == 5) {
						counterSound.set(i, 0);
						ch.noteOff(i + 60);

						if(i == 0) {
							panel60.setBackground(Color.WHITE);
						}
						else if(i == 1) {
							panel61.setBackground(Color.BLACK);
						}
						else if(i == 2) {
							panel62.setBackground(Color.WHITE);
						}
						else if(i == 3) {
							panel63.setBackground(Color.BLACK);
						}
						else if(i == 4) {
							panel64.setBackground(Color.WHITE);
						}
						else if(i == 5) {
							panel65.setBackground(Color.WHITE);
						}
						else if(i == 6) {
							panel66.setBackground(Color.BLACK);
						}
						else if(i == 7) {
							panel67.setBackground(Color.WHITE);
						}
						else if(i == 8) {
							panel68.setBackground(Color.BLACK);
						}
						else if(i == 9) {
							panel69.setBackground(Color.WHITE);
						}
						else if(i == 10) {
							panel70.setBackground(Color.BLACK);
						}
						else if(i == 11) {
							panel71.setBackground(Color.WHITE);
						}
						else if(i == 12) {
							panel72.setBackground(Color.WHITE);
						}
						else if(i == 13) {
							panel73.setBackground(Color.BLACK);
						}
						else if(i == 14) {
							panel74.setBackground(Color.WHITE);
						}
						else if(i == 15) {
							panel75.setBackground(Color.BLACK);
						}
						else if(i == 16) {
							panel76.setBackground(Color.WHITE);
						}
						else if(i == 17) {
							panel77.setBackground(Color.WHITE);
						}
						else if(i == 18) {
							panel78.setBackground(Color.BLACK);
						}
						else if(i == 19) {
							panel79.setBackground(Color.WHITE);
						}
						else if(i == 20) {
							panel80.setBackground(Color.BLACK);
						}
						else if(i == 21) {
							panel81.setBackground(Color.WHITE);
						}
						else if(i == 22) {
							panel82.setBackground(Color.BLACK);
						}
						else if(i == 23) {
							panel83.setBackground(Color.WHITE);
						}
					}
					else if(counterSound.get(i) > 0) {
						counterSound.set(i, counterSound.get(i) + 1);
					}
				}
			}
			else {
				Imgproc.resize(matVertical, matVertical, newSize);
				Imgproc.resize(matHorizontal, matHorizontal, newSize);
				
				Core.flip(matVertical, matVertical, 1);
				Core.flip(matHorizontal, matHorizontal, 1);
				
				Mat matBinaryVertical = new Mat();
				Imgproc.cvtColor(matVertical, matBinaryVertical, Imgproc.COLOR_BGR2HSV);
				Core.inRange(matBinaryVertical, new Scalar(0, 0, minWhite, 0), new Scalar(180, 255, maxWhite, 0), matBinaryVertical);
				
				Mat matBinaryHorizontal = new Mat();
				Imgproc.cvtColor(matHorizontal, matBinaryHorizontal, Imgproc.COLOR_BGR2HSV);
				Core.inRange(matBinaryHorizontal, new Scalar(0, 0, minWhite2, 0), new Scalar(180, 255, maxWhite2, 0), matBinaryHorizontal);
				
				Mat resBinaryVertical = matBinaryVertical.clone();
				BufferedImage img1Vertical = this.getImage(resBinaryVertical);
				Graphics g1 = panel1.getGraphics();
				g1.drawImage(img1Vertical, 0, 0, contentPane);
				
				Mat resBinaryHorizontal = matBinaryHorizontal.clone();
				BufferedImage img1Horizontal = this.getImage(resBinaryHorizontal);
				Graphics g4 = panel4.getGraphics();
				g4.drawImage(img1Horizontal, 0, 0, contentPane);
				
				ArrayList<MatOfPoint> contoursVertical = new ArrayList<MatOfPoint>();
				
				Imgproc.findContours(matBinaryVertical, contoursVertical, hierarchy, 
						Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);
				
				int counterTutsVertical = 0;
				int counterTutsVertical2 = 0;
				if(contoursVertical.size() > 0) {
					int index = -1;
					double maxArea = 0.0;
					for(int i=0; i<contoursVertical.size(); i++) {
						if(maxArea < Imgproc.contourArea(contoursVertical.get(i))) {
							maxArea = Imgproc.contourArea(contoursVertical.get(i));
							index = i;
						}
					}
					
					if(index != -1) {
						Rect rect = Imgproc.boundingRect(contoursVertical.get(index));
						
						Mat matSobelVertical = this.sobelFilter2(matVertical, rect);
						
						Point awalX = new Point(-1, -1);
						Point awalY = new Point(-1, -1);
						Point akhirX = new Point(-1, -1);
						Point akhirY = new Point(-1, -1);
						for(int i=rect.y + rect.height - 20; i>=rect.y + 10; i--) {
							for(int j=rect.x + 5; j<=rect.x + rect.width - 5; j++) {
								double d[] = matSobelVertical.get(i, j);
								
								double ds[] = matSobelVertical.get(i, j + 3);
								double ds2[] = matSobelVertical.get(i, j - 3);
								
								double db[] = resBinaryVertical.get(i - 1, j);
								double db2[] = resBinaryVertical.get(i + 1, j);
								double db3[] = resBinaryVertical.get(i, j + 1);
								double db4[] = resBinaryVertical.get(i, j - 1);
								double db5[] = resBinaryVertical.get(i + 1, j - 1);
								if(d.length > 0 && d[0] == 255.0) {
									if(awalX.x == -1 && awalX.y == -1) {
										if(ds.length > 0 && ds[0] > 127.0) {
											awalX.x = j;
											awalX.y = i;
										}
									}
									else if(i == awalX.y || i + 1 == awalX.y || i - 1 == awalX.y || i + 2 == awalX.y || i - 2 == awalX.y || i + 3 == awalX.y || i - 3 == awalX.y) {	
										if(ds2.length > 0 && ds2[0] > 127.0) {
											akhirX.x = j;
											akhirX.y = i;
										}
										
										if(ds.length > 0 && ds[0] > 127.0) {
											if(j < awalX.x) {
												awalX.x = j;
												awalX.y = i;
											}
										}
									}
									
									if(i < awalX.y && j > awalX.x) {
										akhirY.x = j;
										akhirY.y = i;
										
										awalY.y = i;
										for(int k=(int) akhirY.x; k>awalX.x; k--) {
											double ddd[] = matSobelVertical.get((int) akhirY.y, k);
											double ddd1[] = matSobelVertical.get((int) akhirY.y - 1, k);
											double ddd2[] = matSobelVertical.get((int) akhirY.y + 1, k);
											double ddd3[] = matSobelVertical.get((int) akhirY.y - 2, k);
											double ddd4[] = matSobelVertical.get((int) akhirY.y + 2, k);
											if((ddd.length > 0 && ddd[0] == 255.0) || (ddd1.length > 0 && ddd1[0] == 255.0) || (ddd2.length > 0 && ddd2[0] == 255.0) || (ddd3.length > 0 && ddd3[0] == 255.0) || (ddd4.length > 0 && ddd4[0] == 255.0)) {
												awalY.x = k;
											}
										}
									}
								}
							}
						}						
						
						int cnt = 0;
						int aa = 0, bb = 0;
						noteCoordCressVertical.clear();
						noteCoordVertical.clear();
						for(int i = (int) (awalX.x + 10); i < akhirX.x - 5; i++) {
							double dd[] = matSobelVertical.get((int) (awalX.y - 3), i);
							double dd2[] = resBinaryVertical.get((int) (awalX.y - 3), i + 3);
							double dd3[] = resBinaryVertical.get((int) (awalX.y - 3), i - 3);
							if(dd[0] == 255.0) {
								if(cnt == 0) {
									if(dd2.length > 0 && dd2[0] == 255.0) {
										cnt++;
										aa = i;
									}
								}
								else if(cnt == 1) {
									if(dd3.length > 0 && dd3[0] == 255.0) {
										cnt++;
									}
								}
							}
							if(cnt == 2) {
								bb = i;
								cnt = 0;
								counterTutsVertical++;
								
								ArrayList<Point> points = new ArrayList<Point>();
								Point a = new Point(aa, awalX.y);
								Point b = new Point(bb, awalX.y);
								
								points.add(a);
								points.add(b);
								
								Imgproc.line(matVertical, a, b, new Scalar(255, 0, 0), 1);
								noteCoordVertical.add(points);
							}
						}
						
						int idxSearch = 0;
						for(int i = (int) (awalX.x + 10); i < akhirX.x - 5; i++) {
							double dd[] = matSobelVertical.get((int) (awalX.y - Math.abs(awalX.y - awalY.y) / 2), i);
							double dd2[] = resBinaryVertical.get((int) (awalX.y - Math.abs(awalX.y - awalY.y) / 2), i + 2);
							double dd3[] = resBinaryVertical.get((int) (awalX.y - Math.abs(awalX.y - awalY.y) / 2), i - 2);
							if(dd[0] == 255.0) {
								if(cnt == 0) {
									if(dd2.length > 0 && dd2[0] == 255.0) {
										cnt++;
										aa = i;
									}
								}
								else if(cnt == 1) {
									if(dd3.length > 0 && dd3[0] == 255.0) {
										cnt++;
									}
								}
							}
							if(cnt == 2) {
								bb = i;
								cnt = 0;
								counterTutsVertical2++;
								
								if(idxSearch < noteCoordVertical.size()) {
									ArrayList<Point> tempPoints = noteCoordVertical.get(idxSearch);
									Point a = new Point(aa, (int) (awalX.y - Math.abs(awalX.y - awalY.y) / 2));
									Point b = new Point(bb, (int) (awalX.y - Math.abs(awalX.y - awalY.y) / 2));
									
									tempPoints.add(a);
									tempPoints.add(b);
									
									Imgproc.line(matVertical, a, b, new Scalar(0, 0, 255), 1);
									
									noteCoordVertical.set(idxSearch, tempPoints);
									idxSearch++;
								}
							}
						}
						
						System.out.println(counterTutsVertical + " " + counterTutsVertical2);
						
						double lebarTuts1 = Math.abs(akhirX.x - awalX.x);
						lebarTuts1 /= 14;
						double lebarTuts2 = Math.abs(akhirY.x - awalY.x);
						lebarTuts2 /= 14;
//						noteCoordVertical.addAll(noteCoordCressVertical);
						for(int i=0; i<14; i++) {
							ArrayList<Point> points = new ArrayList<Point>();
							Point a = new Point(awalX.x + (i * lebarTuts1), awalX.y);
							Point b = new Point(awalX.x + ((i + 1) * lebarTuts1), awalX.y);
							Point c = new Point(awalY.x + (i * lebarTuts2), akhirY.y);
							Point d = new Point(awalY.x + ((i + 1) * lebarTuts2), akhirY.y);
							points.add(a);
							points.add(b);
							points.add(c);
							points.add(d);
							
							noteCoordVertical.add(points);
//							noteCoordVertical.set(i + 10, asd);
							Imgproc.line(matVertical, a, b, new Scalar(0, 255, 0), 1);
							Imgproc.line(matVertical, c, d, new Scalar(0, 255, 0), 1);
						}
						
//						System.out.println(noteCoordVertical.size());
//						for(int i=0; i<noteCoordVertical.size(); i++) {
//							ArrayList<Point> asd = noteCoordVertical.get(i);
//							if(asd.size() == 4) {
//								Point a = asd.get(0);
//								Point b = asd.get(1);
//								Point c = asd.get(2);
//								Point d = asd.get(3);
//								
//								Imgproc.line(matVertical, a, b, new Scalar(0, 255, 0));
//								Imgproc.line(matVertical, c, d, new Scalar(0, 255, 0));
//							}
//						}
						
						Imgproc.line(matVertical, awalY, akhirY, new Scalar(255, 0, 0));
						Imgproc.line(matVertical, awalX, akhirX, new Scalar(0, 255, 0));
						BufferedImage imgResultVertical= this.getImage(matVertical);
						Graphics g3 = panel3.getGraphics();
						g3.drawImage(imgResultVertical, 0, 0, contentPane);
						
						BufferedImage imgSobelVertical= this.getImage(matSobelVertical);
						Graphics g2 = panel2.getGraphics();
						g2.drawImage(imgSobelVertical, 0, 0, contentPane);
					}
				}
				
				ArrayList<MatOfPoint> contoursHorizontal = new ArrayList<MatOfPoint>();
				Imgproc.findContours(matBinaryHorizontal, contoursHorizontal, hierarchy, Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);
				int counterTutsHorizontal = 0;
				if(contoursHorizontal.size() > 0) {
					int index = -1;
					double maxArea = 0.0;
					for(int i=0; i<contoursHorizontal.size(); i++) {
						if(maxArea < Imgproc.contourArea(contoursHorizontal.get(i))) {
							Rect rect = Imgproc.boundingRect(contoursHorizontal.get(i));
//							if(rect.y <= 100 && rect.y + rect.height <= 100) {
								maxArea = Imgproc.contourArea(contoursHorizontal.get(i));
								index = i;
//							}
						}
					}
					
					if(index != -1) {
						Rect rect = Imgproc.boundingRect(contoursHorizontal.get(index));
						
						Mat matSobelHorizontal = this.sobelFilter2(matHorizontal, rect);
						
						Point awalX = new Point(-1, -1);
						Point awalY = new Point(-1, -1);
						Point akhirX = new Point(-1, -1);
						Point akhirY = new Point(-1, -1);
						for(int i=rect.y + rect.height - 10 ; i >= rect.y + 3; i--) {
							for(int j=rect.x; j<rect.x + rect.width - 3; j++) {
								double d[] = matSobelHorizontal.get(i, j);
								
								double ds[] = matSobelHorizontal.get(i, j + 3);
								double ds2[] = matSobelHorizontal.get(i, j - 3);
								
								double db[] = resBinaryHorizontal.get(i - 1, j);
								double db2[] = resBinaryHorizontal.get(i + 1, j);
								double db3[] = resBinaryHorizontal.get(i, j + 1);
								double db4[] = resBinaryHorizontal.get(i, j - 1);
								double db5[] = resBinaryHorizontal.get(i + 1, j - 1);
								if(d.length > 0 && d[0] >= 100.0) {
									if(awalX.x == -1 && awalX.y == -1) {
										if(ds.length > 0 && ds[0] > 127.0) {
											awalX.x = j;
											awalX.y = i;
										}
									}
									else if(i == awalX.y || i + 1 == awalX.y || i - 1 == awalX.y || i + 2 == awalX.y || i - 2 == awalX.y || i + 3 == awalX.y || i - 3 == awalX.y) {	
										if(ds2.length > 0 && ds2[0] > 127.0) {
											akhirX.x = j;
											akhirX.y = i;
										}
										
										if(ds.length > 0 && ds[0] > 127.0) {
											if(j < awalX.x) {
												awalX.x = j;
												awalX.y = i;
											}
										}
									}
									
//									if(i < awalX.y && j > awalX.x && j < akhirX.x - 5) {
//										akhirY.x = j;
//										akhirY.y = i;
//										
//										awalY.y = i;
//										for(int k=(int) akhirY.x; k>awalX.x + 10; k--) {
//											double ddd[] = matSobelHorizontal.get((int) akhirY.y, k);
//											double ddd1[] = matSobelHorizontal.get((int) akhirY.y - 1, k);
//											double ddd2[] = matSobelHorizontal.get((int) akhirY.y + 1, k);
//											double ddd3[] = matSobelHorizontal.get((int) akhirY.y - 2, k);
//											double ddd4[] = matSobelHorizontal.get((int) akhirY.y + 2, k);
//											if((ddd.length > 0 && ddd[0] == 255.0) || (ddd1.length > 0 && ddd1[0] == 255.0) || (ddd2.length > 0 && ddd2[0] == 255.0) || (ddd3.length > 0 && ddd3[0] == 255.0) || (ddd4.length > 0 && ddd4[0] == 255.0)) {
//												awalY.x = k;
//											}
//										}
//									}
								}
//								double d[] = matSobelHorizontal.get(i, j);
//								if(d[0] > 100.0) {
//									if(awalX.x == -1 && awalX.y == -1) {
////										double dd[] = matBinaryHorizontal.get(i - 2, j + 2);
////										double dd2[] = matBinaryHorizontal.get(i + 2, j + 2);
////										if(dd[0] == 0.0  && dd2[0] > 0.0) {
//										awalX.x = j;
//										awalX.y = i;
//									}
//									else if(i == awalX.y || i - 1 == awalX.y || i + 1 == awalX.y || i - 2 == awalX.y || i + 2 == awalX.y) {
//										akhirX.x = j;
//										akhirX.y = i;
//										
//										if(j < awalX.x) {
//											awalX.x = j;
//											awalX.y = i;
//										}
//									}
//								
//									if(awalY.x == -1 && awalY.y == -1) {
////										double dd[] = matBinaryHorizontal.get(i + 2, j + 2);
////										if(dd[0] == 0.0 && j > awalX.x) {
//											awalY.x = j;
//											awalY.y = i;
////										}
//									}
//									else if(i < awalY.y) {
////										double dd[] = resBinaryHorizontal.get(i + 1, j + 1);
////										if(dd[0] == 0.0 && j > awalX.x && j < akhirX.x) {
//										if(j > awalX.x) {
//											awalY.x = j;
//											awalY.y = i;
//										}
//									}
//									
//									if(i == awalY.y || i - 1 == awalY.y || i + 1 == awalY.y || i - 2 == awalY.y || i + 2 == awalY.y) {
//										if(j < akhirX.x - 5 && j > awalX.x + 5) {
//											akhirY.x = j;
//											akhirY.y = i;
//										}
//									}
//								}
							}
						}
						
						for(int i=(int) awalX.x + 10; i<akhirX.x; i++) {
							double d[] = matSobelHorizontal.get((int) (awalX.y - 10), i);
							if(d.length > 0 && d[0] > 100.0) {
								if(awalY.x == -1 && awalY.y == -1) {
									awalY.x = i;
									awalY.y = awalX.y - 10;
								}
								else {
									if(i > awalY.x && i < akhirX.x) {
//										if((int) (awalX.y - 9) == awalY.y || (int) (awalX.y - 11) == awalY.y || (int) (awalX.y - 8) == awalY.y || (int) (awalX.y - 12) == awalY.y) {
											akhirY.x = i;
											akhirY.y = awalX.y - 10;
//										}
									}
								}
							}
						}
						
						double lebarTuts1 = Math.abs(akhirX.x - awalX.x);
						lebarTuts1 /= 14;
						double lebarTuts2 = Math.abs(akhirY.x - awalY.x);
						lebarTuts2 /= 14;
						noteCoordHorizontal.clear();
//						noteCoordHorizontal.addAll(noteCoordCressHorizontal);
						for(int i=0; i<14; i++) {
							ArrayList<Point> points = new ArrayList<Point>();
							Point a = new Point(awalX.x + (i * lebarTuts1), awalX.y);
							Point b = new Point(awalX.x + ((i + 1) * lebarTuts1), awalX.y);
							Point c = new Point(awalY.x + (i * lebarTuts2), akhirY.y);
							Point d = new Point(awalY.x + ((i + 1) * lebarTuts2), akhirY.y);
							points.add(a);
							points.add(b);
							points.add(c);
							points.add(d);
							
							noteCoordHorizontal.add(points);
//							Imgproc.line(matHorizontal, a, b, new Scalar(255, 0, 0));
//							Imgproc.line(matHorizontal, c, d, new Scalar(255, 0, 0));
						}
						
						
//						Imgproc.line(matHorizontal, new Point(awalX.x + 35, awalX.y - 4), new Point(akhirX.x - 15, akhirX.y - 4), new Scalar(255, 0, 0));
						Imgproc.line(matHorizontal, awalY, akhirY, new Scalar(255, 0, 0), 1);
						Imgproc.line(matHorizontal, awalX, akhirX, new Scalar(0, 255, 0), 1);
					
						BufferedImage imgResultHorizontal = this.getImage(matHorizontal);
						Graphics g6 = panel6.getGraphics();
						g6.drawImage(imgResultHorizontal, 0, 0, contentPane);
						
						BufferedImage imgSobelHorizontal = this.getImage(matSobelHorizontal);
						Graphics g5 = panel5.getGraphics();
						g5.drawImage(imgSobelHorizontal, 0, 0, contentPane);
					}
				}
//				
//				System.out.println(counterTutsVertical + " " + counterTutsHorizontal);
				// counterTutsVertical = 20;
				// counterTutsHorizontal = 20;
				if(counterTutsVertical == 10 && counterTutsVertical2 == 10) {
					if(startPiano) {
						pianoHorizontal = true;
						pianoVertical = true;
						panelHSV.setVisible(true);
						panelWhiteThreshold.setVisible(false);
					}
				}
			}
		}
	}
	
	private BufferedImage getImage(Mat in) {
        if(in.channels() == 1) {
        	BufferedImage out;
            byte[] data = new byte[in.cols() * in.rows() * (int)in.elemSize()];
            int type = BufferedImage.TYPE_BYTE_GRAY;

            in.get(0, 0, data);
        	
        	out = new BufferedImage(in.cols(), in.rows(), type);

            out.getRaster().setDataElements(0, 0, in.cols(), in.rows(), data);
            return out;
        }
        else {
        	BufferedImage out;
            byte[] data = new byte[in.cols() * in.rows() * (int)in.elemSize()];
            int type = BufferedImage.TYPE_3BYTE_BGR;
            
            Imgproc.cvtColor(in, in, Imgproc.COLOR_BGR2RGB);
            in.get(0, 0, data);
        	
        	out = new BufferedImage(in.cols(), in.rows(), type);

            out.getRaster().setDataElements(0, 0, in.cols(), in.rows(), data);
            return out;
        }   
	}
	
	private boolean checkInPolygon(Point a, Point b, Point c, Point d, double[] dd) {
		double sumAngle = 0.0;
//		a.x = 100.0;
//		b.x = 150.0;
//		c.x = 100.0;
//		d.x = 150.0;
//		
//		a.y = 50.0;
//		b.y = 50.0;
//		c.y = 100.0;
//		d.y = 100.0;
//		
//		dd[0] = 125.0;
//		dd[1] = 75.0;
//		System.out.println(a.x + " " + a.y);
//		System.out.println(b.x + " " + b.y);
//		System.out.println(c.x + " " + c.y);
//		System.out.println(d.x + " " + d.y);
		
		double xa = a.x - dd[0];
		double ya = a.y - dd[1];
		double xb = b.x - dd[0];
		double yb = b.y - dd[1];
		double xc = c.x - dd[0];
		double yc = c.y - dd[1];
		double xd = d.x - dd[0];
		double yd = d.y - dd[1];
		
		double pab = Math.acos((xa * xb + ya * yb) / (Math.sqrt(xa * xa + ya * ya) * Math.sqrt(xb * xb + yb * yb)));
		double pbd = Math.acos((xb * xd + yb * yd) / (Math.sqrt(xb * xb + yb * yb) * Math.sqrt(xd * xd + yd * yd)));
		double pdc = Math.acos((xc * xd + yc * yd) / (Math.sqrt(xc * xc + yc * yc) * Math.sqrt(xd * xd + yd * yd)));
		double pca = Math.acos((xc * xa + yc * ya) / (Math.sqrt(xc * xc + yc * yc) * Math.sqrt(xa * xa + ya * ya)));
		
		sumAngle += Math.toDegrees(pab);
		sumAngle += Math.toDegrees(pbd);
		sumAngle += Math.toDegrees(pdc);
		sumAngle += Math.toDegrees(pca);
//		sumAngle += Math.acos(Math.abs((xa * xb + ya * yb) / Math.sqrt(xa * xa + ya * ya) / Math.sqrt(xb * xb + yb * yb))) * 180 / Math.PI;
//		sumAngle += Math.acos(Math.abs((xb * xc + yb * yc) / Math.sqrt(xb * xb + yb * yb) / Math.sqrt(xc * xc + yc * yc))) * 180 / Math.PI;
//		sumAngle += Math.acos(Math.abs((xc * xd + yc * yd) / Math.sqrt(xc * xc + yc * yc) / Math.sqrt(xd * xd + yd * yd))) * 180 / Math.PI;
//		sumAngle += Math.acos(Math.abs((xd * xa + yd * ya) / Math.sqrt(xd * xd + yd * yd) / Math.sqrt(xa * xa + ya * ya))) * 180 / Math.PI;
//		sumAngle += Math.toDegrees(Math.acos(Math.abs((xa * xb + ya * yb) / Math.sqrt(xa * xa + ya * ya) / Math.sqrt(xb * xb + yb * yb))));
//		sumAngle += Math.toDegrees(Math.acos(Math.abs((xb * xd + yb * yd) / Math.sqrt(xb * xb + yb * yb) / Math.sqrt(xd * xd + yd * yd))));
//		sumAngle += Math.toDegrees(Math.acos(Math.abs((xc * xd + yc * yd) / Math.sqrt(xc * xc + yc * yc) / Math.sqrt(xd * xd + yd * yd))));
//		sumAngle += Math.toDegrees(Math.acos(Math.abs((xc * xa + yc * ya) / Math.sqrt(xc * xc + yc * yc) / Math.sqrt(xa * xa + ya * ya))));
//		System.out.println(Math.toDegrees(Math.acos((xa * xb + ya * yb) / Math.sqrt(xa * xa + ya * ya) / Math.sqrt(xb * xb + yb * yb))));
//		System.out.println(Math.toDegrees(Math.acos((xb * xd + yb * yd) / Math.sqrt(xb * xb + yb * yb) / Math.sqrt(xd * xd + yd * yd))));
//		System.out.println(Math.toDegrees(Math.acos((xc * xd + yc * yd) / Math.sqrt(xc * xc + yc * yc) / Math.sqrt(xd * xd + yd * yd))));
//		System.out.println(Math.toDegrees(Math.acos((xc * xa + yc * ya) / Math.sqrt(xc * xc + yc * yc) / Math.sqrt(xa * xa + ya * ya))));
		// System.out.println(sumAngle);
		// System.out.println();
//		return Math.abs(Math.abs(sumAngle) - 2 * Math.PI) < 1E-9;
		
		return Math.abs(sumAngle - 360) <= 1E-9;
	}
	
	private double[] RGBtoHSV(double[] rgb) {
		rgb[0] /= 255; rgb[1] /= 255; rgb[2] /= 255;

		double temp0 = rgb[0];
		rgb[0] = rgb[2];
		rgb[2] = temp0;
		double res[] = {0.0, 0.0, 0.0};
		double maks, min, delta;
		
		min = Math.min(rgb[0], Math.min(rgb[1], rgb[2]));
		maks = Math.max(rgb[0], Math.max(rgb[1], rgb[2]));
		res[2] = maks;
		delta = maks - min;
		
		if(maks != 0) {
			res[1] = delta / maks;
		}
		else {
			res[1] = 0.0;
			res[2] = -1.0;
			return res;
		}
		
		if(rgb[0] == maks) {
			res[0] = Math.abs(rgb[1] - rgb[2]) / delta;
		}
		else if(rgb[1] == maks) {
			res[0] = 2 + (Math.abs(rgb[2] - rgb[0]) / delta);
		}
		else {
			res[0] = 4 + (Math.abs(rgb[0] - rgb[1]) / delta);
		}
		
		res[0] *= 60;
		if(res[0] < 0) {
			res[0] += 360;
		}

		return res;
	}
	
	private Mat colorDetectHand(Mat image) {
		Mat res = new Mat(image.size(), CvType.CV_8UC1);
		int r = image.rows();
		int c = image.cols();
		for(int i=0; i<r; i++) {
			for(int j=0; j<c; j++) {
				double rgb[] = image.get(i, j);
				double hsv[] = this.RGBtoHSV(rgb);
				double hue = hsv[0];
				double saturation = hsv[1];
				double value = hsv[2];
				
				if(hue >= minHue && hue <= maxHue) {
					if(saturation >= minSaturation && saturation <= maxSaturation) {
						if(value >= minValue && value <= maxValue) {
							double data[] = {0.0, 0.0, 0.0};
							data[0] = 255.0;
							data[1] = 255.0;
							data[2] = 255.0;
							res.put(i, j, data);
						}
						else {
							double data[] = {0.0, 0.0, 0.0};
							data[0] = 0.0;
							data[1] = 0.0;
							data[2] = 0.0;
							res.put(i, j, data);
						}
					}
					else {
						double data[] = {0.0, 0.0, 0.0};
						data[0] = 0.0;
						data[1] = 0.0;
						data[2] = 0.0;
						res.put(i, j, data);
					}
				}
				else {
					double data[] = {0.0, 0.0, 0.0};
					data[0] = 0.0;
					data[1] = 0.0;
					data[2] = 0.0;
					res.put(i, j, data);
				}
			}
		}
		
		return res;
	}

	private Mat colorDetectPaper(Mat image) {
		Mat res = new Mat(image.size(), CvType.CV_8UC1);
		int r = image.rows();
		int c = image.cols();
		
		for(int i=0; i<r; i++) {
			for(int j=0; j<c; j++) {
				double rgb[] = image.get(i, j);
				double hsv[] = this.RGBtoHSV(rgb);
				double hue = hsv[0];
				double saturation = hsv[1];
				double value = hsv[2];
				
				if(hue >= 0.0 && hue <= 50.0) {
					if(saturation >= 0.20 && saturation <= 0.68) {
						if(value >= 0.35 && value <= 1.0) {
							double data[] = {0.0, 0.0, 0.0};
							data[0] = 255.0;
							data[1] = 255.0;
							data[2] = 255.0;
							res.put(i, j, data);
						}
						else {
							double data[] = {0.0, 0.0, 0.0};
							data[0] = 0.0;
							data[1] = 0.0;
							data[2] = 0.0;
							res.put(i, j, data);
						}
					}
					else {
						double data[] = {0.0, 0.0, 0.0};
						data[0] = 0.0;
						data[1] = 0.0;
						data[2] = 0.0;
						res.put(i, j, data);
					}
				}
				else {
					double data[] = {0.0, 0.0, 0.0};
					data[0] = 0.0;
					data[1] = 0.0;
					data[2] = 0.0;
					res.put(i, j, data);
				}
			}
		}
		
		return res;
	}
	
	private Mat sobelFilter(Mat image, Mat imageBinary) {
		Mat res = new Mat(image.rows(), image.cols(), CvType.CV_8UC1);
		int r = image.rows();
		int c = image.cols();
		double data[] = new double[1];
		for(int i=1; i<r - 1; i++) {
			for(int j=1; j<c - 1; j++) {
				double rgb[] = image.get(i, j);
//				if(rgb[0] <= 30 && rgb[1] <= 30 && rgb[2] <= 30) {
					double rgb1[] = image.get(i - 1, j - 1);
					double rgb2[] = image.get(i, j - 1);
					double rgb3[] = image.get(i + 1, j - 1);
					double rgb4[] = image.get(i - 1, j);
					double rgb6[] = image.get(i + 1, j);
					double rgb7[] = image.get(i - 1, j + 1);
					double rgb8[] = image.get(i, j + 1);
					double rgb9[] = image.get(i + 1, j + 1);
					
					double pixel1 = (rgb1[0] + rgb1[1] + rgb1[2]) / 3;
					double pixel2 = (rgb2[0] + rgb2[1] + rgb2[2]) / 3;
					double pixel3 = (rgb3[0] + rgb3[1] + rgb3[2]) / 3;
					double pixel4 = (rgb4[0] + rgb4[1] + rgb4[2]) / 3;
					double pixel6 = (rgb6[0] + rgb6[1] + rgb6[2]) / 3;
					double pixel7 = (rgb7[0] + rgb7[1] + rgb7[2]) / 3;
					double pixel8 = (rgb8[0] + rgb8[1] + rgb8[2]) / 3;
					double pixel9 = (rgb9[0] + rgb9[1] + rgb9[2]) / 3;
					
					double gradientX = -1 * pixel1 + -2 * pixel2 + -1 * pixel3 + 1 * pixel7 + 2 * pixel8 + 1 * pixel9;
					double gradientY = -1 * pixel1 + -2 * pixel4 + -1 * pixel7 + 1 * pixel3 + 2 * pixel6 + 1 * pixel9;
//					double gradient = Math.sqrt(gradientX * gradientX + gradientY * gradientY);
					double gradient = Math.abs(gradientX + gradientY);
					
					data[0] = gradient;
					if(gradient > 255.0) {
						data[0] = 255.0;
					}
					else if(gradient < 0.0) {
						data[0] = 0.0;
					}
					res.put(i, j, data);
//				}
//				else {
//					data[0] = 0;
//					res.put(i, j, data);
//				}
			}
		}
		
		data[0] = 0;
		res.put(0, 0, data);
		res.put(r - 1, c - 1, data);
		return res;
	}

	private Mat sobelFilter2(Mat image, Rect bounding) {
		Mat res = new Mat(image.rows(), image.cols(), CvType.CV_8UC1);
		int r = image.rows();
		int c = image.cols();
		double data[] = new double[1];
		for(int i=1; i<r-1; i++) {
			for(int j=1; j<c-1; j++) {
				if(j >= bounding.x && j <= bounding.x + bounding.width && i >= bounding.y && i <= bounding.y + bounding.height) {
					double d[] = image.get(i,  j + 1);
					double d2[] = image.get(i, j - 1);
//					if((d[0] == 255.0 && d2[0] == 0.0) || (d2[0] == 255.0 && d[0] == 0.0)) {
					double rgb[] = image.get(i, j);
					double rgb1[] = image.get(i - 1, j - 1);
					double rgb2[] = image.get(i, j - 1);
					double rgb3[] = image.get(i + 1, j - 1);
					double rgb4[] = image.get(i - 1, j);
					double rgb6[] = image.get(i + 1, j);
					double rgb7[] = image.get(i - 1, j + 1);
					double rgb8[] = image.get(i, j + 1);
					double rgb9[] = image.get(i + 1, j + 1);
					
					double pixel1 = (rgb1[0] + rgb1[1] + rgb1[2]) / 3;
					double pixel2 = (rgb2[0] + rgb2[1] + rgb2[2]) / 3;
					double pixel3 = (rgb3[0] + rgb3[1] + rgb3[2]) / 3;
					double pixel4 = (rgb4[0] + rgb4[1] + rgb4[2]) / 3;
					double pixel6 = (rgb6[0] + rgb6[1] + rgb6[2]) / 3;
					double pixel7 = (rgb7[0] + rgb7[1] + rgb7[2]) / 3;
					double pixel8 = (rgb8[0] + rgb8[1] + rgb8[2]) / 3;
					double pixel9 = (rgb9[0] + rgb9[1] + rgb9[2]) / 3;
					
					double gradientX = -1 * pixel1 + -2 * pixel2 + -1 * pixel3 + 1 * pixel7 + 2 * pixel8 + 1 * pixel9;
					double gradientY = -1 * pixel1 + -2 * pixel4 + -1 * pixel7 + 1 * pixel3 + 2 * pixel6 + 1 * pixel9;
					double gradient = Math.abs(gradientX + gradientY);
					
					data[0] = gradient;
					if(gradient > 255.0) {
						data[0] = 255.0;
					}
					else if(gradient < 0.0) {
						data[0] = 0.0;
					}
					res.put(i, j, data);
//					}
//					else {
//						data[0] = 0.0;
//						res.put(i, j, data);	
//					}
				}
				else {
					data[0] = 0.0;
					res.put(i, j, data);
				}
			}
		}
		
		data[0] = 0;
		res.put(0, 0, data);
		res.put(r - 1, c - 1, data);
		return res;
	}
		
	// METHOD TO FIND CONVEX HULL
	// GRAHAM SCAN ALGORITHM
	// parameter :
	// 		- biggest contour of hand (ROI) : MatOfPoint
	// return :
	// 		- convex hull point : MatOfPoint
	private MatOfPoint convexHull(MatOfPoint contour) {
		ArrayList<Point> points = new ArrayList<Point>();
		points.addAll(0, contour.toList());
		
		if(points.size() > 0) {
			MatOfPoint result = new MatOfPoint();
			int i, j, n = points.size();
			
			if(n <= 3) {
				if(!(points.get(0) == points.get(n - 1))) points.add(points.get(0));
				result.fromList(points);
				return result;
			}
			
			int PO = 0;
			for(i = 1; i<n; i++) {
				if(points.get(i).y < points.get(PO).y || 
						(points.get(i).y == points.get(PO).y && 
						points.get(i).x > points.get(PO).x)) {
					PO = i;
				}
			}
			
			Point temp = points.get(0);
			points.set(0, points.get(PO));
			points.set(PO, temp);
			
			Point pivot = points.get(0);
			this.sortingPoint(pivot, points);
			
			ArrayList<Point> p = new ArrayList<Point>();
			p.add(points.get(n - 1));
			p.add(points.get(0));
			p.add(points.get(1));
			
			i = 2;
			while(i < n) {
				j = p.size() - 1;
				if(j > 0) {
					if(ccw(p.get(j - 1), p.get(j), points.get(i))) {
						p.add(points.get(i));
						i++;
					}
					else {
						p.remove(j);
					}
				}
				else {
					return result;
				}
			}
			
			result.fromList(p);
			return result;
		}
		else {
			return null;
		}
	}
	
	private boolean ccw(Point a, Point b, Point c) {
		double abx = b.x - a.x;
		double aby = b.y - a.y;
		double acx = c.x - a.x;
		double acy = c.y - a.y;
		
		return (abx * acy - aby * acx) > 0;
	}
	
	private void sortingPoint(Point pivot, ArrayList<Point> p) {
		for(int i=1; i<p.size() - 1; i++) {
			for(int j=2; j<(p.size() - i); j++) {
				if(angleCmp(pivot, p.get(i), p.get(j))) {
					Point temp = p.get(i);
					p.set(i, p.get(j));
					p.set(j, temp);
				}
			}
		}
	}
	
	private boolean angleCmp(Point pivot, Point a, Point b) {
		double dx1 = a.x - pivot.x;
		double dy1 = a.y - pivot.y;
		
		double dx2 = b.x - pivot.x;
		double dy2 = b.y - pivot.y;
		
		if(this.collinear(pivot, a, b)) {			
			return Math.sqrt(dx1 * dx1 + dy1 * dy1) < Math.sqrt(dx2 * dx2 + dy2 * dy2);
		}
		
		return (Math.atan2(dy1,  dx1) - Math.atan2(dy2, dx2)) < 0;
	}
	
	private boolean collinear(Point p, Point q, Point r) {
		double pqx = q.x - p.x;
		double pqy = q.y - p.y;
		double prx = r.x - p.x;
		double pry = r.y - p.y;
		
		return Math.abs(pqx * pry - pqy * prx) < 1E-9;
	}

	
	
	// METHOD TO CONVERT CONVEX HULL POINT FROM MATOFINT TO MATOFPOINT
	// parameters :
	//		- convex hull point : MatOfInt
	// 		- biggest contour of hand (ROI) : MatOfPoint
	MatOfPoint hull2Points(MatOfInt hull, MatOfPoint contour) {
	    ArrayList<Integer> indexes = new ArrayList<Integer>();
	    indexes.addAll(0, hull.toList());
	    ArrayList<Point> points = new ArrayList<>();
	    MatOfPoint point= new MatOfPoint();
	    for(Integer index:indexes) {
	        points.add(contour.toList().get(index));
	    }
	    point.fromList(points);
	    return point;
	}
}
