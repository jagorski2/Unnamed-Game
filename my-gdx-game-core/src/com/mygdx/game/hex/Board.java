package com.mygdx.game.hex;

public class Board {
	
    private Hexagon[][] hexagons;
    private int width;
    private int height;
 
	private int xOffset;
    private int yOffset;
    private int side;
    private float pixelWidth;
    private float pixelHeight;
    
    /**
     * 
     *            0    1
     *         
     *          5        2
     * 
     *            4    3
     * 
     *        Point 0 -> (x, y)
     * 
     */
    public Board(int xOffset, int yOffset, int width,int height, int side) {
    	this.width = width;
    	this.height = height;
    	this.side = side;
    	this.xOffset = xOffset + side;
    	this.yOffset = yOffset + side;
    	Initialize();
    }
    /**
     * 
     * @param width size of one edge of a hexagon
     * @param height in hexagons
     * @param side in hexagons
     */
    /**
     * 
     */
    private void Initialize() {
    	
    	float h = Math.CalculateH(side);
    	float r = Math.CalculateR(side);
    	hexagons = new Hexagon[width][height];
    	
    	float xTranslate = 0;
    	float yTranslate = 0;
    	
    	for (int i = 0; i < width; i ++) {
    		for (int j = 0; j < height; j ++) {
    			if (j%2 == 1)
    				xTranslate = i * (side*2 + h*2);
    			else 
    				xTranslate = i * (side*2 + h*2) + side + h;
    			if (i%2 == 0) 
    				yTranslate = j * r;
    			else 
    				yTranslate = j * r;
    			
    			hexagons[i][j] = new Hexagon(xOffset + xTranslate,yOffset + yTranslate,side);
    			
    		}
    	}
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
}
