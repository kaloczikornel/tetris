package prog3NHF;

import java.awt.*;
import java.util.Random;

public class Tetris implements Steppable{
	private final Point[][] Tetrominos = {
			
			// I-Shape
				{ new Point(-1, 0), new Point(0, 0), new Point(1, 0), new Point(2, 0) },
			
			// J-Shape
				{ new Point(-1, 0), new Point(0, 0), new Point(1, 0), new Point(1, 1) },
	
			// L-Shape
				{ new Point(-1, 0), new Point(0, 0), new Point(1, 0), new Point(-1, 1) },
			
			// O-Shape
				{ new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1) },
			
			// S-Shape
				{ new Point(-1, 0), new Point(0, 0), new Point(0, -1), new Point(1, -1) },
				
			// T-Shape
				{ new Point(-1, 0), new Point(0, 0), new Point(1, 0), new Point(0, -1) },
			
			// Z-Piece
				{ new Point(-1, 0), new Point(0, 0), new Point(0, 1), new Point(1, 1) },
	};
	
	private Color[][] Field;
	private int score = 0;
	private Point[] currentPlace = { new Point(0, 0), new Point(0, 0), new Point(0, 0), new Point(0, 0) };
	private int currentPiece;
	private int nextPiece;
	private Color currentColor;
	private Random r = new Random();
	private int colorStyle = 255;
	private Dimension fieldDim;
	
	
	public Tetris (Dimension d) {
		fieldDim = new Dimension(d);
		Field = new Color[fieldDim.width][fieldDim.height];
		for (int i = 0; i < fieldDim.width; i++)
			for (int j = 0; j < fieldDim.height; j++) {
				if (i == fieldDim.width - 1 || i == 0 || j == fieldDim.height - 1)
					Field[i][j] = Color.DARK_GRAY;
				else
					Field[i][j] = Color.BLACK;
			}
			nextPiece = r.nextInt(7);
			newFallingPiece();
	}
	
	public void initColor() {
		int[] rgb = {r.nextInt(256),r.nextInt(256),r.nextInt(256)} ;
		for (int i = 0; i < 2; i++)
			rgb[i] = (rgb[i] + colorStyle)/2;
		currentColor = new Color(rgb[0], rgb[1], rgb[2]);
	}
	
	public Point[] getTetromino() {
		return currentPlace;
	}
	
	public Point[] getNext() {
		return Tetrominos[nextPiece];
	}
	
	public Color getColor() {
		return currentColor;
	}
	
	public Color[][] getField() {
		return Field;
	}
	
	public int getScore() {
		return score;
	}
	
	public Dimension getDim() {
		return fieldDim;
	}
	
	public void addScore(int a) {
		score += a;
	}
	
	public void setColorStyle(int a) {
		colorStyle = a; 
	}
	
	public int getColorStyle() {
		return colorStyle;
	}
	
	public boolean newFallingPiece() {
		if (!isMapFull()) {
			currentPiece = nextPiece;
			nextPiece = r.nextInt(7);
			for (int i = 0; i < 4; i++)
				currentPlace[i].setLocation(Tetrominos[currentPiece][i]);
			for (Point p: currentPlace) {
				p.translate((fieldDim.width-2)/2, 2);
			}
			initColor();
			return true;
		}
		else
			return false;
	}
	
	public boolean isMapFull() {
		boolean isFull = false;
		Point[] next = { new Point(0, 0), new Point(0, 0), new Point(0, 0), new Point(0, 0) };
		for (int i = 0; i < 4; i++) {
			next[i].setLocation(Tetrominos[nextPiece][i]);
			next[i].translate((fieldDim.width-2)/2, 2);
		}
		try {
			for(Point p: next) 
				if(Field[p.x][p.y] != Color.BLACK)
					isFull = true;
		}catch(ArrayIndexOutOfBoundsException e) {}
		return isFull;
	}
	
	public boolean collidesAt(Point here) throws ArrayIndexOutOfBoundsException{
		for(Point p: currentPlace) 
			if(Field[p.x + here.x][p.y + here.y] != Color.BLACK)
				return true;
		return false;
	}
	
	public void rotate() {
		if (currentPiece != 3) {
			Point referencePoint = new Point();
			referencePoint.setLocation(currentPlace[1]);
			
			Point[] rotated = { new Point(0, 0), new Point(0, 0), new Point(0, 0), new Point(0, 0) };
			for (int i = 0; i < 4; i++) {
				rotated[i].setLocation(currentPlace[i]);
				rotated[i].translate(-referencePoint.x, -referencePoint.y);
				rotated[i].setLocation(-rotated[i].y, rotated[i].x);
			}
			
			boolean canRotate = true;
			for (int i = 0; i < 4; i++) {
				rotated[i].translate(referencePoint.x, referencePoint.y);
			}
			try {
				for(Point p: rotated) 
					if(Field[p.x][p.y] != Color.BLACK)
						canRotate = false;
			}catch(ArrayIndexOutOfBoundsException e) {}
			if (canRotate)
				for (int i = 0; i < 4; i++)
					currentPlace[i].setLocation(rotated[i]);
		}
	}
	
	public boolean move(Point vect) {
		try {
			if (!collidesAt(vect)) {
				for(Point p: currentPlace)
					p.translate(vect.x, vect.y);
				return true;
			}
			else if(vect.equals(new Point (0,1))) {
				placeToField();
				clearRow();
				return newFallingPiece();
			}
		}catch (ArrayIndexOutOfBoundsException e) {}	
		return true;
	}
	
	public void clearRow() {
		boolean isNotFull;
		int clearNum = 0;
		for (int i = fieldDim.height - 2; i >= 0; i--) {
			isNotFull = false;
			for (int j = 1; j < fieldDim.width - 1; j++)
				if (Field[j][i] == Color.BLACK) {
					isNotFull = true;
					break;
			}
			if (!isNotFull) {
				clearNum += 1;
				for (int k = i-1; k >= 0; k--)
					for (int j = 1; j < fieldDim.width - 1; j++)
						Field[j][k + 1] = Field[j][k];
				i += 1;
			}
		}
		score += clearNum*clearNum*100;	 
	}
	
	public void placeToField() {
		for(Point p:currentPlace)
			Field[p.x][p.y] = currentColor;
	}
	
	public boolean step() {
		return move(new Point(0,1));
	}
}

