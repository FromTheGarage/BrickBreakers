package brickBreaker;
import java.awt.Color;
import java.awt.Font; 
import java.awt.Graphics; 
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;




import javax.swing.JPanel;
public class Gameplay extends JPanel implements KeyListener, ActionListener {
	private boolean play = false; //Game shouldn't start immediately when program begins
	private int score = 0;
	
	private int totalBricks = 21;
	private Timer time;
	private int delay = 8;
	
	private double p1X = 5; //Starting position of player 1's paddle
	private double p2X = 589; //Starting position of player 2's paddle
	
	
	 
	private int b1posX = 120; //Starting ball positions
	private int b1posY = 350;
	private int b2posX = 500;
	private int b2posY = 350;
								//Ball directions for collision, BALL 1 SLOPE = 2, BALL 2 SLOPE = -1
	private int b1Xdir = -1;
	private int b1Ydir = 2;
	private int b2Xdir = 1;
	private int b2Ydir = 2;
	
	private MapGenerator map; //Game map
	
	//Allow players to move paddles simultaneously
	
	private boolean p1Left;
	private boolean p1Right;
	private boolean p2Left;
	private boolean p2Right;
	
	
	
	//Default constructor
	public Gameplay() {
		map = new MapGenerator(3,7);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		Timer timer = new Timer(delay, this);
		timer.start();
	}
	
