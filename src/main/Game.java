package main;

import java.awt.Dimension;
import java.awt.print.Printable;

import javax.swing.JFrame;

public class Game {

public static void main(String args[]){
	
	GamePanel.print("Froam MAIN");
	JFrame window=new JFrame("Dragon Tale");
	
	window.setContentPane(new GamePanel());
	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	window.setResizable(false);
	window.pack();
	window.setVisible(true);
	
	}
	
}
