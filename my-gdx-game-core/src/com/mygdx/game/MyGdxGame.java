package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.mygdx.game.hex.Board;
import com.mygdx.game.hex.HexagonBoardRenderer;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	//Texture img;
	Board b;
	HexagonBoardRenderer artist;
	ShaderProgram shader;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		//img = new Texture("badlogic.jpg");
		b = new Board(0, 0, 10, 10, 20);
		artist = new HexagonBoardRenderer(b);
		
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		//batch.draw(img, 0, 0);
		artist.drawBoard();
		batch.end();
	}
}
