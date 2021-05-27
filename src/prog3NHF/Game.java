package prog3NHF;

import java.awt.Dimension;

public class Game {
	private Visual v;
	private Tetris t;
	private Keyhandler k;
	private int speed = 1000;
	
	public Game(Dimension d) {
		t = new Tetris(d);
		v = new Visual(t);
		k = new Keyhandler(this);
	}
	
	public Tetris getTetris() {
		return t;
	}
	
	public Visual getVisual() {
		return v;
	}
	
	public Keyhandler getKey() {
		return k;
	}
	
	public void setSpeed(int a) {
		speed = a;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void initGame() {
		GameFrame gf = new GameFrame();
		gf.startGame(this);
		
		Thread GT = new Thread(new GameThread(this,speed));
		GT.start();
	}
	
	public void endGame() {
		GameFrame gf = new GameFrame();
		gf.endGame(this);
	}
}
	

