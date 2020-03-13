package OnePlayerPong;

import javax.swing.JFrame;
import OnePlayerPong.Gameplay;

public class Main {

	public static void main(String[] args) {

		JFrame obj = new JFrame();
		Gameplay gamePlay = new Gameplay();
		obj.setBounds(10, 10, 500, 600);
		obj.setTitle("UnoPong");
		obj.setResizable(false);
		obj.setVisible(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.add(gamePlay);
		
	}

}
