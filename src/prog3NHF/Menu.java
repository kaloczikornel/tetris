package prog3NHF;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.imageio.ImageIO;
import javax.swing.*;


public class Menu extends JFrame{
	
	private JMenuBar menuBar; 
	private JMenu Play, Settings, CS, Diff, Scoreboard, FieldSize;
	private JMenuItem NG, SCB, Happy, Dark, Easy, Medium, Dont_try, S, M, L;
	private JLabel textLabel;
	private Game g;
	private Dimension fieldDim;
	private int speed = 1000;
	private int colorStyle = 255;
	private String diffString = "Difficulty: Easy ";
	private String colorString = "Color style: Happy ";
	private String fieldString= "Field size: Medium ";
	
	
	
	
	public void initMenu() {
		fieldDim = new Dimension(12,21);
		setTitle("Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600,700);
		setMinimumSize(new Dimension(700, 800));
		setLocationRelativeTo(null);
		
		menuBar = new JMenuBar();
		
		Play = new JMenu("Play");
		NG = new JMenuItem("New Game");
		NG.addActionListener(l->initGame());
		menuBar.add(Play);
		Play.add(NG);
		
		Settings = new JMenu("Settings");
		CS = new JMenu("Color Style");
		Diff = new JMenu("Difficulty");
		FieldSize = new JMenu("Field Size");
		Happy = new JMenuItem("Happy");
		Dark = new JMenuItem("Dark");
		Easy = new JMenuItem("Easy");
		Medium = new JMenuItem("Medium");
		Dont_try = new JMenuItem("Don't try");
		S = new JMenuItem("Small");
		M = new JMenuItem("Medium");
		L = new JMenuItem("Large");
		Happy.addActionListener(l->setColorS(255));
		Dark.addActionListener(l->setColorS(100));
		Easy.addActionListener(l->setDiff(1000));
		Medium.addActionListener(l->setDiff(500));
		Dont_try.addActionListener(l->setDiff(250));
		S.addActionListener(l->setField(new Dimension(10,15)));
		M.addActionListener(l->setField(new Dimension(12,21)));
		L.addActionListener(l->setField(new Dimension(16,27)));
		menuBar.add(Settings);
		Settings.add(CS);
		CS.add(Happy);
		CS.add(Dark);
		Settings.add(Diff);
		Diff.add(Easy);
		Diff.add(Medium);
		Diff.add(Dont_try);
		Settings.add(FieldSize);
		FieldSize.add(S);
		FieldSize.add(M);
		FieldSize.add(L);
		Scoreboard = new JMenu("Scoreboard");
		SCB = new JMenuItem("Scoreboard");
		SCB.addActionListener(l->scoreboard());
		menuBar.add(Scoreboard);
		Scoreboard.add(SCB);

		setLayout(new BorderLayout());
		try {
			add(new JLabel(new ImageIcon(ImageIO.read(new File("pic.jpg")))), BorderLayout.CENTER);
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		textLabel = new JLabel("<html>Current settings:<br> "+diffString+"<br>"+colorString+"<br>"+fieldString+"</html>");
		textLabel.setFont(new Font("Garamond", Font.BOLD, 20));
		textLabel.setForeground(Color.WHITE);
		add(textLabel, BorderLayout.NORTH);
        pack();
		
        getContentPane().setBackground(Color.DARK_GRAY);
		setJMenuBar(menuBar);
		setVisible(true);
	}
	
	public void initGame() {
		setVisible(false);
		g = new Game(fieldDim);
		g.setSpeed(speed);
		g.getTetris().setColorStyle(colorStyle);
		g.initGame();
		
	}
	
	private void writeText() {
		textLabel.setText("<html>Current settings:<br> "+diffString+"<br>"+colorString+"<br>"+fieldString+"</html>");
		textLabel.repaint();
	}
	
	public void setColorS(int c) {
		if (c == 255)
			colorString = "Color style: Happy ";
		else
			colorString = "Color style: Dark ";
		colorStyle = c;
		writeText();
	}
	
	public void scoreboard() {
		setVisible(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		ScoreboardFrame sbf = new ScoreboardFrame();
		sbf.setVisible(true);
		sbf.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
            	setVisible(true);
            	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
	}
	
	public void setDiff(int s) {
		switch (s) {
		case 1000: diffString = "Difficulty: Easy ";
			break;
		case 500: diffString = "Difficulty: Medium ";
			break;
		case 250: diffString = "Difficulty: Don't try ";
			break;
		}
		speed = s;
		writeText();
	}
	
	public void setField(Dimension d) {
		if (d.equals(new Dimension(10,15)))
			fieldString = "Field size: Small ";
		else if (d.equals(new Dimension(12,21)))
			fieldString = "Field size: Medium ";
		else if (d.equals(new Dimension(16,27)))
			fieldString = "Field size: Large ";
		fieldDim.setSize(d);
		writeText();
	}
	
	public Game getGame() {
		return g;
	}
	
	public static void main(String[] args) {
		Menu m = new Menu();
		m.initMenu();
		
	}
	
}
