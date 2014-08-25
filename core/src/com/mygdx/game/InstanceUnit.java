package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.mygdx.game.hex.Hexagon;
import com.mygdx.game.hex.Point;
import com.mygdx.game.screens.BoardScreen;

public class InstanceUnit 
{
	
	private Point position;
	private boolean isSelected;
	private com.app.models.Unit unitBean;
	private Color color;
	private Hexagon hexagon;
	
	public InstanceUnit(com.app.models.Unit unit) {
		this.unitBean = unit;
	}


	public Hexagon getHexagon() {
		return hexagon;
	}


	public void setHexagon(Hexagon hexagon) {
		this.hexagon = hexagon;
	}

	public Point getPosition() 
	{
		return position;
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


	public com.app.models.Unit getUnitBean() {
		return unitBean;
	}


	public void setUnitBean(com.app.models.Unit unitBean) {
		this.unitBean = unitBean;
	}
}
