package brickBlasters;
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
	public boolean play = false; //Game shouldn't start immediately when program begins
	private int score = 0;

	private int totalBricks = 21;
	private int delay = 8;

	private MapGenerator map;
	
	Timer timer = new Timer(delay, this);


	/*Instantiate Paddles and Balls in Global Scope
	 * so that they may be accessed by ALL methods
	 * of this class. This also gives this class
	 * access to all of the Paddle and Ball classes'
	 * behavior methods (movement)*/


	Paddle paddle1 = new Paddle(100, Paddle.getY(), Paddle.getWidth(), Paddle.getHeight(), Color.green, false, false);
	Paddle paddle2 = new Paddle(500, Paddle.getY(), Paddle.getWidth(), Paddle.getHeight(), Color.red, false, false);

	Ball ball1 = new Ball(120, 350, Ball.getWidth(), Ball.getHeight(), -1, 2, Ball.getColor());
	Ball ball2 = new Ball(500, 350, Ball.getWidth(), Ball.getHeight(), 1, 2, Ball.getColor());

	//Move Paddles simultaneously

	//Default constructor
	public Gameplay() {
		map = new MapGenerator(3,7);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		Timer timer = new Timer(delay, this);
		timer.start();
	}

	public void paint(Graphics g) {
		//background
		g.setColor(Color.black);
		g.fillRect(1, 1, 700, 600);

		//drawing map
		map.draw((Graphics2D) g);

		//score
		g.setColor(Color.white);
		g.setFont(new Font("Georgia", Font.BOLD, 25));
		g.drawString("" + score, 590, 30);

		//Draw the paddles
		g.setColor(paddle1.getColor());
		g.fillRect(paddle1.getX(), Paddle.getY(), Paddle.getWidth(), Paddle.getHeight());

		g.setColor(paddle2.getColor());
		g.fillRect(paddle2.getX(), Paddle.getY(), Paddle.getWidth(), Paddle.getHeight());

		//Control the paddles
		if(paddle1.getMovingLeft()) {
			play = true;
			paddle1.x -= 1.5;
			if(paddle1.x <= 0) {
				paddle1.x -= paddle1.x;
			}

		}
		if(paddle1.getMovingRight()) {
			play = true;
			paddle1.x += 1.5;
			if(paddle1.x >= 600) {
				paddle1.x = 600;
			}
		}
		if(paddle2.getMovingLeft()) {
			play = true;
			paddle2.x -= 1.5;
			if(paddle2.x <= 0) {
				paddle2.x -= paddle2.x;
			}
		}
		if(paddle2.getMovingRight()) {
			play = true;
			paddle2.x += 1.5;
			if(paddle2.x >= 600) {
				paddle2.x = 600;
			}
		}


		//Draw the balls
		g.setColor(Ball.getColor());
		g.fillOval(ball1.x, ball1.y, Ball.getWidth(), Ball.getHeight() );

		g.setColor(Ball.getColor());
		g.fillOval(ball2.x, ball2.y, Ball.getWidth(), Ball.getHeight() );

		//Win
		if(totalBricks <= 0) {
			play = false;
			ball1.setDX(0);
			ball1.setDY(0);
			g.setColor(Color.red);
			g.setFont(new Font("Times New Roman", Font.BOLD, 30) );
			g.drawString("YOU WON!", 260, 300);
		}

		//Lose
		if(ball1.y > 570 || ball2.y > 570) {
			play = false;
			ball1.setDX(0);
			ball1.setDY(0);
			ball2.setDX(0);
			ball2.setDY(0);
			g.setColor(Color.RED);
			g.setFont(new Font("Times New Roman", Font.BOLD, 30));
			g.drawString("Game Over, Score: " + score, 225, 300);

			g.setFont(new Font("Times New Roman", Font.BOLD, 20));
			g.drawString("Press Enter to Restart", 230, 350);
		}

		g.dispose();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		timer.start();
		if(play) {
			//Areas of intersection between balls and paddles
			Rectangle ball1Rect = new Rectangle(ball1.x, ball1.y, Ball.getWidth(), Ball.getHeight());
			Rectangle ball2Rect = new Rectangle(ball2.x, ball2.y, Ball.getWidth(), Ball.getHeight());
			Rectangle paddle1Rect = new Rectangle(paddle1.x, Paddle.getY(), Paddle.getWidth(), Paddle.getHeight());
			Rectangle paddle2Rect = new Rectangle(paddle2.x, Paddle.getY(), Paddle.getWidth(), Paddle.getHeight());
			
			
			
			
			//BALLS HIT PADDLES
			if(ball1Rect.intersects(paddle1Rect) || ball1Rect.intersects(paddle2Rect)) { //Ball 1 Hits Either Paddle
				ball1.reflectY();
			}
			if(ball2Rect.intersects(paddle1Rect) || ball2Rect.intersects(paddle2Rect)) { //Ball 1 Hits Either Paddle
				ball2.reflectY();
			}
			

			A: for(int i = 0; i<map.map.length; i++) {
				for(int j = 0; j<map.map[0].length; j++) {
					if(map.map[i][j] > 0) {
						int brickX = j*map.BRICK_WIDTH + 80;
						int brickY = i*map.BRICK_HEIGHT + 50;
						int brickWidth = map.BRICK_WIDTH;
						int brickHeight = map.BRICK_HEIGHT;
						
						Rectangle brickRect = new Rectangle(brickX, brickY);
						
						//Distance between balls and bricks
						double dist1 = Math.sqrt( Math.pow((brickRect.x - ball1.x), 2) ) + Math.sqrt( Math.pow((brickRect.y - ball1.y), 2) ); //Distance between ball1 and brickRect
						double dist2 = Math.sqrt( Math.pow((brickRect.x - ball2.x), 2) ) + Math.sqrt( Math.pow((brickRect.y - ball2.y), 2) ); //Distance between ball2 and brickRect
						
						if(ball1Rect.x == brickRect.x || ball2Rect.x == brickRect.x) {
							map.setBrickValue(0, i, j);
							totalBricks--;
							score += 5;
							
							if(ball1.dX + 19 <= brickRect.x || ball1.x + 1 >= brickRect.x + brickRect.width) {
								ball1.reflectX();
							} 
							else {
								ball1.reflectY();
							}
							if(ball2.dX + 19 <= brickRect.x || ball2.x + 1 >= brickRect.x + brickRect.width) {
								ball2.reflectX();
							} 
							else {
								ball2.reflectY();
							}
							
							break A;
						}
					}
				}
			}
			
			//Move balls to start game
			ball1.moveBall();
			ball2.moveBall();
			
			//BALL-WALL COLLISIONS
			if(ball1.x < 0 || ball1.x > 675) {// WALL COLLISION 
				ball1.reflectX();
			}
			if(ball1.y < 0) {//CEILING COLLISION
				ball1.reflectY();
			}
			if(ball2.x < 0 || ball2.x > 675) {//WALL COLLISION 
				ball2.reflectX();
			}
			if(ball2.y < 0) {//CEILING COLLISION
				ball2.reflectY();
			}
			
		}
		
		
		
		
		
		
		
		//Update graphics END OF ACTIONPERFORMED
		repaint();

	}
		
	
	
	
			


		//Controls
		@Override
		public void keyPressed(KeyEvent e) {
			//Player 1 Controls

			if(e.getKeyCode() == KeyEvent.VK_A) {
				if(paddle1.x < 10) {
					paddle1.setX(10);
				} 
				else {
					paddle1.setMovingLeft(true);
					play = true;
					paddle1.moveLeft();
				}
			}

			if(e.getKeyCode() == KeyEvent.VK_D) {
				if(paddle1.x >= 600) {
					paddle1.setX(600);

				} 
				else {
					paddle1.setMovingRight(true);
					play = true;
					paddle1.moveRight();

				}

			}

			//Player 2 Controls
			if(e.getKeyCode() == KeyEvent.VK_LEFT) {
				if(paddle2.x < 10) {
					paddle2.setX(10);
				} 
				else {
					paddle2.setMovingLeft(true);
					play = true;
					paddle2.moveLeft();
				}
			}

			if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
				if(paddle2.x >= 600) {
					paddle2.setX(600);

				} 
				else {
					paddle2.setMovingRight(true);
					play = true;
					paddle2.moveRight();

				}

			}

			//Restart
			if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				if(!play) {
					play = true;
					ball1.x = 120;
					ball1.y = 350;

					ball2.x = 500;
					ball2.y = 350;

					ball1.dX = -1;
					ball1.dY = 2;

					ball2.dX = 1;
					ball2.dY = 2;

					paddle1.x = 100;
					paddle2.x = 500;

					score = 0;
					totalBricks = 21;
					map = new MapGenerator(3,7);
					repaint();
				}
			}

		}



		@Override
		public void keyReleased(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_A) {
				paddle1.setMovingLeft(false);
			} 
			else if(e.getKeyCode() == KeyEvent.VK_D) {
				paddle1.setMovingRight(false);
			} 
			else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
				paddle2.setMovingLeft(false);
			}
			else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
				paddle2.setMovingRight(false);
			}
			else {};

		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub

		}

	}



