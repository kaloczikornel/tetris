package prog3NHF;

import org.junit.Assert;
import java.awt.Dimension;
import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

public class TetrisTest {

	Tetris t;
	
	@Before
	public void setUp() {
		t = new Tetris(new Dimension(12,21)); 
	}
	
	@Test
	public void testMove() {
		boolean result = false;
		Point[] p = { new Point(0, 0), new Point(0, 0), new Point(0, 0), new Point(0, 0) };
		for (int i = 0; i < 4; i++)
			p[i].setLocation(t.getTetromino()[i]);
		t.move(new Point(0,1));
		for (int i = 0; i < 4; i++)
			if (t.getTetromino()[i].y == p[i].y + 1)
				result  = true;
			else {
				result = false;
				break;
			}
		Assert.assertEquals(true, result);
	}
	
	@Test 
	public void testEnd() {
		t.placeToField();
		boolean canMove = t.step();
		Assert.assertEquals(false, canMove);
	}
	
	

}
