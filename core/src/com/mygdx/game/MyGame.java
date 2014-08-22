package com.mygdx.game;

import com.badlogic.gdx.Game;

public class MyGame extends Game {
    public static final String TITLE="Game Project"; 
    public static final int WIDTH=600,HEIGHT=800; // used later to set window size
    
     MainMenuScreen menu;
     BoardScreen board;
    @Override
    public void create() {
    	menu = new MainMenuScreen(this);
    	board = new BoardScreen(this);
        setScreen(menu);
    }
}