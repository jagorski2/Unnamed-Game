package com.mygdx.game.hex;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
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
	 r.setColor(Color.BLACK);
	 
	 for (int i = 0; i < board.getWidth(); i++) {
		 for (int j = 0; j < board.getHeight(); j++) {
			 Hexagon hex = hexagons[i][j];
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
