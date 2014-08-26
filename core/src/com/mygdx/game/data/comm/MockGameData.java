package com.mygdx.game.data.comm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.app.models.Instance;
import com.app.models.Tile;
import com.app.models.User;
import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.BattleInstance;
import com.mygdx.game.InstanceUnit;
import com.mygdx.game.hex.Board;

public class MockGameData implements GameDataInterface {

	@Override
	public Board getBoard(int id) {
		
		Board board = new Board(-30, -30, 30);
		List<Tile> list = new ArrayList<Tile>();
		switch (id) {
		case 1:
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 3; j++) {
					Tile tile = new Tile();
					tile.setBoardId(id);
					tile.setType(id);
					tile.setX(i);
					tile.setY(j);
					list.add(tile);
				}
			}
			break;
		case 2:
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					
					Tile tile = new Tile();
					tile.setBoardId(id);
					tile.setType(id);
					tile.setX(i);
					tile.setY(j);
					list.add(tile);
				}
			}
			break;
		case 3:
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					Tile tile = new Tile();
					tile.setBoardId(id);
					tile.setType(id);
					tile.setX(i);
					tile.setY(j);
					list.add(tile);
				}
			}
			break;
		}
		
		
		board.setTileBeans(list);
		board.initialize();
		return board;
	}

	@Override
	public List<Board> getBoards(int playerId) {
		List<Board> list = new LinkedList<Board>();
		list.add(this.getBoard(1));
		list.add(this.getBoard(2));
		list.add(this.getBoard(3));
		return list;
	}

	@Override
	public User getUser(String playerId) {
		User user = new User();
		user.setUserId(69);
		user.setName("fernando");
		user.setPassword("fernando");
		return user;
	}

	@Override
	public List<InstanceUnit> getPlayerUnits(String playerId) {
		return null;
	}

	@Override
	public boolean moveUnit(int battleId, int unitId, int x, int y) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public BattleInstance getBattleInstance(Instance instanceBean) {
		
		/*
		 * get board from DB
		 */
		Board board = this.getBoard(instanceBean.getBoardId());
		
		/*
		 * create battleInstance
		 */
		BattleInstance battleInstance = new BattleInstance(board,instanceBean);
		
		return battleInstance;
	}

	@Override
	public List<Instance> getInstances(User user) {
		
		List<Instance> list = new LinkedList<Instance>();
		for (int i = 1; i <= 3; i ++) {
			Instance instance = new Instance();
			instance.setBoardId(i);
			instance.setInstanceId(i);
			instance.setMissionId(i);
			instance.setTurnId(i);
			list.add(instance);
		}
		return list;
	}

}
