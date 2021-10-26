package gameState;

import java.awt.Graphics2D;
import java.util.ArrayList;

import main.GamePanel;

public class GameStateManager {


	private GameState[] gameStates;
	private int currentState;

	public static final int NUMGAMESTATES=2;
	public static final int MENUSTATE=0;
	public static final int LEVEL1STATE=1;

	public GameStateManager(){

		gameStates=new GameState[NUMGAMESTATES];

		currentState=MENUSTATE;

		loadState(currentState);
		
	}

	private void loadState(int state){
		if(state == MENUSTATE)
			gameStates[state]=new MenuState(this);
		if(state == LEVEL1STATE)
			gameStates[state]=new Level1State(this);
	}

	private void unloadState(int state){
		gameStates[state]= null;
	}

	public void setState(int state){
		
		unloadState(currentState);
		currentState=state;
		loadState(currentState);
		
	}

	public void update(){
		GamePanel.print("UPDATE from game state manager");
		
		try{
			gameStates[currentState].update();
		}catch(Exception e){

		}

	}


	public void draw(Graphics2D g){
		try{
			gameStates[currentState].draw(g);
		}catch(Exception e){

		}
		GamePanel.print("DRAW from game state manager");


	}

	public void keyPressed(int k){
		System.out.println("key pressed");
		gameStates[currentState].keyPressed(k);
	}

	public void keyReleased(int k){
		gameStates[currentState].keyReleased(k);
	}
}
