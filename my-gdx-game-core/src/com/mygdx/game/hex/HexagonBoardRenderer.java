package com.mygdx.game.hex;

import java.awt.Font;
import java.util.Arrays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.MyGdxGame;

public class HexagonBoardRenderer {
	private Board board;

	public HexagonBoardRenderer(Board board) {
		this.setBoard(board);
	}

	public void drawBoard() {

		/* Line Drawing Inits */
		Hexagon[][] hexagons = board.getHexagons();
		ShapeRenderer r = new ShapeRenderer();
		r.setColor(Color.BLACK);

		/* Hexagon inits */
		//TextureRegion textureGreen; = new TextureRegion(new Texture Gdx.files.internal("textures/grass.jpg")), 800, 800);
		PolygonSpriteBatch sprite_batch = new PolygonSpriteBatch();

		/* Loop through and add hexagons as well as the outline */
		for (int i = 0; i < board.getWidth(); i++) {
			for (int j = 0; j < board.getHeight(); j++) {

				Hexagon hex = hexagons[i][j];
				/* Generate the Polygon Region */
				PolygonRegion polyReg = new PolygonRegion(MyGdxGame.textureGreen, hex.getVertices(),MyGdxGame.triangles); 
				sprite_batch.begin();
				sprite_batch.draw(polyReg, 0, 0);
				sprite_batch.end();
				r.begin(ShapeType.Line);
				r.polygon(hex.getVertices());
				r.end();
			}
		}
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}
}
