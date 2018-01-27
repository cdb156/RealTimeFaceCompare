package com.hzgc.collect.expand.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.hzgc.collect.expand.log.LogEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonHelper {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static String toJson(Object o) {
        String string = null;
        try {
            string = mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return string;
    }

    public static <T> T toObject(String jsonData, Class<T> beanType) {
        T t = null;
        try {
            // TODO: 2018-1-27 此处报错
            t = mapper.readValue(jsonData, beanType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return t;
    }

    public static <T> List<T> toArray(String jsonData, Class<T> beanType) {
        JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, beanType);
        List<T> list = new ArrayList<>();
        try {
            list = mapper.readValue(jsonData, javaType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
