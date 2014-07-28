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
	
    private double distance(double x1, double x2, double y1, double y2) {
    	return java.lang.Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
       
      }
    
	private void closestHexagon(int x, int y) {
		double closest = 999999999;

		double dist;
		for (int i = 0; i < MyGdxGame.hex_number; i++) {
			dist = distance(x, MyGdxGame.hex_center[i][0], y,
					MyGdxGame.hex_center[i][1]);
			 //System.out.println(closest);
			if (dist < closest) {
				closest = dist;
				MyGdxGame.Current_Hexagon = MyGdxGame.Vert_Array[i];
				// System.out.println(Arrays.toString(MyGdxGame.Vert_Array[i]));
			}

		}

	}


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
		closestHexagon(Gdx.input.getX(),Gdx.input.getY());
		System.out.println(distance(Gdx.input.getX() , 0, Gdx.input.getY(), 0));
		ShapeRenderer r = new ShapeRenderer();
		r.setColor(Color.WHITE);

		for (int i = 0; i < MyGdxGame.hex_number; i++) {
		//	System.out.println("Current: " +Arrays.toString(Current_Hexagon)+ " CurrentlyDrawn: " + Arrays.toString(MyGdxGame.Vert_Array[i]));

			
			if( MyGdxGame.Current_Hexagon == MyGdxGame.Vert_Array[i]){
				MyGdxGame.polyReg = new PolygonRegion(MyGdxGame.textureGrass,MyGdxGame.Vert_Array[i], MyGdxGame.triangles);
			}
			else{
				MyGdxGame.polyReg = new PolygonRegion(MyGdxGame.textureSolid,MyGdxGame.Vert_Array[i], MyGdxGame.triangles);
			}
			MyGdxGame.sprite_batch.begin();
			MyGdxGame.sprite_batch.draw(MyGdxGame.polyReg, 0, 0);
			MyGdxGame.sprite_batch.end();
			r.begin(ShapeType.Line);
			r.circle(MyGdxGame.hex_center[i][0], MyGdxGame.hex_center[i][1], 1);
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