	//Display
	public void paint(Graphics g) {
		// background
		g.setColor(Color.black);
		g.fillRect(1, 1, 692, 592); //x, y, width, height
		
		//drawing map
		map.draw((Graphics2D) g);
		
		// borders
		g.setColor(Color.yellow);
		g.fillRect(0, 0, 3, 592);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(691, 0, 3, 592);
		
		// display score
		g.setColor(Color.white);
		g.setFont(new Font("Courier", Font.BOLD, 25));
		g.drawString(""+score, 590, 30);
		
		// Player 1's paddle
		g.setColor(Color.white);
		g.fillRect((int) p1X, 550, 100, 8);
		
		//Player 2's paddle
		g.setColor(Color.pink);
		g.fillRect((int) p2X, 550, 100, 8);
		
		//Paddle Movement
		if(p1Left == true) {
			play = true;
			p1X -= 1.5;
				if(p1X <= 0) {
					p1X -= p1X;
				}
		}
		if(p1Right == true) {
			play = true;
			p1X += 1.5;
				if(p1X >= 600) {
					p1X = 600;
				}
		}
		if(p2Left == true) {
			play = true;
			p2X -= 1.5;
				if(p2X <= 0) {
					p2X -= p2X;
				}
		}
		if(p2Right == true) {
			play = true;
			p2X += 1.5;
				if(p2X >= 600) {
					p2X = 600;
				}
		}
		
		
			
		// the ball 
		g.setColor(Color.CYAN);
		g.fillOval(b1posX, b1posY, 20, 20);
		
		g.setColor(Color.orange);
		g.fillOval(b2posX, b2posY, 20, 20);
		
		
		if(totalBricks <= 0) {
			play = false;
			b1Xdir = 0;
			b1Ydir = 0;
			b2Xdir = 0;
			b2Ydir = 0;
			g.setColor(Color.red);
			g.setFont(new Font("Dialog", Font.BOLD, 30));
			g.drawString("You Won!: ", 230, 350);
			
			g.setFont(new Font("Dialog", Font.BOLD, 20));
			g.drawString("Press Enter to Restart", 230, 300);
			
		}
		
		//Player loses if ball goes under paddles
		
		if(b1posY > 570) {
			play = false;
			b1Xdir = 0;
			b1Ydir = 0;
			b2Xdir = 0;
			b2Ydir = 0;
			g.setColor(Color.red);
			g.setFont(new Font("Dialog", Font.BOLD, 30));
			g.drawString("GAME OVER, Score:" +score , 190, 300);
			
			g.setFont(new Font("Dialog", Font.BOLD, 20));
			g.drawString("Press Enter to Restart", 240, 350);
		}
		
		if(b2posY > 570) {
			play = false;
			b1Xdir = 0;
			b1Ydir = 0;
			b2Xdir = 0;
			b2Ydir = 0;
			g.setColor(Color.red);
			g.setFont(new Font("Dialog", Font.BOLD, 30));
			g.drawString("GAME OVER, Score:" +score , 190, 300);
			
			g.setFont(new Font("Dialog", Font.BOLD, 20));
			g.drawString("Press Enter to Restart", 240, 350);
			
		}
		
		g.dispose();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(play) { //BALL-PADDLE COLLISION
			//Condition when ball 1 hits paddle 1
			if(new Rectangle(b1posX,b1posY, 20, 20).intersects(new Rectangle((int) p1X, 550, 100, 8)) ) {
				b1Ydir = -b1Ydir;
			}
			//Condition when ball 1 hits paddle 2
			if(new Rectangle(b1posX,b1posY, 20, 20).intersects(new Rectangle((int) p2X, 550, 100, 8)) ) {
				b1Ydir = -b1Ydir;
			}
			//Condition when ball 2 hits paddle 1
			if(new Rectangle(b2posX,b2posY, 20, 20).intersects(new Rectangle((int) p1X, 550, 100, 8)) ) {
				b2Ydir = -b2Ydir;
			}
			//Condition when ball 2 hits paddle 2
			if(new Rectangle(b2posX,b2posY, 20, 20).intersects(new Rectangle((int) p2X, 550, 100, 8)) ) {
				b2Ydir = -b2Ydir;
			}
			
			A: for(int i = 0; i < map.map.length; i++) {
				for(int j = 0; j<map.map[0].length; j++) {
					if(map.map[i][j] > 0) {
						int brickX = j * map.brickWidth + 80;
						int brickY = i * map.brickHeight + 50;
						int brickWidth = map.brickWidth;
						int brickHeight = map.brickHeight;
						
						Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
						Rectangle b1Rect = new Rectangle(b1posX, b1posY, 20, 20); //ball 1 area
						Rectangle b2Rect = new Rectangle(b2posX, b2posY, 20, 20); //ball 2 area
						Rectangle brickRect = rect; //Brick area
						
						//Condition for collision between the first ball and a brick
						if(b1Rect.intersects(brickRect)) {
							map.setBrickValue(0, i, j);
							totalBricks--;
							score += 5;
							
							if(b1posX + 19 <= brickRect.x || b1posX + 1 >= brickRect.x + brickRect.width) {
								b1Xdir = -b1Xdir;
							} 
							else {
								b1Ydir = -b1Ydir;
							}
							
						}
						
						//Condition for collision between the second ball and a brick
						if(b2Rect.intersects(brickRect)) {
							map.setBrickValue(0, i, j);
							totalBricks--;
							score += 5;
							
							if(b2posX + 19 <= brickRect.x || b2posX + 1 >= brickRect.x + brickRect.width) {
								b2Xdir = -b2Xdir;
							} 
							else {
								b2Ydir = -b2Ydir;
							}
							
						}
				
					}
				}
			}
			
			//Move ball when game starts 
			b1posX += b1Xdir;
			b1posY += b1Ydir;
			b2posX += b2Xdir;
			b2posY += b2Ydir;
			
			//BALL-WALL COLLISION
			if(b1posX < 0) { //Hits left wall
				b1Xdir = -b1Xdir;
			}
			if(b1posY < 0) { //Hits Ceiling
				b1Ydir = -b1Ydir;
			}
			if(b1posX > 670) { //Hits right wall
				b1Xdir = -b1Xdir;
			}
			if(b2posX < 0) { //Hits left wall
				b2Xdir = -b2Xdir;
			}
			if(b2posY < 0) { //Hits Ceiling
				b2Ydir = -b2Ydir;
			}
			if(b2posX > 670) { //Hits right wall
				b2Xdir = -b2Xdir;
			}
		
		}
		repaint();//Redraw the screen so the paddle can move (create new frame)
		
	}

	@Override

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_A) {
			p1Left = true;
		} 
		else if(e.getKeyCode() == KeyEvent.VK_D) {
			p1Right = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			p2Left = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			p2Right = true;
		}
		else {};
		

		//Reset the game
	if(e.getKeyCode() == KeyEvent.VK_ENTER) {	
		if(!play) {
			play = true;
			b1posX = 120;
			b1posY = 350;
			b2posX = 500;
			b2posY = 350;
			
			b1Xdir = -1;
			b1Ydir = 2;
			b2Xdir = 1;
			b2Ydir = 2;
			
			p1X = 5;
			p2X = 589
					;
			score = 0;
			totalBricks = 21;
			map = new MapGenerator(3, 7);
			repaint(); //Redraw original frame
		}
		
		}
	}
	
	//Detects when a key is no longer being pressed, stops movement
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_A) {
			p1Left = false;
		} 
		else if(e.getKeyCode() == KeyEvent.VK_D) {
			p1Right = false;
		} 
		else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			p2Left = false;
		}
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			p2Right = false;
		}
		else {};
		
		
		
	}
			
	
	/*
	
	
	/*The keyTyped methods isn't used, but
	 * is necessary when using keyPressed and keyReleased to detect keyboard input*/
	 
	

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
