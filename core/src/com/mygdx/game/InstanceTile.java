package com.mygdx.game;

import com.mygdx.game.hex.Hexagon;

public class InstanceTile {

	private Hexagon hexagon;
	private InstanceUnit unit;
	private int type;
	private boolean occupied;

	public InstanceTile(Hexagon hexagon) {
		this.hexagon = hexagon;
	}
	public Hexagon getHexagon() {
		return hexagon;
	}

	public void setHexagon(Hexagon hexagon) {
		this.hexagon = hexagon;
	}

	public InstanceUnit getUnit() {
		return unit;
	}

	public void setUnit(InstanceUnit unit) {
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
