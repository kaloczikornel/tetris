package prog3NHF;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyhandler implements KeyListener{
	private Game g;
	
	public Keyhandler(Game game) {
		g = game;
	}
		
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			g.getTetris().rotate();
			g.getVisual().repaint();
			break;
		case KeyEvent.VK_DOWN:
			g.getTetris().move(new Point(0,1));
			g.getTetris().addScore(10);
			g.getVisual().repaint();
			break;
		case KeyEvent.VK_LEFT:
			g.getTetris().move(new Point(-1,0));
			g.getVisual().repaint();
			break;
		case KeyEvent.VK_RIGHT:
			g.getTetris().move(new Point(1,0));
			g.getVisual().repaint();
			break;
		} 
	}
		
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
}
