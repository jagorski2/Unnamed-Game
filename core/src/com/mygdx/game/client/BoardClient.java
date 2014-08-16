package com.mygdx.game.client;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.game.client.json.JsonClient;
import com.mygdx.game.client.json.JsonUtil;
import com.mygdx.game.client.json.ResponseCallback;
import com.mygdx.game.client.json.exceptions.JsonClientException;
import com.mygdx.game.client.json.models.Tile;
import com.mygdx.game.hex.Board;

public class BoardClient {
	
	private static final String uri = "/tiles";
	
	public void getBoardFromServer(Board board) {
		
	   JsonClient.getInstance().mySendRequest(board, "/tiles");
	}
}
