package brickBlasters;

import java.awt.Color;
import java.awt.Graphics;

public class Paddle {
	protected int x;
	private static int y = 550;
	private static int width = 100;
	private static int height = 8;
	private Color c;
	private boolean movingLeft;
	private boolean movingRight;

	//Constructor
	public Paddle(int x, int y, int width, int height, Color c, boolean movingLeft, boolean movingRight) {
		this.setX(x);
		Paddle.setY(y);
		Paddle.setWidth(width);
		Paddle.setHeight(height);
		this.setColor(c);
		this.setMovingLeft(movingLeft);
		this.setMovingRight(movingRight);
	}


	//Behaviors
	public void moveLeft() {
		x-= 40;
	}

	public void moveRight() {
		x += 40;
	}
	


	//Getters and Setters

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

	public void setColor(Color c) {
		this.c = c;
	}

	public Color getColor() {
		return c;
	}

	public void setMovingLeft(boolean movingLeft) {
		this.movingLeft = movingLeft;
	}
	
	public boolean getMovingRight() {
		return movingRight;
	}
	
	public void setMovingRight(boolean movingRight) {
		this.movingRight = movingRight;
	}
	
	public boolean getMovingLeft() {
		return movingLeft;
	}



}
