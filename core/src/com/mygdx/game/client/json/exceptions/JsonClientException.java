package com.mygdx.game.client.json.exceptions;

public class JsonClientException extends Exception {

    private Throwable originalException;

    public JsonClientException(String url, String msg) {
        super("Exception when requesting URL " + url + "\nMessage: " + msg);
    }

    public JsonClientException(String url, Throwable originalException) {
        super("Exception when requesting URL " + url + "\nOriginal message: " + originalException.getMessage());
        this.originalException = originalException;
    }

    public JsonClientException(String url, String msg, Throwable originalException) {
        super("Exception when requesting URL " + url + "\nMessage: " + msg + "\nOriginal message: " + originalException.getMessage());
        this.originalException = originalException;
    }

    public Throwable getOriginalException() {
        return originalException;
    }

}
