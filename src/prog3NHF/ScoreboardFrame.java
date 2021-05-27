package prog3NHF;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Comparator;
import java.util.List;

import javax.swing.*;


public class ScoreboardFrame extends JFrame{
	private ScoreboardData data;
	
	private void initComponents() {
		setLayout(new BorderLayout());
		add(new JScrollPane(new JTable(data)), BorderLayout.CENTER);
	}
	
    @SuppressWarnings("unchecked")
	public ScoreboardFrame() {
        super("Scoreboard");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        try {
            data = new ScoreboardData();
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("scores.dat"));
            data.scoreboard = (List<Scoreboard>)ois.readObject();
            ois.close();
            data.scoreboard.sort(Comparator.comparing(Scoreboard::getScore).reversed());
            for (int i = 0; i < data.scoreboard.size(); i++)
            	data.scoreboard.set(i, new Scoreboard(i+1,data.scoreboard.get(i).getName(),data.scoreboard.get(i).getScore()));
            data.scoreboard = data.removeDuplicates();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("scores.dat"));
                    oos.writeObject(data.scoreboard);
                    oos.close();
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        setMinimumSize(new Dimension(500, 200));
        setLocationRelativeTo(null);
        initComponents();
    }
}
