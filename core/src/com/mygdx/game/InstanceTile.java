package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.mygdx.game.hex.Hexagon;

public class InstanceTile {

	private Hexagon hexagon;
	private PolygonSprite polygonSprite;
	private InstanceUnit unit;
	private int type;
	private boolean occupied;
	private boolean isSelected;

	public InstanceTile(Hexagon hexagon) {
		this.hexagon = hexagon;
	}
	public PolygonSprite getPolygonSprite() {
		return polygonSprite;
	}

	public void setPolygonSprite(PolygonSprite polygonSprite) {
		this.polygonSprite = polygonSprite;
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
	public boolean isSelected() {
		return isSelected;
	}
	public void setSelected(boolean sel){
		isSelected = sel;
	}

}
