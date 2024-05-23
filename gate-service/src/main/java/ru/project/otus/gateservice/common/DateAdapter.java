package ru.project.otus.gateservice.common;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import ru.project.otus.gateservice.common.constant.Constant;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateAdapter extends TypeAdapter<Object> {
    private final SimpleDateFormat format = new SimpleDateFormat();

    @Override
    public void write(JsonWriter jsonWriter, Object o) throws IOException {
        format.applyPattern(Constant.DATE_FORMAT);
        jsonWriter.value(format.format(o));
    }

    @Override
    public Object read(JsonReader jsonReader) throws IOException {
        format.applyPattern(Constant.DATE_FORMAT);
        String value = jsonReader.nextString();

        try {
            return format.parse(value);
        } catch (ParseException ex) {
            return null;
        }
    }
}
