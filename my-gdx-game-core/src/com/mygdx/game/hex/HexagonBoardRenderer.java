package com.mygdx.game.hex;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
	 Texture black_tex = new Texture(Gdx.files.internal("textures/black.png"));
	 Texture red_tex = new Texture(Gdx.files.internal("textures/red.png"));
	 Texture hex_tex = new Texture(Gdx.files.internal("textures/hex.png"));
	 
	 
	 
	// textureSolid = makeTextureBox(1, 0xFFFF0000, 0, 0); 
	 

	  /*  PolygonRegion polyReg = new PolygonRegion(new TextureRegion(textureSolid), new float[] {
	        a*0, b*0,
	        a*0, b*2,
	        a*3, b*2,
	        a*3, b*0,
	        a*2, b*0,
	        a*2, b*1,
	        a*1, b*1,
	        a*1, b*0,
	    }); */
	    //PolygonSprite poly = new PolygonSprite(polyReg);
	   // poly.setOrigin(a, b);
	    //PolygonSpriteBatch polyBatch = new PolygonSpriteBatch();
	 

	 
	 ShapeRenderer r = new ShapeRenderer();
	 PolygonSpriteBatch sprite_batch = new PolygonSpriteBatch();
	 r.setColor(Color.BLACK);
	 for (int i = 0; i < board.getWidth(); i++) {
		 for (int j = 0; j < board.getHeight(); j++) {
			 sprite_batch.begin();
			// if(((i % 2) == (j % 2))){
				 
				 Hexagon hex = hexagons[i][j];
				 TextureRegion textureSolid = new TextureRegion(hex_tex,32,32);
			     PolygonRegion polyReg = new PolygonRegion(textureSolid , hex.getVertices(), new short[] {0, 1, 2, 3, 4, 5});
				 
				sprite_batch.draw(polyReg,0,0); 
				
			// }else {

			 //sprite_batch.draw(black_tex,i*32,j*32);
			// }
			 
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
