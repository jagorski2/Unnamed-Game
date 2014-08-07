package com.mygdx.game.play;

import com.mygdx.game.hex.Hexagon;

public class Tile {

	private Hexagon hexagon;
	private Unit unit;
	private int type;
	private boolean occupied;
	
	
	public Hexagon getHexagon() {
		return hexagon;
	}
	public void setHexagon(Hexagon hexagon) {
		this.hexagon = hexagon;
	}
	public Unit getUnit() {
		return unit;
	}
	public void setUnit(Unit unit) {
		this.unit = unit;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public boolean isOccupied() {
		return occupied;
	}
	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}
	
}
