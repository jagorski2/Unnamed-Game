package com.mygdx.game.data.client;

import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.game.data.json.JsonUtil;

public class ClientUtils<T> {
	private final Class<T> type;
	
	public ClientUtils(Class<T> type) {
		this.type = type;
	}
	public  List listFromJson(Class<T> type,Object object) {
		final List<T> retList = new LinkedList<T>();
		List<JsonValue> jsons = (List)object;
		final JsonUtil util = JsonUtil.getInstance();
		/**
		 * get each JsonValue and convert to an Instance
		 */
		for (JsonValue value : jsons) {
			T bean = (T) util.fromJson(type.getClass(), value.toString());
			retList.add(bean);
		}
		return retList;
	}
}
