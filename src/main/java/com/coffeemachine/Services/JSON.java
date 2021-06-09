package com.coffeemachine.Services;

import com.google.gson.Gson;

public class JSON {
    public static Gson gson = new Gson();

    public static String toJSON(Object object){
        return gson.toJson(object);
    }

    public static <T> T fromJSON(String object, Class<T> t){
        return gson.fromJson(object, t);
    }
}
