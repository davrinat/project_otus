package ru.project.otus.gateservice.common.util;

import org.apache.camel.Exchange;

import java.util.Objects;

public class ExceptionUtils {

    public static Exception exceptionExtractor(Exchange exchange) {
        Exception ex = null;
        if (Objects.nonNull(exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class))) {
            ex = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
        } else if (Objects.nonNull(exchange.getIn().getBody(Exception.class))) {
            ex = exchange.getIn().getBody(Exception.class);
        } else if (Objects.nonNull(exchange.getException())) {
            ex = exchange.getException();
        } else {
            String str = exchange.getIn().getHeader(Exchange.EXCEPTION_CAUGHT, String.class);
            if (Objects.nonNull(str)) { ex = new Exception(str); }
        }
        return ex;
    }
}
