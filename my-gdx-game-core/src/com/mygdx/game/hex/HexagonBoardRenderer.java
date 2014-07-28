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

	public void initHexagons() {
		Hexagon[][] hexagons = board.getHexagons();
		for (int i = 0; i < board.getWidth(); i++) {
			for (int j = 0; j < board.getHeight(); j++) {
				Hexagon hex = hexagons[i][j];
				MyGdxGame.Vert_Array[MyGdxGame.hex_number] = hex.getVertices();
				MyGdxGame.hex_center[MyGdxGame.hex_number] = hex.getCenter();
				MyGdxGame.hex_number++;
			}
		}

	}

	public void drawBoard() {

		ShapeRenderer r = new ShapeRenderer();
		r.setColor(Color.WHITE);
		for (int i = 0; i < MyGdxGame.hex_number; i++) {
			MyGdxGame.polyReg = new PolygonRegion(MyGdxGame.textureSolid,MyGdxGame.Vert_Array[i], MyGdxGame.triangles);
			MyGdxGame.sprite_batch.begin();
			MyGdxGame.sprite_batch.draw(MyGdxGame.polyReg, 0, 0);
			MyGdxGame.sprite_batch.end();
			r.begin(ShapeType.Line);
			r.polygon(MyGdxGame.Vert_Array[i]);
			r.end();
		}

	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}
}
