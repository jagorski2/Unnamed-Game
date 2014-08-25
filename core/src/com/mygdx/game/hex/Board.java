package com.mygdx.game.hex;
import java.util.List;

import com.app.models.Tile;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.InstanceTile;
import com.mygdx.game.screens.BoardScreen;

public class Board {

	private boolean ready;				//used to determine whether or not the board is ready to play with
	
	private InstanceTile[][] tiles;
	private int width;
	private int height;

	private int xOffset;
	private int yOffset;
	private int side;
	private float pixelWidth;

	private float pixelHeight;
	
	private List<Tile> tileBeans;
	
	public Board(int xOffset, int yOffset, int side) {
		this.side = side;
		this.xOffset = xOffset + side;
		this.yOffset = yOffset + side;
		ready = false;
	}
	
	public void initialize() {
		float h = Math.CalculateH(side);
		float r = Math.CalculateR(side);
		
		Point point = this.determineGridBounds();
		width = (int) point.getX();
		height = (int) point.getY();
		tiles = new InstanceTile[width][height];

		float xTranslate = 0;
		float yTranslate = 0;
		
		for (int i = 0; i < tileBeans.size(); i ++) {
			
			Tile tile = tileBeans.get(i);
			
			int x = tile.getX();
			int y = tile.getY();
			
			yTranslate = y * (r * 2);
			xTranslate = x * (side + h);
			if (x % 2 == 1) {
				yTranslate += r;
			}
			
			Hexagon hex = new Hexagon(xOffset + xTranslate, yOffset+ yTranslate, side);
			InstanceTile instanceTile = new InstanceTile(hex);
			instanceTile.setType(tile.getType());
			tiles[x][y] = instanceTile;
		}
		Hexagon hex = new Hexagon(0,0,2);
		InstanceTile tile = new InstanceTile(hex);
		tile.setType(10);
		
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (tiles[i][j] == null) {
					tiles[i][j] = tile;
				}
			}
		}
		tileBeans = null;
		ready = true;
	}
	
	private Point determineGridBounds() {
		int greatestX = 0;
		int greatestY = 0;
		
		for (Tile tile : tileBeans) {
			if (tile.getX() > greatestX) {
				greatestX = tile.getX();
			}
			if (tile.getY() > greatestY) {
				greatestY = tile.getY();
			}	
		}
		
		return new Point(greatestX + 1, greatestY + 1);
	}

	public InstanceTile getClosestTile(Vector3 vect) {
		float x = vect.x;
		float y = vect.y;
		Vector3 centerVect = new Vector3();
		double closest = 999999999;
		double dist;
		InstanceTile ret = null;
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
					//System.out.println(i + " : " + j);
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
				InstanceTile tile = tiles[i][j];
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
	
	/**
	 * Use this method to expose a tile, do not use the array of tiles
	 * @param x
	 * @param y
	 * @return
	 */
	public InstanceTile getTile(int x,int y) {
		InstanceTile tile = null;
		if (x >= tiles.length) {
			return tile;
		}
		if (y >= tiles[0].length) {
			return tile;
		}
		tile = tiles[x][y];
		return tile;
	}
	
	public List<Tile> getTileBeans() {
		return tileBeans;
	}

	public void setTileBeans(List<Tile> tileBeans) {
		this.tileBeans = tileBeans;
	}
}
