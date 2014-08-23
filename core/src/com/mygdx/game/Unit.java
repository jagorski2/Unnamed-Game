package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.mygdx.game.hex.Hexagon;
import com.mygdx.game.hex.Point;

public class Unit 
{
	
	private Point position;
	private int type;
	private boolean isSelected;
	private Color color;
	private Hexagon hexagon;
	
	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}


	public Hexagon getHexagon() {
		return hexagon;
	}


	public void setHexagon(Hexagon hexagon) {
		this.hexagon = hexagon;
	}

	
	public Unit (int unitType){
		this.type = unitType;
	}
	
	
	public Point getPosition() 
	{
		return position;
	}
	public void setUnitType(int unitType) 
	{
		this.type = unitType;
	}	
	public int getUnitType() 
	{
		return this.type;
	}	
	public void setSelected(boolean selected) 
	{
		this.isSelected = selected;
	}	
	public boolean isSelected() 
	{
		return this.isSelected;
	}	
	
	public void setPosition(Point position)
	{
		this.position = position;
	}
	
	/*
	 * Draw the unit on the board.
	 */
	public void drawUnit() 
	{
		BoardScreen.project_shape_renderer.setColor(color);
		BoardScreen.project_shape_renderer.begin(ShapeType.Line);
		BoardScreen.project_shape_renderer.polygon(hexagon.getVertices());
		BoardScreen.project_shape_renderer.end();
	}


	public Color getColor() {
		return color;
	}


	public void setColor(Color color) {
		this.color = color;
	}
}
