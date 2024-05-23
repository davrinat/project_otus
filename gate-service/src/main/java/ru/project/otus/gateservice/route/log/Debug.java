package ru.project.otus.gateservice.route.log;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
import ru.project.otus.gateservice.common.constant.Constant;

@Component
public class Debug extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        errorHandler(noErrorHandler());

        from("direct:responseBackLogger")
                .routeId("DebugResponseRouteId")
                .convertBodyTo(String.class)
                .log(LoggingLevel.INFO, "response external service :: gate "
                        + "HTTPCode :: ${header." + Exchange.HTTP_RESPONSE_CODE + "}"
                        + " :: CorrelationId :: ${exchangeProperty" + Constant.SPAN_ID + "}\r\n"
                        + "Header :: ${headers}\r\n"
                        + "Body :: ${body}"
                ).end();

        from("direct:requestBackLogger")
                .routeId("DebugRequestBackLogger")
                .convertBodyTo(String.class)
                .log(LoggingLevel.INFO, "request external service :: gate"
                        + " :: CorrelationId :: ${exchangeProperty" + Constant.SPAN_ID + "}\r\n"
                        + "Header :: ${headers}\r\n"
                        + "Body :: ${body}"
                ).end();
    }
}
