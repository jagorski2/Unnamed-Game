package com.mygdx.game.hex;

import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.play.Tile;
import com.mygdx.game.play.Unit;
import com.mygdx.game.utils.UnitTypeConstants;

public class Board {

	private Tile[][] tiles;
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
		Initialize();
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
	private void Initialize() {

		float h = Math.CalculateH(side);
		float r = Math.CalculateR(side);
		tiles = new Tile[width][height];

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

				tiles[i][j] = new Tile();
				Hexagon hex = new Hexagon(xOffset + xTranslate, yOffset+ yTranslate, side);
				tiles[i][j].setHexagon(hex);
				if (i == 1 && j == 1 ){
					tiles[i][j].setOccupied(true);
					tiles[i][j].setUnit(new Unit(UnitTypeConstants.Warrior));

				}
				if (i == 1 && j == 2 ){
					tiles[i][j].setOccupied(true);
					tiles[i][j].setUnit(new Unit(UnitTypeConstants.Mage));

				}
				if (i == 1 && j == 3 ){
					tiles[i][j].setOccupied(true);
					tiles[i][j].setUnit(new Unit(UnitTypeConstants.Archer));

				}
			}
		}
	}

	public Tile closestTile(Vector3 vect) {
		float x = vect.x;
		float y = vect.y;
		Vector3 centerVect = new Vector3();
		double closest = 999999999;
		double dist;
		Tile ret = null;
		for (int i = 0; i < getWidth(); i++) {
			for (int j = 0; j < getHeight(); j++) {
				Hexagon hex;
				hex = tiles[i][j].getHexagon();
				centerVect.set(hex.getCenter().getX(), hex.getCenter().getY(),
						0);
				dist = Math.distance(x, centerVect.x, y, centerVect.y);
				if (dist < closest) {
					closest = dist;
					ret = tiles[i][j];
				}

			}
		}
		return ret;

	}

	public Tile[][] getTiles() {
		return tiles;
	}

	public void setTiles(Tile[][] tiles) {
		this.tiles = tiles;
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
}
