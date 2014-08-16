package com.mygdx.game.client;

import com.mygdx.game.hex.Board;

public class BoardRunnable implements Runnable{
	
	private Board board;
	public BoardRunnable(Board board) {
		this.board = board;
	}
	
	@Override
	public void run() {
		board.initializeWithTiles();
		Thread.yield();
	}
	
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}

}
