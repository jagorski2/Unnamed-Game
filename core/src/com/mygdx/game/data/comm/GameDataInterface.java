package com.mygdx.game.data.comm;

import java.util.List;

import com.app.models.Instance;
import com.app.models.Unit;
import com.app.models.User;
import com.mygdx.game.MyGame;
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
	
	/**
	 * this should never be called by the front-end, please use getBattleInstance instead
	 * @param id boardID
	 * @return
	 */
	public Board getBoard(int id);
	public User getUser(String playerId);
	public List<Unit> getPlayerUnits(String playerId);
	
	/**
	 * 
	 * @return true upon successful gamestate change
	 */
	public boolean moveUnit(int battleId,int unitId,int x,int y);


	/**
	 * 
	 * @param game instance of the only game
	 * @param instanceBean id's relevant to the query
	 */
	public void loadInstance(MyGame game,Instance instanceBean);
	
	
	/**
	 * gets all battle instances that the user is currently involved in
	 * 
	 * this should be used in the display all current battles/boards screen
	 * 
	 * 
	 * 
	 * @param user logged in User to fetch its associates instances
	 * @return
	 */
	public List<Instance> getInstances(User user);
	

	public List<Unit> getUnits(Instance instance, User user);
}
