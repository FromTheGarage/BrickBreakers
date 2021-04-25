//TODO: FINISH GAME, BUT RESIZE BRICKS AND CHANGE INTERSECTION AREAS
package brickBlasters;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class MapGenerator {
	public int map[][]; //brick cells
	public final int BRICK_WIDTH;
	public final int BRICK_HEIGHT;
	
	//Constructor
	public MapGenerator(int row, int col) {
		map = new int[row][col];
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[0].length; j++) {
				map[i][j] = 1;
			}
		}
		
		BRICK_WIDTH = 540/col;
		 BRICK_HEIGHT = 150/row;
		
		
	}
	
	public void draw(Graphics2D g) {
		//Current Brick Dimensions: Height = 50, Width = 77
		for(int i = 0; i < map.length; i++ ) {
			for(int j = 0; j < map[0].length; j++) {
				if(map[i][j] > 0) {
					g.setColor(Color.white);
					g.fillRect(j * BRICK_WIDTH + 80, i * BRICK_HEIGHT + 50 , BRICK_WIDTH, BRICK_HEIGHT);
					
					//Brick borders
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.black);
					g.drawRect(j * BRICK_WIDTH + 80, i * BRICK_HEIGHT + 50 , BRICK_WIDTH, BRICK_HEIGHT);
				}
			}
		}
		

	}
	
	public void setBrickValue(int value, int row, int col) {
		map[row][col] = value;
		
	}
	
}