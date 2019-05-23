package com.example.dervis.autonomous.Helpers;

import com.example.dervis.autonomous.Objects.ListObjIcon;
import com.google.gson.Gson;

public class GsonConv {

    public static String toJson(ListObjIcon obj){
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        return json;
    }

    public static ListObjIcon toObject(String jsonObj){
        Gson gson = new Gson();
         return gson.fromJson(jsonObj, ListObjIcon.class);
    }
}
