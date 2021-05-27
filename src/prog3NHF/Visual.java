package prog3NHF;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;
import java.awt.Point;

public class Visual extends JPanel {
	
	private Tetris t;
	private Dimension fieldDim;
	public static final int shapeSize = 30;
	
	public Visual(Tetris tetris) {
		t = tetris;
		fieldDim = new Dimension(t.getDim());
	}
	
	public void paintShape(Graphics g) {
		g.setColor(t.getColor());
		for(Point p: t.getTetromino())
			g.fillRect(p.x*shapeSize, p.y*shapeSize, shapeSize-1, shapeSize-1);
	}
	
	public void paintNextShape(Graphics g) {
		g.setColor(Color.RED);
		for(Point p: t.getNext())
			g.fillRect((p.x + fieldDim.width + 2)*shapeSize, (p.y + 3)*shapeSize, shapeSize-1, shapeSize-1);
	}
	
	public void paintComponent(Graphics g) {
		g.fillRect(0, 0, (fieldDim.width + 6)*shapeSize, fieldDim.height*shapeSize);
		for (int i = 0; i < fieldDim.width; i++)
			for (int j = 0; j < fieldDim.height; j++) {
				g.setColor(t.getField()[i][j]);
				g.fillRect(i*shapeSize, j*shapeSize, shapeSize-1, shapeSize-1);
			}
		
		g.setFont(new Font("Helvetica", Font.PLAIN, 20));
		g.setColor(Color.WHITE);
		g.drawString("Score: " + t.getScore(), fieldDim.width*30, 25);
		
		paintShape(g);
		paintNextShape(g);
		
	}
	
	
}
