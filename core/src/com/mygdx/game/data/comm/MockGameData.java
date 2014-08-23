package com.mygdx.game.data.comm;

import java.util.LinkedList;
import java.util.List;

import com.mygdx.game.Unit;
import com.mygdx.game.data.json.User;
import com.mygdx.game.hex.Board;

public class MockGameData implements GameDataInterface {

	@Override
	public Board getBoard(int id) {
		
		Board board = new Board(-30, -30, 4, 8, 50);
		return board;
	}

	@Override
	public List<Board> getBoards(int playerId) {
		Board board = new Board(-30, -30, 3, 3, 50);
		Board board1 = new Board(-30, -30, 5, 5, 30);
		Board board2 = new Board(-30, -30, 6, 6, 40);
		List<Board> list = new LinkedList<Board>();
		list.add(board);
		list.add(board1);
		list.add(board2);
		return list;
	}

	@Override
	public User getUser(String playerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Unit> getPlayerUnits(String playerId) {
		// TODO Auto-generated method stub
		return null;
	}

}
