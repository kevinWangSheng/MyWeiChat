package com.kevin.chat.utils;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtobufIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wang
 * @create 2023-12-18-20:10
 */
public class SerializationUtil {
    private static Map<Class<?>, Schema<?>> cachedSchema = new ConcurrentHashMap<>();

    private static Objenesis objenesis = new ObjenesisStd();

    public static <T> byte[] serialize(T obj){
        Class<T> cls = (Class<T>) obj.getClass();

        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);

        try {
            Schema<T> schema = getSchema(cls);
            return ProtobufIOUtil.toByteArray(obj,schema,buffer);
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }finally{
            buffer.clear();
        }
    }

    public static <T> T deserialize(byte[] data, Class<T> cls){
        try {
            T message = objenesis.newInstance(cls);
            Schema<T> schema = getSchema(cls);
            ProtobufIOUtil.mergeFrom(data,message,schema);
            return message;
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    private static <T> Schema<T> getSchema(Class<T> cls) {
        Schema<T> schema = (Schema<T>)cachedSchema.get(cls);
        if(null == schema){
            schema = RuntimeSchema.getSchema(cls);
            cachedSchema.put(cls,schema);
        }
        return schema;
    }
}
