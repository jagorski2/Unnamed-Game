package com.mygdx.game.client.json.models;

public class BoardsTile {
	private int boardId;
	private int x;
	private int y;
	private int type;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	public String toString() {
		return "Tile { boardId:" + getBoardId() + ", x:" + getX() + ", y:" + getY() + ", type:" + getType() + "}";
	}
}
