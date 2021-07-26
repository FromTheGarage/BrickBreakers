package brickBreaker;
import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		//Create frame and GamePlay object
		JFrame object = new JFrame();
		Gameplay gamePlay = new Gameplay();
		object.setBounds(10, 10, 700, 600);
		object.setTitle("Brick Breaker Multiplayer");
		
		//Window is fixed size 
		object.setResizable(false);
		object.setVisible(true);
		object.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		object.add(gamePlay);
	}
}
