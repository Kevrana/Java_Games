package OnePlayerPong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener, ActionListener {

	private Boolean play = false;
	private int score = 0;
	
	private Timer timer;
	private int delay = 3;
	
	private int player = 310;
	private int ballposX = 255;
	private int ballposY = 300;
	
	private int ballXDir = -1;
	private int ballYDir = -2;
	
	
	public Gameplay() {
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
	}
	
	public void paint(Graphics g) {
		
		//background 
		g.setColor(new Color(51,204,255));
		g.fillRect(1,1, 495, 595);
		
		//ground
		g.setColor(new Color(204,153,0));
		g.fillRect(1,545, 495, 595);
		
		
		// score
		g.setColor(Color.white);
		g.setFont(new Font("impact", Font.BOLD, 35));
		g.drawString("" + score, 400, 75);
		
		// paddle 
		g.setColor(Color.black);
		g.fillRoundRect(player, 515, 120, 20, 20, 20);
		g.setColor(Color.green);
		g.fillRoundRect(player+5, 520, 105, 10, 20, 20);
		g.setColor(Color.black);
		g.fillRoundRect(player+20, 532, 80, 50, 5, 5);
		g.setColor(Color.green);
		g.fillRect(player+30, 533, 60, 50);
		
		// ball
		g.setColor(Color.black);
		g.fillOval(ballposX, ballposY, 35, 35);
		g.setColor(Color.red);
		g.fillOval(ballposX+5, ballposY+5, 25, 25);
		
		// borders - top, left, right
		g.setColor(Color.black);
		// left border
		g.fillRect(0, 0, 5, 595);
		//top border
		g.fillRect(0, 0, 495, 5);
		// right border
		g.fillRect(495, 0, 5, 595);
		
		
		
		
		
		
		// if ball goes below paddle line
		if(ballposY > 570) {
			play = false;
			ballXDir = 0;
			ballYDir = 0;
			delay = 3;

			g.setColor(Color.red);
			g.setFont(new Font("serif", Font.BOLD, 50));
			g.drawString("GAME OVER!", 60, 275);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("Score: " + score, 170, 315);

			g.setFont(new Font("serif", Font.BOLD, 20));
			g.drawString("Press enter to Restart", 130, 350);

		}
		
		g.dispose();
	}
	
	public void moveRight() {
		play = true;
		player += 25;
	}
	
	public void moveLeft() {
		play = true;
		player -= 25;
	}
	
	
	public void keyPressed(KeyEvent e) {
		
		// make paddle move right till right border
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(player >= 400) {
				player = 400;
			}
			else {
				moveRight();
			}
		}
		
		// make paddle move left till left border
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			if(player < 10) {
				player = 10;
			}
			else {
				moveLeft();
			}
		}
		
		// pause key when SPACE is pressed
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			if(play) {
				play = false;
			}
		}
		
		// restart game if enter is pressed
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(!play) {
				play = true;
				ballposX = 120;
				ballposY = 350;
				ballXDir = -1;
				ballYDir = -2;
				player = 310;
				score = 0;
				repaint();
			}
		}
	}
	
	
	
	public void actionPerformed(ActionEvent e) {
		timer.start();
		 
		if(play) {
			if(new Rectangle(ballposX, ballposY, 35, 35).intersects(new Rectangle(player, 514, 118, 1 ))) {
				ballposY -= 20;
				ballYDir = -ballYDir;
				score += 1;
			}
			
			
			
			ballposX += ballXDir;
			ballposY += ballYDir;
			if(ballposX < 0 ) {
				ballXDir = -ballXDir;
			}
			if(ballposY < 0 ) {
				ballYDir = -ballYDir;
			}
			if(ballposX > 470 ) {
				ballXDir = -ballXDir;
			}
		}
		
	
		repaint();
	}

	// not needed 
	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}

}









