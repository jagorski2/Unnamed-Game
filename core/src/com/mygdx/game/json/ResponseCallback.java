package com.mygdx.game.json;

import com.mygdx.game.json.exceptions.JsonClientException;


public interface ResponseCallback<T> {

    void onResponse(T returnObject);
    void onFail(JsonClientException t);

}

