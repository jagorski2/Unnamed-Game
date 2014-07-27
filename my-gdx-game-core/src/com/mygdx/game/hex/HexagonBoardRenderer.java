package com.mygdx.game.hex;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

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
	 TextureRegion textureSolid = new TextureRegion(new Texture(Gdx.files.internal("textures/grass.jpg")),800,800);
	 PolygonSpriteBatch sprite_batch = new PolygonSpriteBatch();
	 
	 /* Loop through and add hexagons as well as the outline */
	 for (int i = 0; i < board.getWidth(); i++) {
		 for (int j = 0; j < board.getHeight(); j++) {
			
			 Hexagon hex = hexagons[i][j];
			 /* Generate the Polygon Region*/
			 PolygonRegion polyReg = 
					 new PolygonRegion(textureSolid,  			/* Type Texture Region */
					 				   hex.getVertices(),		/* Type Float[] Returned from hex.getVertices */
					 				   new short[]				/* Type short[] This is the vertices of the triangles that make up the hexagon*/
					 						  { 0,3,1,			/* Point 0 -> Point 3 -> 1 */
		    		 							1,4,2,			/* Point 1 -> Point 4 -> 2 */
		    		 							2,5,3,			/* Point 2 -> Point 5 -> 3 */
		    		 							3,0,4,			/* Point 3 -> Point 0 -> 4 */
		    		 							4,1,5,			/* Point 4 -> Point 1 -> 5 */
		    		 							5,2,0 });		/* Point 5 -> Point 2 -> 6 */
					 
			sprite_batch.begin();
			sprite_batch.draw(polyReg,0,0);
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
