package prog3NHF;

import java.awt.Color;
import java.awt.Dimension;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MenuTest {
	
	Menu testMenu;
	
	@Before
	public void setUp() {
		testMenu = new Menu();
	}
	

	@Test
	public void testInitMenu() {
		boolean test = false;
		testMenu.initMenu();
		test = testMenu.getContentPane().getBackground() == Color.DARK_GRAY;
		Assert.assertEquals(true, test);
	}
	
	@Test
	public void testInitGame() {
		testMenu.initMenu();
		testMenu.initGame();
		Assert.assertEquals(1000, testMenu.getGame().getSpeed());
	}
	
	@Test
	public void testGameSetters(){
		testMenu.initMenu();
		testMenu.setDiff(389);
		testMenu.setColorS(42);
		testMenu.setField(new Dimension(16,27));
		testMenu.initGame();
		Assert.assertEquals(389, testMenu.getGame().getSpeed());
		Assert.assertEquals(42, testMenu.getGame().getTetris().getColorStyle());
		Assert.assertEquals(27, testMenu.getGame().getTetris().getDim().height);
		Assert.assertEquals(16, testMenu.getGame().getTetris().getDim().width);
	}

}
