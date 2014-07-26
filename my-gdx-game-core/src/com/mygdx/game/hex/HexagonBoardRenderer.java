package com.mygdx.game.hex;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class HexagonBoardRenderer {
 private Board board;
 
 public HexagonBoardRenderer(Board board) {
	 this.setBoard(board);
 }
 
 
 public void drawBoard() {
	 Hexagon[][] hexagons = board.getHexagons();
	 ShapeRenderer r = new ShapeRenderer();
	 PolygonSpriteBatch sprite_batch = new PolygonSpriteBatch();
	 r.setColor(Color.BLACK);
	 Texture black_tex = new Texture(Gdx.files.internal("textures/black.png"));
	 Texture red_tex = new Texture(Gdx.files.internal("textures/red.png"));
	 for (int i = 0; i < board.getWidth(); i++) {
		 for (int j = 0; j < board.getHeight(); j++) {
			 sprite_batch.begin();
			 if(((i % 2) == (j % 2))){
				sprite_batch.draw(red_tex,i*32,j*32); 
			 }else {

			 sprite_batch.draw(black_tex,i*32,j*32);
			 }
			 
			 sprite_batch.end();
			// Hexagon hex = hexagons[i][j];
			// r.begin(ShapeType.Line);
			// r.polygon(hex.getVertices());
			// r.end();
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
