package com.mygdx.game.hex;

import java.util.List;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.game.client.json.models.Tile;

public class Board {

	private Hexagon[][] hexagons;
	
	private boolean ready;				//used to determine whether or not the board is ready to play with
	
	private int width;
	private int height;

	private int xOffset;
	private int yOffset;
	private int side;
	private float pixelWidth;
	private float pixelHeight;

	/**
	 * 
	 * 0 1
	 * 
	 * 5 2
	 * 
	 * 4 3
	 * 
	 * Point 0 -> (x, y)
	 * 
	 */
	public Board(int xOffset, int yOffset, int width, int height, int side) {
		this.width = width;
		this.height = height;
		this.side = side;
		this.xOffset = xOffset + side;
		this.yOffset = yOffset + side;
		this.initialize();
	}
	
	public Board(int xOffset, int yOffset, int side) {
		this.side = side;
		this.xOffset = xOffset + side;
		this.yOffset = yOffset + side;
		ready = false;
	}

	/**
	 * 
	 * @param width
	 *            size of one edge of a hexagon
	 * @param height
	 *            in hexagons
	 * @param side
	 *            in hexagons
	 */
	/**
     * 
     */
	private void initialize() {

		float h = Math.CalculateH(side);
		float r = Math.CalculateR(side);
		hexagons = new Hexagon[width][height];

		float xTranslate = 0;
		float yTranslate = 0;

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (j % 2 == 1)
					xTranslate = i * (side * 2 + h * 2);
				else
					xTranslate = i * (side * 2 + h * 2) + side + h;
				if (i % 2 == 0)
					yTranslate = j * r;
				else
					yTranslate = j * r;

				hexagons[i][j] = new Hexagon(xOffset + xTranslate, yOffset
						+ yTranslate, side);

			}
		}
		ready = true;
	}
	
	public void initialize(List<Tile> tiles) {
		float h = Math.CalculateH(side);
		float r = Math.CalculateR(side);
		
		Point point = this.determineGridBounds(tiles);
		width = (int) point.getX();
		height = (int) point.getY();
		hexagons = new Hexagon[width][height];

		float xTranslate = 0;
		float yTranslate = 0;
		
		for (int i = 0; i < tiles.size(); i ++) {
			
			Tile tile = tiles.get(i);
			int x = tile.getX();
			int y = tile.getY();
			
			if (x == 1) {
				System.out.println("x is 1");
			}
			
			if (y % 2 == 1)
				xTranslate = x * (side * 2 + h * 2);
			else
				xTranslate = x * (side * 2 + h * 2) + side + h;
			if (x % 2 == 0)
				yTranslate = y * r;
			else
				yTranslate = y * r;
			System.out.println(tile.toString());
			Hexagon hex = new Hexagon(xOffset + xTranslate,yOffset + yTranslate , side);
			hexagons[x][y] = hex;
		}
		
		Hexagon hex = new Hexagon(0,0,2);
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (hexagons[i][j] == null) {
					hexagons[i][j] = hex;
				}
			}
		}
		ready = true;
	}
	
	private Point determineGridBounds(List<Tile> tiles) {

		int greatestX = 0;
		int greatestY = 0;
		
		for (Tile tile : tiles) {
			if (tile.getX() > greatestX) {
				greatestX = tile.getX();
			}
			if (tile.getY() > greatestY) {
				greatestY = tile.getY();
			}	
		}
		
		return new Point(greatestX,greatestY);
	}

	public Hexagon closestHexagon(Vector3 vect) {
		float x = vect.x;
		float y = vect.y;
		Vector3 centerVect = new Vector3();
		double closest = 999999999;
		double dist;
		Hexagon ret = null;
		for (int i = 0; i < getWidth(); i++) {
			for (int j = 0; j < getHeight(); j++) {
				Hexagon hex;
				hex = getHexagons()[i][j];
				centerVect.set(hex.getCenter().getX(), hex.getCenter().getY(),0);
				dist = Math.distance(x, centerVect.x, y, centerVect.y);
				if (dist < closest) {
					closest = dist;
					ret = hex;
				}

			}
		}
		return ret;

	}
	
	public void loadBoard(List<Tile> tiles) {
		
	}

	public Hexagon[][] getHexagons() {
		return hexagons;
	}

	public void setHexagons(Hexagon[][] hexagons) {
		this.hexagons = hexagons;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getxOffset() {
		return xOffset;
	}

	public void setxOffset(int xOffset) {
		this.xOffset = xOffset;
	}

	public int getyOffset() {
		return yOffset;
	}

	public void setyOffset(int yOffset) {
		this.yOffset = yOffset;
	}

	public int getSide() {
		return side;
	}

	public void setSide(int side) {
		this.side = side;
	}

	public float getPixelWidth() {
		return pixelWidth;
	}

	public void setPixelWidth(float pixelWidth) {
		this.pixelWidth = pixelWidth;
	}

	public float getPixelHeight() {
		return pixelHeight;
	}

	public void setPixelHeight(float pixelHeight) {
		this.pixelHeight = pixelHeight;
	}

	public boolean isReady() {
		return ready;
	}

	public void setReady(boolean ready) {
		this.ready = ready;
	}
}
