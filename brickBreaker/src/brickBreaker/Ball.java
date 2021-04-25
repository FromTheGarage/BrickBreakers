package brickBreaker;

import java.awt.Color;
import java.awt.Graphics;

//Constants?
public class Ball{

	//States
	protected int x; //Starting Ball positions
	protected int y;
	protected static int width = 20;
	protected static int height = 20;
	//Ball 1= 120, 350, Ball 2 = 500, 350	
	//Ball directions for collision, Ball 1 SLOPE = 2, Ball 2 SLOPE = -1
	protected static Color color = Color.white;
	
	//Constructor
	public Ball(int x, int y, int width, int height, Color color) {
		this.setX(x);
		this.setY(y);
		Ball.setWidth(width);
		Ball.setHeight(height);
		Ball.setColor(color);
		
	}
	
	//Reflection behaviors
	
	public void reflectX() {
		x = -x;
	}
	
	public void reflectY() {
		y = -y;
	}
	
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
		return Ball.width;
	}
	
	public static void setHeight(int height) {
		Ball.height = height;
	}
	
    public static int getHeight() {
    	return height;
    }

	public static void setColor(Color color) {
		Ball.color = color;
	}
	
	public static Color getColor() {
		return color;
	}
	
	//DRAW METHOD
	public void drawBall(Graphics g) {
		g.setColor(color);
		g.fillOval(x, y, 20, 20);
	}
	
}