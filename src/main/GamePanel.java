package main;

import gameState.GameStateManager;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable,KeyListener{

	//DIMENTION
	public static final int WIDTH=320;
	public static final int HEIGHT=240;
	public static final int SCALE=2;

	//game thread
	private Thread thread;
	private boolean running;
	public static boolean isPrint=true;
	private int FPS=60;
	private long targetTime=1000/FPS;

	//image
	private BufferedImage image;
	private Graphics2D g;

	//game states manager
	private GameStateManager gsm;
	
	public static void print(String s){
		if(isPrint){
			System.out.println(s);
		}
		
	}

	public GamePanel(){
		
		super();
		//print("GAME PANEL constructor");
		setPreferredSize(new Dimension((WIDTH*SCALE),(HEIGHT*SCALE)));
		
		setFocusable(true);
		requestFocus();
		
	}

	public void addNotify(){
		//print("ADD NOTIFY of game panel");
		super.addNotify();
		
		if(thread==null){
			thread=new Thread(this);
			addKeyListener(this);
			thread.start();
		}
	}


	private void init(){
		//print("Froam INIT of game panel");
		
		image =new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

		g=(Graphics2D) image.getGraphics(); 
		running=true;

		gsm=new GameStateManager();
	}
	public void run(){
		//print("Froam RUN of game panel");
		init();

		long start;
		long elapsed;
		long wait;
		while(running){

			start=System.nanoTime();

			update();
			draw();
			drawToScreen();

			elapsed=System.nanoTime()-start;
			wait = targetTime - elapsed / 1000000;

			if(wait<0) wait=5;
			try {
				thread.sleep(wait);
			} catch (Exception  e) {

				e.printStackTrace();
			}
			isPrint=false;
		}
	}

	private void update() {
		//print("UPDATE of game panel");
		gsm.update();
	}

	private void draw() {
		//print("DRAW of game panel");
		gsm.draw(g);
	}

	private void drawToScreen() {
		//print("DRAW TO SCREEN of game panel");
		
		Graphics g2= getGraphics();
		g2.drawImage(image, 0, 0,WIDTH*SCALE,HEIGHT*SCALE, null);
		g2.dispose();
	}

	public void keyTyped(KeyEvent key){

	}
	public void keyPressed(KeyEvent key){
		gsm.keyPressed(key.getKeyCode());
	}
	public void keyReleased(KeyEvent key){
		gsm.keyReleased(key.getKeyCode());
	}
}
