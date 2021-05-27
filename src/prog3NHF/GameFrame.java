package prog3NHF;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Comparator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GameFrame extends JFrame{
	
	private ScoreboardData data;
	
	
	@SuppressWarnings("unchecked")
	public void initData() {
		// Induláskor betöltjük az adatokat
		try {
			data = new ScoreboardData();
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("scores.dat"));
			data.scoreboard = (List<Scoreboard>)ois.readObject();
			ois.close();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
    
		// Bezáráskor mentjük az adatokat és elõtte 
		//rendezzük valamint a duplikációkat kitöröljük
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					data.scoreboard.sort(Comparator.comparing(Scoreboard::getScore).reversed());
		            for (int i = 0; i < data.scoreboard.size(); i++)
		            	data.scoreboard.set(i, new Scoreboard(i+1,data.scoreboard.get(i).getName(),data.scoreboard.get(i).getScore()));
		            data.scoreboard = data.removeDuplicates();
					ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("scores.dat"));
					oos.writeObject(data.scoreboard);
					oos.close();
				} catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		});
	}
	
	public void startGame(Game g) {
		setTitle("Tetris");
		toFront();
		requestFocus();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize((g.getTetris().getDim().width + 1)*30+5*30, (g.getTetris().getDim().height + 1)*30);
		setLocationRelativeTo(null);
		addKeyListener(g.getKey());
		add(g.getVisual());
		setVisible(true);
	}
	
	public void endGame(Game g) {
		setTitle("Game Over");
		toFront();
		requestFocus();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 110);
		setLocationRelativeTo(null);
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JTextField nameField = new JTextField(20);
		JButton b = new JButton("OK!");
		b.addActionListener(l->addName(nameField,g.getTetris().getScore()));
		p1.add(new JLabel("Type your name:"));
		p2.add(nameField);
		p2.add(b);
		add(p1, BorderLayout.NORTH);
		add(p2, BorderLayout.SOUTH);
		setVisible(true);
		
	}
	
	public void addName(JTextField tf, int score) {
		initData();
		data.scoreboard.add(new Scoreboard(1,tf.getText(),score));
		dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		ScoreboardFrame sbf = new ScoreboardFrame();
		sbf.setVisible(true);
		sbf.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	

}
