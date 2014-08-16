package com.mygdx.game.client.json;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Net.HttpResponse;
import com.badlogic.gdx.Net.HttpResponseListener;
import com.badlogic.gdx.net.HttpStatus;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonValue.JsonIterator;
import com.mygdx.game.client.json.models.Tile;
import com.mygdx.game.hex.Board;

public class BoardResponseListener implements HttpResponseListener{
	
	Board board;
	List<Tile> tiles;
	
	public BoardResponseListener(Board board) {
		this.board = board;
		tiles = new ArrayList<Tile>();
	}

	@Override
	public void handleHttpResponse(HttpResponse httpResponse) {
		 int statusCode = httpResponse.getStatus().getStatusCode();
         if((statusCode != HttpStatus.SC_OK)) {
         	
         } 
         JsonReader jRead = new JsonReader();
         JsonValue jVal = jRead.parse(httpResponse.getResultAsString());
         JsonIterator jitr = jVal.iterator();
         while (jitr.hasNext()) {
        	 JsonValue val = jitr.next();
        	 Tile tile = JsonUtil.getInstance().fromJson(Tile.class, val.toString());
        	 tiles.add(tile);
         }
         board.initialize(tiles);
	}

	@Override
	public void failed(Throwable t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancelled() {
		// TODO Auto-generated method stub
		
	}
	
	private void initializeBoard() {
		
	}

}
