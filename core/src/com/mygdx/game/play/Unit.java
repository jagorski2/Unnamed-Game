package com.mygdx.game.play;

import com.mygdx.game.hex.Point;

public class Unit 
{
	
	private Point position;
	private int type;
	private boolean isSelected;
	
	
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
		
	}
}
