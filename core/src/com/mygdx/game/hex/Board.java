package com.mygdx.game.hex;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.game.BoardScreen;
import com.mygdx.game.Tile;
import com.mygdx.game.Unit;
import com.mygdx.game.data.json.BoardsTile;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.utils.UnitTypeConstants;

public class Board {

	private boolean ready;				//used to determine whether or not the board is ready to play with
	
	private Tile[][] tiles;
	private int width;
	private int height;

	private int xOffset;
	private int yOffset;
	private int side;
	private float pixelWidth;
	private float pixelHeight;
	
	private List<BoardsTile> boardsTiles;

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
					tiles[i][j].setUnit(new Unit(UnitTypeConstants.WARRIOR));

				}
				if (i == 1 && j == 2 ){
					tiles[i][j].setOccupied(true);
					tiles[i][j].setUnit(new Unit(UnitTypeConstants.MAGE));

				}
				if (i == 1 && j == 3 ){
					tiles[i][j].setOccupied(true);
					tiles[i][j].setUnit(new Unit(UnitTypeConstants.ARCHER));

				}
				
				
			}
		}
		ready = true;
	}
	
	public void initializeWithTiles() {
		float h = Math.CalculateH(side);
		float r = Math.CalculateR(side);
		
		Point point = this.determineGridBounds();
		width = (int) point.getX();
		height = (int) point.getY();
		tiles = new Tile[width][height];

		float xTranslate = 0;
		float yTranslate = 0;
		
		for (int i = 0; i < boardsTiles.size(); i ++) {
			BoardsTile tile = boardsTiles.get(i);
			int x = tile.getX();
			int y = tile.getY();
			
			if (y % 2 == 1)
				xTranslate = x * (side * 2 + h * 2);
			else
				xTranslate = x * (side * 2 + h * 2) + side + h;
			if (x % 2 == 0)
				yTranslate = y * r;
			else
				yTranslate = y * r;
			
			tiles[x][y] = new Tile();
			Hexagon hex = new Hexagon(xOffset + xTranslate, yOffset+ yTranslate, side);
			tiles[x][y].setHexagon(hex);
			tiles[x][y].setType(tile.getType());
			Unit unit = null;
			
			if (x == 1 && y == 1 ){
				tiles[x][y].setOccupied(true);
				unit = new Unit(UnitTypeConstants.WARRIOR);
				unit.setColor(Color.RED);
				unit.setHexagon(tiles[x][y].getHexagon());
			}
			else if (x == 1 && y == 2 ){
				tiles[x][y].setOccupied(true);
				unit = new Unit(UnitTypeConstants.MAGE);
				unit.setColor(Color.BLUE);
				unit.setHexagon(tiles[x][y].getHexagon());
			}
			else if (x == 1 && y == 3 ){
				tiles[x][y].setOccupied(true);
				unit = new Unit(UnitTypeConstants.ARCHER);
				unit.setColor(Color.GREEN);
				unit.setHexagon(tiles[x][y].getHexagon());
			}
			tiles[x][y].setUnit(unit);
		}
		
		Tile tile = new Tile();
		tile.setType(10);
		Hexagon hex = new Hexagon(0,0,2);
		tile.setHexagon(hex);
		
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (tiles[i][j] == null) {
					tiles[i][j] = tile;
				}
			}
		}
		boardsTiles = null;
		ready = true;
	}
	
	private Point determineGridBounds() {
		int greatestX = 0;
		int greatestY = 0;
		
		for (BoardsTile tile : boardsTiles) {
			if (tile.getX() > greatestX) {
				greatestX = tile.getX();
			}
			if (tile.getY() > greatestY) {
				greatestY = tile.getY();
			}	
		}
		
		return new Point(greatestX + 1, greatestY + 1);
	}

	public Tile getClosestTile(Vector3 vect) {
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
	
	public void drawBoard() {

		/* Line Drawing Inits */
		BoardScreen.project_shape_renderer.setProjectionMatrix(BoardScreen.camera.combined);
		BoardScreen.project_shape_renderer.setColor(Color.BLACK);

		/* Hexagon inits */
		// TextureRegion textureGreen; = new TextureRegion(new Texture
		// Gdx.files.internal("textures/grass.jpg")), 800, 800);


		BoardScreen.sprite_batch.setProjectionMatrix(BoardScreen.camera.combined);

		/* Loop through and add hexagons as well as the outline */
		for (int i = 0; i < this.getWidth(); i++) {
			for (int j = 0; j < this.getHeight(); j++) {
				Tile tile = tiles[i][j];
				Hexagon hex = tile.getHexagon();

				/* Generate the Polygon Region */
				PolygonRegion polyReg = new PolygonRegion(
						BoardScreen.textureGreen, hex.getVertices(),
						BoardScreen.triangles);
				BoardScreen.sprite_batch.begin();
				BoardScreen.sprite_batch.draw(polyReg, 0, 0);
				BoardScreen.sprite_batch.end();
				BoardScreen.project_shape_renderer.begin(ShapeType.Line);
				BoardScreen.project_shape_renderer.polygon(hex.getVertices());
				BoardScreen.project_shape_renderer.end();
			}
		}
	}
	
	public boolean isReady() {
		return ready;
	}

	public void setReady(boolean ready) {
		this.ready = ready;
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

	public List<BoardsTile> getBoardsTiles() {
		return boardsTiles;
	}

	public void setBoardsTiles(List<BoardsTile> boardsTiles) {
		this.boardsTiles = boardsTiles;
	}
	public Tile getTile(int x,int y) {
		Tile tile = null;
		if (x >= tiles.length) {
			return tile;
		}
		if (y >= tiles[0].length) {
			return tile;
		}
		tile = tiles[x][y];
		return tile;
	}
}
