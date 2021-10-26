package gameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import main.GamePanel;

import tileMap.Background;

public class MenuState extends GameState{

	private Background bg;

	private int currentChoice=0;
	private String[] options={
			"Start","Help","Quit"
	};

	private Color titleColor;
	private Font titleFont;

	private Font font;

	public MenuState(GameStateManager gsm){
		this.gsm=gsm;
		GamePanel.print("MENUSTATE constractor");
		try{
			
			//bg=new Background("/backgrounds/jumping player2.png", 1);
			bg=new Background("/backgrounds/menubg.gif", 1);
			bg.setVector(-0.1, 0);

			titleColor=new Color(128,0,0);
			titleFont=new Font("Century Gthic",Font.PLAIN,28);
			font=new Font("Arial",Font.PLAIN,12);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void init() {

	}
	public void update() {
		GamePanel.print("UPDATE from menu state");
		bg.update();
	}
	public void draw(Graphics2D g) {
		GamePanel.print("DRAW from menu state");
		bg.draw(g);


		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString("Dragon Tale", 80, 70);

		g.setFont(font);
		for(int i=0;i<options.length;i++){
			if(i==currentChoice){
				g.setColor(Color.black);
			}else{
				g.setColor(Color.red);
			}

			g.drawString(options[i], 145,140+i*15);
		}
	}

	private void select(){
		if(currentChoice==0){
			System.out.println("true;");
			gsm.setState(GameStateManager.LEVEL1STATE);
		}
		if(currentChoice==1){

		}
		if(currentChoice==2){
			System.exit(0);
		}

	}
	public void keyPressed(int k) {
		System.out.println("Enter presed");
		if(k==KeyEvent.VK_ENTER){
			select();
			System.out.println("Enter presed");
		}
		
		if(k==KeyEvent.VK_UP){
			currentChoice--;
			if(currentChoice==-1){
				currentChoice=options.length-1;
			}
		}
		if(k==KeyEvent.VK_DOWN){
			currentChoice++;
			if(currentChoice==options.length){
				currentChoice=0;
			}
		}
	}
	public void keyReleased(int k) {
	}
	

}
