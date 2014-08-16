package com.mygdx.game.client.json;

import com.mygdx.game.client.json.exceptions.JsonClientException;


public interface ResponseCallback<T> {

    void onResponse(T returnObject);
    void onFail(JsonClientException t);

}

