

import javax.swing.*;

public class Main {

	public static void main(String[] args) {
		
		
		JFrame frame = new JFrame();
		GamePanel panel = new GamePanel();
		
		frame.setBounds(10, 10, 700, 600);
		frame.setTitle(" Project 0 ");
		frame.setResizable(false);
	
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.setVisible(true);
	}

}
