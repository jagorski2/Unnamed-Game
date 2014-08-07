package com.mygdx.game.hex;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.play.Tile;

public class HexagonBoardRenderer {
	private Board board;

	public HexagonBoardRenderer(Board board) {
		this.setBoard(board);
	}

	public void drawBoard() {

		/* Line Drawing Inits */
		Tile[][] tiles = board.getTiles();
		ShapeRenderer r = new ShapeRenderer();
		r.setProjectionMatrix(MyGdxGame.camera.combined);
		r.setColor(Color.BLACK);

		/* Hexagon inits */
		// TextureRegion textureGreen; = new TextureRegion(new Texture
		// Gdx.files.internal("textures/grass.jpg")), 800, 800);
		PolygonSpriteBatch sprite_batch = new PolygonSpriteBatch();
		sprite_batch.setProjectionMatrix(MyGdxGame.camera.combined);

		/* Loop through and add hexagons as well as the outline */
		for (int i = 0; i < board.getWidth(); i++) {
			for (int j = 0; j < board.getHeight(); j++) {

				Hexagon hex = tiles[i][j].getHexagon();
				/* Generate the Polygon Region */
				PolygonRegion polyReg = new PolygonRegion(
						MyGdxGame.textureGreen, hex.getVertices(),
						MyGdxGame.triangles);
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
