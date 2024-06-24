package ru.project.otus.gateservice.common.processor.log;

import com.google.gson.Gson;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.http.entity.ContentType;
import ru.project.otus.gateservice.common.util.ExceptionUtils;
import ru.project.otus.gateservice.log.Error;
import ru.project.otus.gateservice.log.ErrorDetail;

import java.util.Objects;

public class RestFaultResponseBuilder implements Processor {

    @Override
    public void process(Exchange exchange) {
        int status = 400;

        Exception ex = ExceptionUtils.exceptionExtractor(exchange);
        Error error;
        if (Objects.nonNull(ex)) {
            error = new Error();
            error.setCode(status);
            error.setMessage(ex.getMessage());
        } else {
            error = new Error(ErrorDetail.UNKNOWN_ERROR, "");
        }

        exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, status);
        exchange.getIn().setHeader(Exchange.CONTENT_TYPE, ContentType.APPLICATION_JSON);
        exchange.getIn().setBody(new Gson().toJson(error));
    }
}
