package com.mygdx.game.data.client;

import java.util.LinkedList;
import java.util.List;

import com.app.models.Instance;
import com.app.models.Tile;
import com.app.models.User;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.game.data.json.JsonClient;
import com.mygdx.game.data.json.JsonClientException;
import com.mygdx.game.data.json.JsonUtil;
import com.mygdx.game.data.json.ResponseCallback;

public class InstancesClient implements Runnable {

	private final String uri ="/getInstances";
	private final List<Instance> list;
	private User user;
	
	/**
	 * 
	 * @param list the list reference that elements will be added to by the callback
	 * @param user the request parameter for the DB transaction
	 */
	public InstancesClient(List<Instance> list, User user) {
		this.user = user;
		this.list = list;
	}
	@Override
	public void run() {
		int id = user.getUserId();
		ResponseCallback callback = new ResponseCallback(){

			@Override
			public void onResponse(Object returnObject) {
				List<JsonValue> jsons = (List)returnObject;
				JsonUtil util = JsonUtil.getInstance();
				/**
				 * get each JsonValue and convert to an Instance
				 */
				for (JsonValue value : jsons) {
					Instance instance = util.fromJson(Instance.class, value.toString());
					list.add(instance);
				}
			}

			@Override
			public void onFail(JsonClientException t) {
				t.printStackTrace();	
			}
			
		};
		JsonClient.getInstance().sendPost(id, uri, callback, List.class);
	}

}
