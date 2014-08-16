package com.mygdx.game.client.json;

import com.badlogic.gdx.Net;
import com.badlogic.gdx.Net.HttpResponseListener;
import com.badlogic.gdx.net.HttpStatus;

public class JsonHttpResponseListener implements HttpResponseListener {
 		
     	private StringWrapper wrapped;
 		
     	public JsonHttpResponseListener(StringWrapper wrapped) {
 			this.wrapped = wrapped;	
 		}
 		public void handleHttpResponse(Net.HttpResponse httpResponse) {
             int statusCode = httpResponse.getStatus().getStatusCode();
             if((statusCode != HttpStatus.SC_OK)) {
             	
             } 
             //System.err.println(httpResponse.getResultAsString());
             wrapped.setResponse(httpResponse.getResultAsString());
             System.err.println("response:" + wrapped.getResponse());
         }

         public void failed(Throwable t) {
          
         }

			@Override
		public void cancelled() {
				// TODO Auto-generated method stub	
		}
			public StringWrapper getWrapped() {
				return wrapped;
			}
			public void setWrapped(StringWrapper wrapped) {
				this.wrapped = wrapped;
			}
}
