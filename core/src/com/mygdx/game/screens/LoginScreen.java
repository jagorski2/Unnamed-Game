package com.mygdx.game.screens;

import com.app.models.User;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.MyGame;
import com.mygdx.game.data.comm.GameDataInterface;
import com.mygdx.game.data.comm.GameDataUtils;

public class LoginScreen implements Screen {

	private Table table = new Table();
	private final MyGame game;
	 private Skin skin = new Skin(Gdx.files.internal("uiskin.json"),
		       new TextureAtlas(Gdx.files.internal("uiskin.atlas")));
	private Stage stage = new Stage();
	public LoginScreen(final MyGame game) {
		this.game = game;
		final TextField userField = new TextField("", skin);
		TextField passwordField = new TextField("",skin);
		TextButton buttonLogin = new TextButton("login",skin);
		
		buttonLogin.addListener(new ClickListener(){
	            @Override
	            public void clicked(InputEvent event, float x, float y) {
	            	game.loggedInUser.setName(userField.getMessageText());
	            	game.loggedInUser.setPassword(userField.getMessageText());
	        		GameDataInterface gameData = GameDataUtils.getInstance();
	        		gameData.login(game.loggedInUser);
	            }
	        });
		buttonLogin.setSize(userField.getWidth(), userField.getHeight());
		table.add(userField);
		table.row().padBottom(10);
		table.add(passwordField);
		table.row().padBottom(10);
		table.add(buttonLogin);
		table.row().padBottom(10);
		table.setFillParent(true);
		stage.addActor(table);

	}
	
	@Override
	public void render(float delta) {
		 Gdx.gl.glClearColor(0, 0, 0, 1);
	     Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	     
	     if (game.loggedInUser.getUserId() != 0) {
	    	 game.setScreen(game.menu);
	     }
	     
	     stage.act();
	     stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
        stage.dispose();
        skin.dispose();
	}

}
