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
		MyGdxGame.Project_Shape_Renderer.setProjectionMatrix(MyGdxGame.camera.combined);
		MyGdxGame.Project_Shape_Renderer.setColor(Color.BLACK);

		/* Hexagon inits */
		// TextureRegion textureGreen; = new TextureRegion(new Texture
		// Gdx.files.internal("textures/grass.jpg")), 800, 800);


		MyGdxGame.sprite_batch.setProjectionMatrix(MyGdxGame.camera.combined);

		/* Loop through and add hexagons as well as the outline */
		for (int i = 0; i < board.getWidth(); i++) {
			for (int j = 0; j < board.getHeight(); j++) {
				Hexagon hex = tiles[i][j].getHexagon();

				/* Generate the Polygon Region */
				PolygonRegion polyReg = new PolygonRegion(
						MyGdxGame.textureGreen, hex.getVertices(),
						MyGdxGame.triangles);
				MyGdxGame.sprite_batch.begin();
				MyGdxGame.sprite_batch.draw(polyReg, 0, 0);
				MyGdxGame.sprite_batch.end();
				MyGdxGame.Project_Shape_Renderer.begin(ShapeType.Line);
				MyGdxGame.Project_Shape_Renderer.polygon(hex.getVertices());
				MyGdxGame.Project_Shape_Renderer.end();
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
