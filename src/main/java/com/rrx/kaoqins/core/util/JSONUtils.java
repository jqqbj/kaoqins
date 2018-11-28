package com.rrx.kaoqins.core.util;

/**
 * @Author JQQ
 */
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

public class JSONUtils {

    private final static ObjectMapper objectMapper;

    private JSONUtils() {
    }

    static {
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        //-- 配置Jackson返回日期类型格式（默认是日期的毫秒数）
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        objectMapper.setDateFormat(format);
    }

    /**
     * Description:javaBean,list,array convert to json string
     */
    public static String obj2json(Object obj) throws Exception {
        return objectMapper.writeValueAsString(obj);
    }

    /**
     * 不抛异常
     * by 周忠友
     * @param obj
     * @return
     */
    public static final String obj2Json(Object obj){
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException var2) {
            var2.printStackTrace();
            return "";
        }
    }

    public static final  <T> T json2Bean(String jsonStr, Class<T> clazz){
        try {
            return objectMapper.readValue(jsonStr, clazz);
        } catch (IOException var3) {
            var3.printStackTrace();
            return null;
        }
    }

    public static final <T> T json2Bean(String json, TypeReference<T> valueTypeRef) {
        try {
            return objectMapper.readValue(json, valueTypeRef);
        } catch (IOException var3) {
            var3.printStackTrace();
            return null;
        }
    }

    public static final <T> T json2Bean(String json, JavaType javaType) {
        try {
            return objectMapper.readValue(json, javaType);
        } catch (IOException var3) {
            var3.printStackTrace();
            return null;
        }
    }

    /**
     * Description:json string convert to javaBean
     */
    public static <T> T json2pojo(String jsonStr, Class<T> clazz) throws Exception {
        return objectMapper.readValue(jsonStr, clazz);
    }

    /**
     * Description:json string convert to javaBean
     */
    public static <T> T json2pojo(String jsonStr, TypeReference<T> typeReference) throws Exception {
        return objectMapper.readValue(jsonStr, typeReference);
    }

    public static <T> T json2pojo(String jsonStr, JavaType javaType) throws Exception {
        return objectMapper.readValue(jsonStr, javaType);
    }

    /**
     * Description: json string convert to map
     */
    @SuppressWarnings("unchecked")
    public static <T> Map<String, Object> json2map(String jsonStr) throws Exception {
        return objectMapper.readValue(jsonStr, Map.class);
    }

    /**
     * Description:json string convert to map with javaBean
     */
    public static <T> Map<String, T> json2map(String jsonStr, Class<T> clazz) throws Exception {
        JavaType javaType = constructParametricType(Map.class, String.class, clazz);
        Map<String, T> map = objectMapper.readValue(jsonStr, javaType);
        return map;
    }

    /**
     * Description:json array string convert to list with javaBean
     */
    public static <T> List<T> json2list(String jsonArrayStr, Class<T> clazz) throws Exception {
        if(jsonArrayStr == null || "".equals(jsonArrayStr)){
            return null;
        }

        JavaType javaType = constructParametricType(List.class, clazz);
        List<T> list = objectMapper.readValue(jsonArrayStr,javaType);
        return list;
    }

    /**
     * Description:map convert to javaBean
     */
    public static <T> T map2pojo(@SuppressWarnings("rawtypes") Map map, Class<T> clazz) {
        return objectMapper.convertValue(map, clazz);
    }

    public static JavaType constructParametricType(Class<?> parametrized, Class<?>... parameterClasses) {
        return objectMapper.getTypeFactory().constructParametricType(parametrized, parameterClasses);
    }

    public static JavaType constructParametricType(Class<?> parametrized, JavaType... parameterTypes) {
        return objectMapper.getTypeFactory().constructParametricType(parametrized, parameterTypes);
    }

    public static <T> T convertValue(Object fromValue, JavaType javaType) {
        return objectMapper.convertValue(fromValue, javaType);
    }
}
