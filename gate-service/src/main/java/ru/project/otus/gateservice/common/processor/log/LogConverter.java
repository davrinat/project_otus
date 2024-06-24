package ru.project.otus.gateservice.common.processor.log;

import com.google.gson.Gson;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import ru.project.otus.gateservice.common.constant.Constant;
import ru.project.otus.gateservice.common.util.MessageUtils;
import ru.project.otus.gateservice.log.LogRecord;

import java.io.InputStream;
import java.util.Date;
import java.util.Objects;

public class LogConverter implements Processor {
    @Override
    public void process(Exchange exchange) {
        LogRecord record = new LogRecord();
        Gson gson = new Gson();

        record.setSystem(Constant.PROJECT_NAME);
        record.setTraceId(exchange.getProperty(Constant.TRACE_ID, String.class));
        record. setSpanId(exchange.getProperty(Constant.SPAN_ID, String.class));
        record.setTimestamp(new Date());
        record.setComponent(exchange.getProperty(Constant.LOG_COMPONENT, String.class));
        record.setOperation(exchange.getProperty(Constant.LOG_OPERATION, String.class));
        record.setDirection(exchange.getProperty(Constant.LOG_DIRECTION, String.class));
        record.setStep(exchange.getProperty(Constant.LOG_STEP, String.class));

        Object body = Objects.isNull(exchange.getMessage().getBody())
                ? exchange.getIn().getBody()
                : exchange.getMessage().getBody();

        if (body instanceof InputStream) {
            record.setData(MessageUtils.getStringFromInputStream((InputStream) body));
        } else if (body instanceof String) {
            record.setData(body);
        } else {
            record.setData(gson.toJson(body));
        }

        exchange.getMessage().setBody(gson.toJson(record));
        exchange.setProperty("record", record);
    }
}
