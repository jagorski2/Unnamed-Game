package com.mygdx.game.client;

import com.mygdx.game.client.json.JsonClient;
import com.mygdx.game.hex.Board;

public class BoardClient implements Runnable {
	
	private static final String uri = "/tiles";
	private final Board board;
	public BoardClient(Board board) {
		this.board = board;
	}

	@Override
	public void run() {
		JsonClient.getInstance().mySendRequest(board, uri);
		
		while (board.getBoardsTiles() == null) {
			Thread.yield();
		}
		
		board.initializeWithTiles();
	}
}
