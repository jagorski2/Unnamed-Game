package com.mygdx.game.json;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;

public class JsonUtil {

    private final Json json;

    //Singleton pattern ------
    private static JsonUtil instance;
    private JsonUtil() {
        json = new Json(JsonWriter.OutputType.json);
    }
    public static synchronized JsonUtil getInstance() {
        if(instance == null) {
            instance = new JsonUtil();
        }
        return instance;
    }
    //------------------------

    public <T> T readFromJsonFile(Class<T> clazz, String filename) {
        FileHandle file = Gdx.files.local(filename);
        return json.fromJson(clazz, file);
    }

    public void writeToJsonFile(Object obj, String filename) {
        FileHandle file = Gdx.files.local(filename);
        json.toJson(obj, file);
    }

    public String toJson(Object obj) {
        return json.toJson(obj);
    }

    public <T> T fromJson(Class<T> clazz, String jsonString) {
        return json.fromJson(clazz, jsonString);
    }
}