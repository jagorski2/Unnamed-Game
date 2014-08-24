package com.mygdx.game.data.comm;

import java.util.List;

import com.mygdx.game.Unit;
import com.mygdx.game.data.json.User;
import com.mygdx.game.hex.Board;
/**
 *  Functions as a gatekeeper between front-end and back-end. talks to the server for any CRUD purposes
 *  
 *  Currently there are two classes that implement this, MockGameData and RealGameData
 *  
 * @author ianno_000
 *
 */
public interface GameDataInterface {
	
	public Board getBoard(int id);
	public List<Board> getBoards(int playerId);
	public User getUser(String playerId);
	public List<Unit> getPlayerUnits(String playerId);
	
	/**
	 * 
	 * @return true upon successful gamestate change
	 */
	public boolean moveUnit(int battleId,int unitId,int x,int y);
}
