package ru.project.otus.gateservice.common.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import ru.project.otus.gateservice.common.constant.Constant;

import java.util.Objects;

public class ConvertPath implements Processor {

    @Override
    public void process(Exchange exchange) {
        var header = exchange.getIn().getHeader(Constant.CAMEL_HTTP_METHOD);

        if (Objects.nonNull(header) && header.equals("GET")) {

            exchange.getIn().setHeader(Constant.CAMEL_HTTP_METHOD, "POST");
        }
    }
}
