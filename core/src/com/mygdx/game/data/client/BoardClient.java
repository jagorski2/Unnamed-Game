package com.mygdx.game.data.client;

import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonValue.JsonIterator;
import com.mygdx.game.data.json.BoardsTile;
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
				List<BoardsTile> tiles = new LinkedList<BoardsTile>();
				JsonUtil util = JsonUtil.getInstance();
				for (JsonValue value : list) {
					//value = (JsonValue)value;
					BoardsTile bt = util.fromJson(BoardsTile.class, value.toString());
					tiles.add(bt);
					
				}
				board.setBoardsTiles(tiles);
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
