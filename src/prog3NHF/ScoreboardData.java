package prog3NHF;

import java.util.ArrayList;

import java.util.List;

import javax.swing.table.AbstractTableModel;


public class ScoreboardData extends AbstractTableModel{
	
	List<Scoreboard> scoreboard = new ArrayList<Scoreboard>(); 
	
	public int getRowCount() {
		return scoreboard.size();
	}
	
	public int getColumnCount() {
		return 3;
	}
	
	public List<Scoreboard> removeDuplicates() {
		List<Scoreboard> newList = new ArrayList<Scoreboard>();
		boolean contains; 
		for (int i = 0; i < scoreboard.size(); i++) {
			String tmp = scoreboard.get(i).getName();
			contains = false;
			for(int j = 0; j < newList.size(); j++) {
				if (tmp.equals(newList.get(j).getName())) {
					contains = true;
					break;
				}
			}
			if(!contains) 
				newList.add(scoreboard.get(i));	
		}
		return newList;
	}
	
	public String getColumnName(int columnIndex) {
		switch (columnIndex) {
		case 0: return "Place";
		case 1: return "Name";
		case 2: return "Score";
		default: return "empty";
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Scoreboard sb = scoreboard.get(rowIndex);
		switch (columnIndex) {
		case 0: return sb.getPlace();
		case 1: return sb.getName();
		default: return sb.getScore();
		}
	}

}
