package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.mygdx.game.hex.Board;
import com.mygdx.game.hex.HexagonBoardRenderer;
import com.mygdx.game.play.BattleInstance;
import com.mygdx.game.play.BattleInstancePlayer;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	//Texture img;
	BattleInstance battle;
	ShaderProgram shader;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		//img = new Texture("badlogic.jpg");
		
		Board b = new Board(0, 0, 10, 10, 20);
		List<BattleInstancePlayer> players = new ArrayList<BattleInstancePlayer>();
		players.add(new BattleInstancePlayer());
		battle = new BattleInstance(b,players);
		
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		battle.drawBattleInstance();
		//batch.draw(img, 0, 0);
		batch.end();
	}
}
