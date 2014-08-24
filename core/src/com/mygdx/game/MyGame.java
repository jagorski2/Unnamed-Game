package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.mygdx.game.screens.MainMenuScreen;

public class MyGame extends Game {
    public static final String TITLE="Game Project"; 
    public static final int WIDTH=400,HEIGHT=400; // used later to set window size
    
    public MainMenuScreen menu;
    public BoardScreen board;
    @Override
    public void create() {
    	menu = new MainMenuScreen(this);
    	board = new BoardScreen(this);
        setScreen(menu);
    }
}