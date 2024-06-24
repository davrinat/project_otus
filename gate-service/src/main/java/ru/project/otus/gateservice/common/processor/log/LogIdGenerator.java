package ru.project.otus.gateservice.common.processor.log;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import ru.project.otus.gateservice.common.constant.Constant;

import java.util.Objects;
import java.util.UUID;

public class LogIdGenerator implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        var id = exchange.getIn().getHeader(Constant.TRACE_ID, String.class);
        var spanId = exchange.getProperty(Constant.SPAN_ID, String.class);

        if (Objects.isNull(id) || id.isEmpty()) {
            id = UUID.randomUUID().toString();

            exchange.setProperty(Constant.TRACE_ID, id);
            exchange.setProperty(Constant.SPAN_ID, id);
            exchange.getIn().setHeader(Constant.TRACE_ID, id);
        } else if (Objects.isNull(spanId) || spanId.isEmpty()) {
            exchange.setProperty(Constant.TRACE_ID, id);
            spanId = UUID.randomUUID().toString();
            exchange.setProperty(Constant.SPAN_ID, spanId);
            exchange.getIn().setHeader(Constant.SPAN_ID, spanId);
        }
    }
}
