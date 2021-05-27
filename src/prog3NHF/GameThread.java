package prog3NHF;

public class GameThread extends Thread{
	private Game g;
	private int speed;
	private volatile boolean stopSignal = false;
	
	public GameThread(Game game, int s) {
		speed = s;
		g = game;
	}
	
	public void start() {
		stopSignal = false;
		run();
	}
	
	public void run() {
		try {
			while(!stopSignal) {
				stopSignal = !(g.getTetris().step());
				g.getVisual().repaint();
				if (!stopSignal)
					sleep(speed);
				speed -= 1;
			}
			if (stopSignal)
				g.endGame();
			
		} catch (InterruptedException e) {
				e.printStackTrace();	
		}
	}
	
	public boolean getStopSignal() {
		return stopSignal;
	}
}
