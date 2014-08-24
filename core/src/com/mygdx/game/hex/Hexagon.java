package com.mygdx.game.hex;

public class Hexagon {

	private Point[] points;
	private float[] vertices;
	private Point center;
	private float side;
	private float h;
	private float r;
	private float x;
	private float y;

	public Hexagon(float x, float y, float side) {
		this.x = x;
		this.y = y;
		this.side = side;
		this.CalculateVertices();
	}
	
	private void CalculateVertices() {
		this.h = Math.CalculateH(side);
		this.r = Math.CalculateR(side);
		// x,y coordinates are top left point
		points = new Point[6];
		points[0] = new Point(x, y);
		points[1] = new Point(x + side, y);
		points[2] = new Point(x + side + h, y + r);
		points[3] = new Point(x + side, y + r + r);
		points[4] = new Point(x, y + r + r);
		points[5] = new Point(x - h, y + r);
		center = new Point(x + (int) (side / 2), y + r);

		vertices = new float[12]; // because there is 6 vertices in a hexagon,
									// hence 12 floats to represent all the
									// coordinates

		for (int i = 0; i < points.length; i++) {
			Point p = points[i];
			vertices[i * 2] = p.getX();
			vertices[i * 2 + 1] = p.getY();
		}

	}

	public Point getCenter() {
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}

	public Point[] getPoints() {
		return points;
	}

	public void setPoints(Point[] points) {
		this.points = points;
	}

	public float getSide() {
		return side;
	}

	public void setSide(float side) {
		this.side = side;
	}

	public float getH() {
		return h;
	}

	public void setH(float h) {
		this.h = h;
	}

	public float getR() {
		return r;
	}

	public void setR(float r) {
		this.r = r;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float[] getVertices() {
		return vertices;
	}

	public void setVertices(float[] vertices) {
		this.vertices = vertices;
	}
}
