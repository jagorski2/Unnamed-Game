package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class UnitArtist {
	/*
	 * robot for testing purposes
	 */
	private Texture robotSheet;
	private TextureRegion[] robotRegion;
	private Animation robotAnimation;
	private SpriteBatch spriteBatch;
	private TextureRegion currentRobot;
	private OrthographicCamera camera;
	private float stateTime;
	/*
	 * end of robot stuff
	 */
	
	public UnitArtist(OrthographicCamera camera){
		this.camera = camera;
		/*
		 * robot
		 */
		int NCOLS = 8;
		int NROWS = 8;
		robotSheet = new Texture(Gdx.files.internal("swordman.png"));
		TextureRegion[][] tmp = TextureRegion.split(robotSheet, robotSheet.getWidth()/NCOLS, robotSheet.getHeight()/NROWS);
		System.out.println(robotSheet.getWidth() + ":" + robotSheet.getHeight());
		robotRegion = new TextureRegion[NROWS * NCOLS];
		int index = 0;
		System.out.println(tmp.length);
		System.out.println(tmp[0].length);
		robotRegion = new TextureRegion[8];
		for (int i = 0; i < 8; i ++) {
			robotRegion[i] = tmp[0][i];
		}
		/*
		for (int i = 0; i < NROWS; i ++) {
			for (int j = 0; j < NCOLS; j++) {
				robotRegion[index++] = tmp[i][j];
			}
		}
		*/
		robotAnimation = new Animation(0.1f,robotRegion);
		spriteBatch = new SpriteBatch();
		stateTime = 0;
		/*
		 * end of robot
		 */
	}
	
	public void drawRobot(int x, int y) {
		stateTime += Gdx.graphics.getDeltaTime();
		currentRobot = robotAnimation.getKeyFrame(stateTime, true);
		spriteBatch.begin();
		spriteBatch.setProjectionMatrix(camera.combined);
		spriteBatch.draw(currentRobot, x, y, 0, 0, 20, 30, 2, 2, 0);
		spriteBatch.end();
	}
}
