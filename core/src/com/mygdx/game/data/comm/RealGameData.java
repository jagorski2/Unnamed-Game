package com.mygdx.game.data.comm;

import java.util.List;

import com.app.models.Instance;
import com.app.models.User;
import com.mygdx.game.BattleInstance;
import com.mygdx.game.InstanceUnit;
import com.mygdx.game.data.client.BoardClient;
import com.mygdx.game.data.client.InstanceClient;
import com.mygdx.game.hex.Board;

public class RealGameData implements GameDataInterface {

	@Override
	public Board getBoard(int id) {
		Board board = new Board(-30,-30,50);
       
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
	public List<InstanceUnit> getPlayerUnits(String playerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean moveUnit(int battleId, int unitId, int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public BattleInstance getBattleInstance(Instance instanceBean) {
		BattleInstance instance = null;
		
		/*
		 * initialize my board
		 */
		Board board = new Board(-30,-30,50);
	       
		Thread boardInitialize = new Thread(new BoardClient(board));
        boardInitialize.start();
        
        /*
         * give the battleInstance references to its composed beans
         */
		instance = new BattleInstance(board,instanceBean);
		return instance;
	}

}
