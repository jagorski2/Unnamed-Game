package com.mygdx.game.data.comm;

import java.util.LinkedList;
import java.util.List;

import com.app.models.Instance;
import com.app.models.Unit;
import com.app.models.User;
import com.mygdx.game.BattleInstance;
import com.mygdx.game.InstanceUnit;
import com.mygdx.game.MyGame;
import com.mygdx.game.data.client.BoardClient;
import com.mygdx.game.data.client.InstancesClient;
import com.mygdx.game.data.client.UnitsClient;
import com.mygdx.game.hex.Board;

public class RealGameData implements GameDataInterface {

	@Override
	public Board getBoard(int id) {
		Board board = new Board(-30,-30,50);
       
		
        return board;
	}

	@Override
	public User getUser(String playerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean moveUnit(int battleId, int unitId, int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void loadInstance(MyGame game, Instance instanceBean) {
		
		Thread boardInitialize = new Thread(new BoardClient(game,instanceBean));
        boardInitialize.start();
	}

	@Override
	public List<Instance> getInstances(User user) {
		
		/*
		 * initialize the board to be returned
		 */
		List<Instance> list = new LinkedList<Instance>();
		
		/*
		 * start the request thread
		 */
		Thread thread = new Thread(new InstancesClient(list,user));
	thread.start();
		// TODO Auto-generated method stub
		return list;
	}

	@Override
	public List<Unit> getPlayerUnits(String playerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Unit> getUnits(Instance instance, User user) {
		List<Unit> retList = new LinkedList<Unit>();
		Thread thread = new Thread(new UnitsClient(retList,instance,user));
		thread.start();
		// TODO Auto-generated method stub
		return retList;
	}

	/**
	 * right now i see this method working by getting the ID associated with a name/password from the database 
	 * and only pulling back the ID if both columns match exactly.
	 */
	@Override
	public void login(User user) {
		user.setUserId(1);
	}

}
