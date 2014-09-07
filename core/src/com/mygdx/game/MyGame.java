package com.mygdx.game;

import com.app.models.User;
import com.badlogic.gdx.Game;
import com.mygdx.game.screens.BoardScreen;
import com.mygdx.game.screens.LoginScreen;
import com.mygdx.game.screens.MainMenuScreen;

public class MyGame extends Game {
    public static final String TITLE="Game Project"; 
    public static final int WIDTH=400,HEIGHT=400; // used later to set window size
    
    public MainMenuScreen menu;
    public BoardScreen board;
    public LoginScreen login;
    public User loggedInUser;
    @Override
    public void create() {
    	loggedInUser = new User();
    	menu = new MainMenuScreen(this);
    	board = new BoardScreen(this);
    	login = new LoginScreen(this);
        setScreen(login);
    }
}