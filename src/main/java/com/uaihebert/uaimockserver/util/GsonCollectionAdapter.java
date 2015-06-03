package com.uaihebert.uaimockserver.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.Collection;

public class GsonCollectionAdapter implements JsonSerializer<Collection<?>> {
    @Override
    public JsonElement serialize(Collection<?> source, Type typeOfSrc, JsonSerializationContext context) {
        if (source == null || source.isEmpty()){
            return null;
        }

        final JsonArray array = new JsonArray();

        for (Object child : source) {
            JsonElement element = context.serialize(child);
            array.add(element);
        }

        return array;
    }
}