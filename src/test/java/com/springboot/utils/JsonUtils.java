package com.springboot.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T jsonToDto(String json, Class<T> clazz) throws Exception {
        return objectMapper.readValue(json, clazz);
    }

    public static String dtoToJson(Object object) throws Exception {
        return objectMapper.writeValueAsString(object);
    }
}
