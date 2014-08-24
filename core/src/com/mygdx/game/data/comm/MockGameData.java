package com.mygdx.game.data.comm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.Unit;
import com.mygdx.game.data.json.BoardsTile;
import com.mygdx.game.data.json.User;
import com.mygdx.game.hex.Board;

public class MockGameData implements GameDataInterface {

	@Override
	public Board getBoard(int id) {
		
		Board board = new Board(-30, -30, 30);
		List<BoardsTile> list = new ArrayList<BoardsTile>();
		switch (id) {
		case 1:
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 3; j++) {
					BoardsTile tile = new BoardsTile();
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
					
					BoardsTile tile = new BoardsTile();
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
					BoardsTile tile = new BoardsTile();
					tile.setBoardId(id);
					tile.setType(id);
					tile.setX(i);
					tile.setY(j);
					list.add(tile);
				}
			}
			break;
		}
		
		
		board.setBoardsTiles(list);
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
		user.setId(69);
		user.setUserName("fernando");
		user.setPassword("fernando");
		return user;
	}

	@Override
	public List<Unit> getPlayerUnits(String playerId) {
		return null;
	}

	@Override
	public boolean moveUnit(int battleId, int unitId, int x, int y) {
		// TODO Auto-generated method stub
		return true;
	}

}
