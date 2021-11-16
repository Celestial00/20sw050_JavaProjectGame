

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.*;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

	private boolean play = false;
	private int score = 0;
	private int totalBricks = 21;
	private Timer time;
	private int delay = 8;
	private int Playerx = 310;
	private int Ballposx = 120;
	private int Ballposy = 350;
	private int BallXdir = -1;
	private int BallYdir = -2;
	
	private MapGenerator map;
	
	public GamePanel() {
		
		map = new MapGenerator(3, 7);
		
		this.addKeyListener(this);
		setFocusable(true);
		this.setFocusTraversalKeysEnabled(true);
     	time = new Timer(delay, this);
		time.start();
		
		
		
	}

	
	public void paint(Graphics g) {
	
		g.setColor(Color.black);
        g.fillRect(1, 1, 692, 592);
        
        map.draw((Graphics2D)g);
        
	//	Walls

        g.setColor(Color.BLUE);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(692, 0, 3, 592);
        

        g.setColor(Color.red);
        g.fillRect(Playerx, 550, 100, 8);
        
		//final score

        g.setColor(Color.white);
        g.setFont(new Font("Arail", Font.BOLD, 25));
        g.drawString("" + score, 590, 30);
        
        
		//ball

        g.setColor(Color.BLUE);
        g.fillOval(Ballposx, Ballposy, 20, 20);
		
        if(Ballposy > 750) {
        	
        	play = false;
        	BallXdir = 0;
        	BallYdir = 0;
        	g.setColor(Color.red);
        	g.setFont(new Font("Arail", Font.BOLD, 30));
        	g.drawString("Game OVer, Score " + score, 190, 300);
        	
        }
        
        g.dispose();
        
	}
	
	public void moveRight() {
		
		play = true;
		Playerx += 20;
		
	}
	
public void moveLeft() {
		
		play = true;
		Playerx -= 20;
		
	}
	
	
	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			
			if(Playerx >= 600) {
				
				Playerx = 600;
				
			}
			else {
				
				moveRight();
			}
			
		}
		 
      if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			
    	  if(Playerx < 10) {
    		  
    		  Playerx = 10;
    		  
    	  }
    	  
    	  else {
    		  
    		  moveLeft();
    	  }
			
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		time.start();
		
		if(play) {
			
			if(new Rectangle(Ballposx, Ballposy, 20, 20).intersects(new Rectangle(Playerx, 550, 100, 8))) {
				
				BallYdir =- BallYdir;
				
			}
			
			A: for(int i = 0; i < map.map.length; i ++) {
				
				for(int j = 0; j < map.map[0].length; j++) {
					
					if(map.map[i][j] > 0) {
						
						int brickX = j * map.brickWidth + 80;
						int brickY = i * map.brickHeight + 50;
						int brickWidth = map.brickWidth;
						int brickHeight = map.brickHeight;
						
						Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight );
					    Rectangle ballRect = new Rectangle(Ballposx, Ballposy, 20, 20);
					    Rectangle brickRect = rect;
					   
					    
					    if(ballRect.intersects(brickRect)) {

					    	
					    	map.setBrickValue(0, i, j);
					    	totalBricks--;
					    	score += 5;
					    	
					    	if(Ballposx + 19 <= brickRect.x || Ballposx + 1 >= brickRect.x + brickRect.width ) {
					    		
					    		BallXdir =- BallYdir;
					    		
					    	}
					    	
					    	else {
					    		
					    		BallYdir =- BallYdir;
					    		
					    	}
					    	
					    	break A;
					    	
					    }
					    
						
					}
					
					
				}
				
			}
			
			Ballposx += BallXdir;
			Ballposy += BallYdir;
			
			if(Ballposx < 0) {
				
				BallXdir =- BallXdir;
				
				
			}
			
      if(Ballposy < 0) {
				
				BallYdir =- BallYdir;
				
				
			}

    if(Ballposx > 670) {
	
	BallXdir =- BallXdir;
	
	
         }
			
		}
		
		repaint();
		
	}

}
