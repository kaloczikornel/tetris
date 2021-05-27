package prog3NHF;

import java.awt.Dimension;

import java.awt.Point;
import java.awt.event.KeyEvent;

import org.junit.Assert;
import org.junit.Before;

import org.junit.Test;

public class KeyTest {
	
	Game g;
	GameFrame GF;
	Point[] p ={ new Point(0, 0), new Point(0, 0), new Point(0, 0), new Point(0, 0) };
	
	@Before
	public void setUp() {
		g = new Game(new Dimension(12,21));
		GF = new GameFrame();
		GF.startGame(g);
		for (int i = 0; i < 4; i++)
			p[i].setLocation(g.getTetris().getTetromino()[i]);
		
		
	}

	@Test
	public void testDown() {
		boolean result = false;
		KeyEvent down = new KeyEvent(GF, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,KeyEvent.VK_DOWN,KeyEvent.CHAR_UNDEFINED);
		GF.dispatchEvent(down);
		for (int i = 0; i < 4; i++)
			if (g.getTetris().getTetromino()[i].y == p[i].y + 1)
				result  = true;
			else {
				result = false;
				break;
			}
		Assert.assertEquals(true, result);
	}
	
	@Test
	public void testLeftRight() {
		boolean result = false;
		KeyEvent left = new KeyEvent(GF, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,KeyEvent.VK_LEFT,KeyEvent.CHAR_UNDEFINED);
		KeyEvent right = new KeyEvent(GF, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,KeyEvent.VK_RIGHT,KeyEvent.CHAR_UNDEFINED);
		GF.dispatchEvent(left);
		for (int i = 0; i < 4; i++)
			if (g.getTetris().getTetromino()[i].x == p[i].x - 1)
				result  = true;
			else {
				result = false;
				break;
			}
		Assert.assertEquals(true, result);
		GF.dispatchEvent(right);
		for (int i = 0; i < 4; i++)
			if (g.getTetris().getTetromino()[i].x == p[i].x)
				result  = true;
			else {
				result = false;
				break;
			}
		Assert.assertEquals(true, result);
	}
	

}
