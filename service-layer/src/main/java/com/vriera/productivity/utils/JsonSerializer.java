package com.vriera.productivity.utils;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class JsonSerializer {

    public String serialize(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }

}
