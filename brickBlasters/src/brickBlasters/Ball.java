package brickBlasters;
import java.awt.Color;
import java.awt.Graphics;

public class Ball {
	protected int x;
	protected int y;
	private static int width = 20;
	private static int height = 20;
	protected double dX; //-1
	protected double dY; //-2
	private static Color c = Color.CYAN;
	
	
	//Constructor
	public Ball(int x, int y, int width, int height, double dX, double dY, Color c) {
		this.x = x;
		this.y = y;
		Ball.setWidth(width);
		Ball.setHeight(height);
		this.dX = dX;
		this.dY = dY;
		Ball.setColor(c);

	}
	
	//Behaviors
	public void reflectX() {
			dX = -dX;
	}
		
	public void reflectY() {
			dY = -dY;
	}
		
	public void moveBall() {
			x += dX;
			y += dY;
			
	}

	
	//GETTERS AND SETTERS
	public void setX(int x) {
		this.x = x;
	}
	
	public int getX() {
		return x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getY() {
		return y;
	}
	
	public static void setWidth(int width) {
		Ball.width = width;
	}
	
	public static int getWidth() {
		return width;
	}
	
	public static void setHeight(int height) {
		Ball.height = height;
	}
	
	public static int getHeight() {
		return height;
	}
	
	public void setDX(int dX) {
		this.dX = dX;
	}
	
	public double getDX() {
		return dX;
	}
	
	public void setDY(int dY) {
		this.dY = dY;
	}
	
	public double getDY() {
		return dY;
	}
	
	public static void setColor(Color c) {
		Ball.c = c;
	}
	public static Color getColor() {
		return c;
	}
	
		
}
