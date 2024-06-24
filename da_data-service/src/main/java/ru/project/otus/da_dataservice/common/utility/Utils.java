package ru.project.otus.da_dataservice.common.utility;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Utils {

    public String toArray(String search) {
        StringBuilder sb = new StringBuilder()
                .append("[\" ")
                .append(search)
                .append(" \"]");

        return sb.toString();
    }

    public String toJson(String search) {
        JsonObject result = new JsonObject();
        result.add("query", new JsonPrimitive(search));

        return String.valueOf(result);
    }
}
