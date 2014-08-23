package com.mygdx.game.data.comm;

import java.util.List;

import com.mygdx.game.Unit;
import com.mygdx.game.data.client.BoardClient;
import com.mygdx.game.data.json.User;
import com.mygdx.game.hex.Board;

public class GameData implements GameDataInterface {

	@Override
	public Board getBoard(int id) {
		Board board = new Board(-30,-30,40);
       
		Thread boardInitialize = new Thread(new BoardClient(board));
        boardInitialize.start();
        
        return board;
	}

	@Override
	public List<Board> getBoards(int playerId) {
		// TODO Auto-generated method stub
		return null;
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
