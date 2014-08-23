package com.mygdx.game.data.json;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.HttpStatus;
import com.badlogic.gdx.Net.HttpResponseListener;
import com.mygdx.game.hex.Board;

public class JsonClient {
	
	private String baseURL = "http://localhost:9000";
	public static final String POST = "POST";
	
	private static JsonClient instance;
    private JsonClient() {
    }
    public static synchronized JsonClient getInstance() {
        if(instance == null) {
            instance = new JsonClient();
        }
        return instance;
    }
    
	public <T> void sendPost(Object requestObject, String uri, final ResponseCallback<T> callback, final Class<T> clazz) {
        sendRequest(requestObject, uri, POST, callback, clazz);
    }
	
	 private <T> void sendRequest(Object requestObject, String uri, String method, final ResponseCallback<T> callback, final Class<T> clazz) {

	        String requestJson = JsonUtil.getInstance().toJson(requestObject);

	        Net.HttpRequest request = new Net.HttpRequest(method);
	        final String url = getURL(uri);
	        request.setUrl(url);

	        request.setContent(requestJson);

	        request.setHeader("Content-Type", "application/json");
	        request.setHeader("Accept", "application/json");

	        Gdx.net.sendHttpRequest(request, new Net.HttpResponseListener() {
	            public void handleHttpResponse(Net.HttpResponse httpResponse) {
	                int statusCode = httpResponse.getStatus().getStatusCode();
	                if(statusCode != HttpStatus.SC_OK) {
	                	callback.onFail(new JsonClientException(url,"Received http status: " + statusCode));
	                    return;
	                }

	                String responseJson = httpResponse.getResultAsString();
	                try {
	                    T responseObject = JsonUtil.getInstance().fromJson(clazz, responseJson);
	                    callback.onResponse(responseObject);
	                }
	                catch(Exception exception) {
	                    callback.onFail(new JsonClientException(url, "Exception parsing response as JSON.", exception));
	                }
	            }

	            public void failed(Throwable t) {
	                callback.onFail(new JsonClientException(url, t));
	            }

				@Override
				public void cancelled() {
					// TODO Auto-generated method stub
					
				}
	        });
	        

	    }
	 
	    private String getURL(String uri) {
	        return baseURL + uri;
	    }

}
