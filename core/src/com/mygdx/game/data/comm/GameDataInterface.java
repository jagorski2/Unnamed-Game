package com.mygdx.game.data.comm;

import java.util.List;

import com.mygdx.game.Unit;
import com.mygdx.game.data.json.User;
import com.mygdx.game.hex.Board;

public interface GameDataInterface {
	
	public Board getBoard(int id);
	public List<Board> getBoards(int playerId);
	public User getUser(String playerId);
	public List<Unit> getPlayerUnits(String playerId);

}
