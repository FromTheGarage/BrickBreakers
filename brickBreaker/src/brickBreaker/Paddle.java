package brickBreaker;

import java.awt.Color;
import java.awt.event.KeyEvent;

public class Paddle{
	
	//States (Nouns)
	protected int x; //Vertical Position
	protected static int y = 550; //Horizontal Position
	protected static int width = 100;
	protected static int height = 8;
	protected Color color; //Color of Paddle
	//Behaviors (Verbs)- No getters/setters
	
	
	
	
	//Constructor for Paddle
	public Paddle(int x, int y, int width, int height, Color color) {
		this.setX(x);
		Paddle.setY(y);
		Paddle.setWidth(width);
		Paddle.setHeight(height);
		this.setColor(color);	
	}
	
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			if(x < 10) {
				x = 10;
			} else {
				moveLeft();
			}
			
		}
	}
	
	
	public void moveLeft() {
		x -= 20;
	}
	
	public void moveRight(int x) {
		x += 1;
	}
	
	
	
	//Getters and Setters to set and access values
	public void setX(int x) {
		this.x = x;
	}
	
	public int getX() {
		return x;
	}
	
	public static void setY(int y) {
		Paddle.y = y;
	}
	
	public static int getY() {
		return y;
	}
	
	public static void setWidth(int width) {
		Paddle.width = width;
	}
	
	public static int getWidth() {
		return width;
	}
	
	public static void setHeight(int height) {
		Paddle.height = height;
	}
	
	public static int getHeight() {
		return height;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public Object getColor() {
		return color;
	}
	

}
