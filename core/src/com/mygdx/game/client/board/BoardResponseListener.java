package com.mygdx.game.client.board;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.Net.HttpResponse;
import com.badlogic.gdx.Net.HttpResponseListener;
import com.badlogic.gdx.net.HttpStatus;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonValue.JsonIterator;
import com.mygdx.game.client.json.JsonUtil;
import com.mygdx.game.client.json.models.BoardsTile;
import com.mygdx.game.hex.Board;

public class BoardResponseListener implements HttpResponseListener{
	
	Board board;			//this should ALWAYS be a reference to the main game board
	
	public BoardResponseListener(Board board) {
		this.board = board;
	}

	@Override
	public void handleHttpResponse(HttpResponse httpResponse) {
		 int statusCode = httpResponse.getStatus().getStatusCode();
         if((statusCode != HttpStatus.SC_OK)) {
         	
         } 
         JsonReader jRead = new JsonReader();
         JsonValue jVal = jRead.parse(httpResponse.getResultAsString());
         JsonIterator jitr = jVal.iterator();
         
         List<BoardsTile> tiles = new LinkedList<BoardsTile>();	//this will be passed to board at the end of this responseListener
         
         //this grabs each Tile element from the  json string
         while (jitr.hasNext()) {
        	 JsonValue val = jitr.next();
        	 BoardsTile tile = JsonUtil.getInstance().fromJson(BoardsTile.class, val.toString());
        	 tiles.add(tile);
         }
         board.setBoardsTiles(tiles);
         
         /*
          * board.initalizeWithTiles()
          * 
          * This method call does not work because it gets interrupted and stopped in the middle of its loop (from my experience)
          * my theory is because the library only gives this response listener X seconds or clock cycles to complete or some bullshit like that
          * 
          * if you can think of a better reason please let me know, i'm very curious
          * 
          * but anyway, this is why I made a new thread in the game loop and had it initialize the board once we have the response tiles
          * 
          */
	}

	@Override
	public void failed(Throwable t) {
		t.printStackTrace();
		// TODO Auto-generated method stub
	}

	@Override
	public void cancelled() {
		// TODO Auto-generated method stub
		
	}
}
