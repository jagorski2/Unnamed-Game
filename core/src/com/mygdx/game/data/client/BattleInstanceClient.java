package com.mygdx.game.data.client;

import java.util.List;

import com.app.models.Instance;
import com.mygdx.game.BattleInstance;
import com.mygdx.game.data.json.JsonClient;
import com.mygdx.game.data.json.JsonClientException;
import com.mygdx.game.data.json.ResponseCallback;

public class BattleInstanceClient implements Runnable {

	private int id;
	private final Instance instance;
	private static final String uri = "/instance";
	
	public BattleInstanceClient(int id, Instance instance) {
		this.id = id;
		this.instance = instance;
	}
	
	@Override
	public void run() {
		
		ResponseCallback callback = new ResponseCallback() {

			@Override
			public void onResponse(Object returnObject) {
				Instance inst = (Instance)returnObject;
				instance.setBoardId(inst.getBoardId());
				instance.setInstanceId(inst.getInstanceId());
				instance.setMissionId(inst.getMissionId());
				instance.setTurnId(inst.getTurnId());
			}

			@Override
			public void onFail(JsonClientException t) {
				t.printStackTrace();	
			}
			
		};
		JsonClient.getInstance().sendPost(id, uri, callback, Instance.class);
	}

}
