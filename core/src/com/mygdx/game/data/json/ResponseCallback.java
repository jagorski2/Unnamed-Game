package com.mygdx.game.data.json;



public interface ResponseCallback<T> {

    void onResponse(T returnObject);
    void onFail(JsonClientException t);

}

