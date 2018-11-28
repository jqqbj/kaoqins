package com.rrx.kaoqins.core.util;

import org.springframework.beans.BeanUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 对象工具
 */
public class ObjectUtil {

    public static  <T> T deepClone(T source) throws Exception {
        if(source == null){
            return null;
        }
        //将对象写到流里
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream oo = new ObjectOutputStream(bo);
        oo.writeObject(source);
        //从流里读出来
        ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
        ObjectInputStream oi = new ObjectInputStream(bi);
        return (T) oi.readObject();
    }

    public static void copyProperties(Object source, Object target){
        if (source == null){
            return;
        }
        if (target == null){
            return;
        }
        BeanUtils.copyProperties(source,target);
    }
}
