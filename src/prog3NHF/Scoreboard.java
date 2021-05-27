package prog3NHF;

import java.io.Serializable;

public class Scoreboard implements Serializable{
	private int place;
	private String name;
	private int score;
	
	public Scoreboard(int p, String n, int s) {
		place = p;
		name = n;
		score = s; 
	}
	
	public int getPlace() {
		return place;
	}
	
	public String getName() {
		return name;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setPlace(int a) {
		place = a;
	}
	
	public void setName(String n) {
		name = n;
	}
	
	public void setScore(int a) {
		score = a;
	}

}
