package com.mygdx.game.data.client;

import java.util.LinkedList;
import java.util.List;

import com.app.models.Tile;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.game.data.json.JsonClient;
import com.mygdx.game.data.json.JsonClientException;
import com.mygdx.game.data.json.JsonUtil;
import com.mygdx.game.data.json.ResponseCallback;
import com.mygdx.game.hex.Board;

public class BoardClient implements Runnable {
	
	private static final String uri = "/tiles";
	private final Board board;
	public BoardClient(Board board) {
		this.board = board;
	}

	@Override
	public void run() {
		ResponseCallback callback = new ResponseCallback() {
			@Override
			public void onResponse(Object returnObject) {
				List<JsonValue> list = (List)returnObject;
				List<Tile> tiles = new LinkedList<Tile>();
				JsonUtil util = JsonUtil.getInstance();
				
				
				/**
				 * get each JsonValue and convert to a Tile
				 */
				for (JsonValue value : list) {
					//value = (JsonValue)value;
					Tile bt = util.fromJson(Tile.class, value.toString());
					tiles.add(bt);
					
				}
				
				/*
				 * Response is complete, initialize the board.
				 */
				board.setTileBeans(tiles);
				board.initialize();
			}

			@Override
			public void onFail(JsonClientException t) {
				t.printStackTrace();
			}
			
		};
		JsonClient.getInstance().sendPost(1, uri, callback, List.class);
	}
}
