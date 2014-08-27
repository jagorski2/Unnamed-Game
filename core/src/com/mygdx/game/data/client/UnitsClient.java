package com.mygdx.game.data.client;

import java.util.LinkedList;
import java.util.List;

import com.app.models.Instance;
import com.app.models.Tile;
import com.app.models.Unit;
import com.app.models.User;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.game.data.json.JsonClient;
import com.mygdx.game.data.json.JsonClientException;
import com.mygdx.game.data.json.JsonUtil;
import com.mygdx.game.data.json.ResponseCallback;

public class UnitsClient implements Runnable {

	private final String uri ="/getUnits";
	private final List<Unit> list;
	private User user;
	private Instance instance;
	
	/**
	 * 
	 * @param list the list reference that elements will be added to by the callback
	 * @param user the request parameter for the DB transaction
	 */
	public UnitsClient(List<Unit> list, Instance instance, User user) {
		this.user = user;
		this.list = list;
		this.instance = instance;
	}
	@Override
	public void run() {
		
		/**
		 * first ID sent in is the user,
		 * second ID sent in is the instanceID
		 */
		int id = user.getUserId();
		int id2 = instance.getInstanceId();
		List<Integer> reqList = new LinkedList<Integer>();
		reqList.add(id);
		reqList.add(id2);
		
		
		ResponseCallback callback = new ResponseCallback(){

			@Override
			public void onResponse(Object returnObject) {
				List<JsonValue> jsons = (List)returnObject;
				JsonUtil util = JsonUtil.getInstance();
				/**
				 * get each JsonValue and convert to an Instance
				 */
				for (JsonValue value : jsons) {
					Unit unit = util.fromJson(Unit.class, value.toString());
					list.add(unit);
				}
			}

			@Override
			public void onFail(JsonClientException t) {
				t.printStackTrace();	
			}
			
		};
		JsonClient.getInstance().sendPost(reqList, uri, callback, List.class);
	}

}
