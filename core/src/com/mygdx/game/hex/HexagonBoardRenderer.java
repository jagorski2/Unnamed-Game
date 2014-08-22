package com.mygdx.game.hex;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.mygdx.game.BoardScreen;

public class HexagonBoardRenderer {
	private Board board;

	public HexagonBoardRenderer(Board board) {
		this.setBoard(board);
	}

	public void drawBoard() {

		/* Line Drawing Inits */
		Hexagon[][] hexagons = board.getHexagons();
		ShapeRenderer r = new ShapeRenderer();
		r.setProjectionMatrix(BoardScreen.camera.combined);
		r.setColor(Color.BLACK);

		/* Hexagon inits */
		// TextureRegion textureGreen; = new TextureRegion(new Texture
		// Gdx.files.internal("textures/grass.jpg")), 800, 800);
		PolygonSpriteBatch sprite_batch = new PolygonSpriteBatch();
		sprite_batch.setProjectionMatrix(BoardScreen.camera.combined);

		/* Loop through and add hexagons as well as the outline */
		for (int i = 0; i < board.getWidth(); i++) {
			for (int j = 0; j < board.getHeight(); j++) {

				Hexagon hex = hexagons[i][j];
				/* Generate the Polygon Region */
				PolygonRegion polyReg = new PolygonRegion(
						BoardScreen.textureGreen, hex.getVertices(),
						BoardScreen.triangles);
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
