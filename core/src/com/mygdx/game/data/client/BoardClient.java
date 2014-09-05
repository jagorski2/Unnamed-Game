package com.mygdx.game.data.client;

import java.util.LinkedList;
import java.util.List;

import com.app.models.Instance;
import com.app.models.Tile;
import com.app.models.Unit;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.game.BattleInstance;
import com.mygdx.game.InstanceUnit;
import com.mygdx.game.MyGame;
import com.mygdx.game.data.json.JsonClient;
import com.mygdx.game.data.json.JsonClientException;
import com.mygdx.game.data.json.JsonUtil;
import com.mygdx.game.data.json.ResponseCallback;
import com.mygdx.game.hex.Board;

public class BoardClient implements Runnable {
	
	private static final String uri = "/tiles";
	private final MyGame game;
	private final Instance instance;
	/**
	 * 
	 * @param board this instance of board will get data and initialize
	 * @param id the board id that the database should select on
	 */
	public BoardClient(MyGame game,Instance instance) {
		this.game = game;
		this.instance = instance;
	}

	@Override
	public void run() {
		ResponseCallback callback = new ResponseCallback() {
			@Override
			public void onResponse(Object returnObject) {
				
				List<JsonValue> list = (List)returnObject;
				List<Tile> tiles = new LinkedList<Tile>();
				List<Unit> units = new LinkedList<Unit>();
				JsonUtil util = JsonUtil.getInstance();
				
				/**
				 * get each JsonValue and convert to a Tile or Unit
				 */
				for (JsonValue value : list) {
					//value = (JsonValue)value;]
					if ("unitId".equals(value.child().name()))	{
						Unit unit = util.fromJson(Unit.class, value.toString());
						units.add(unit);
					} else if ("boardId".equals(value.child().name())) {
						Tile bt = util.fromJson(Tile.class, value.toString());
						tiles.add(bt);
					}
				}
				
				Board board = new Board(30,30,30);
				board.setTileBeans(tiles);
				board.initialize();
				
				

				/*
				 * create an InstanceUnit object for each Unit object
				 */
				List<InstanceUnit> instanceUnits = new LinkedList<InstanceUnit>();
				for (Unit unit : units) {
					InstanceUnit instanceUnit = new InstanceUnit(unit);
					instanceUnits.add(instanceUnit);
				}
				BattleInstance battle = new BattleInstance(board,instance,instanceUnits);
				/*
				 * set the board screen's BattleInstance to the just populated object
				 */
				game.board.setBattle(battle);
				System.out.println("ready");
				
				/*
				 * tell the game to switch its screen to the BoardScreen screen
				 */
				game.setScreen(game.board);
			}

			@Override
			public void onFail(JsonClientException t) {
				t.printStackTrace();
			}
			
		};
		
		/*
		 * send the request after we declare the callback anonymous class
		 */
		JsonClient.getInstance().sendPost(instance.getInstanceId(), uri, callback, List.class);
	}
}
