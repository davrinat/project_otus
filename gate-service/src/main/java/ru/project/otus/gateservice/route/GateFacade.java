package ru.project.otus.gateservice.route;

import org.apache.camel.Exchange;
import org.apache.camel.Predicate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spi.HeaderFilterStrategy;
import org.springframework.stereotype.Component;
import ru.project.otus.gateservice.common.constant.Constant;
import ru.project.otus.gateservice.common.processor.log.LogIdGenerator;
import ru.project.otus.gateservice.common.processor.log.RestFaultResponseBuilder;

@Component
public class GateFacade extends RouteBuilder {
    private final Predicate isLog = simple("{{log.enable}}").isEqualToIgnoreCase("true");

    @Override
    public void configure() {
        errorHandler(defaultErrorHandler());

        onException(Exception.class)
                .handled(true)
                .log("into error handler")
                .process(new RestFaultResponseBuilder())
                .setProperty(Constant.LOG_LEVEL, constant("ERROR"))
                .setProperty(Constant.LOG_STEP, constant("Exception"))
                .wireTap("direct:loggingRoute").copy(true).end()
                .end();

        from("netty-http:http://{{base.gate.host}}:{{base.gate.port}}"
                + "?matchOnUriPrefix=true"
                + "&sendServiceVersion=false"
                + "&chunkedMaxContentLength=512000000")

                .process(new LogIdGenerator())

                .filter(isLog)
                    .log("inbound_log")
                    .setProperty(Constant.LOG_LEVEL, constant("INFO"))
                    .setProperty(Constant.LOG_COMPONENT, constant("FACADE"))
                    .setProperty(Constant.LOG_OPERATION, header(Exchange.HTTP_PATH))
                    .setProperty(Constant.LOG_DIRECTION, constant(HeaderFilterStrategy.Direction.IN))
                    .setProperty(Constant.LOG_STEP, constant("REQUEST"))
                    .wireTap("direct:loggingRoute").copy(true).end()
                .end()

                .to("direct:mainRoute")

                .filter(isLog)
                   .log("outbound_log")
                   .setProperty(Constant.LOG_LEVEL, constant("INFO"))
                   .setProperty(Constant.LOG_COMPONENT, constant("FACADE"))
                   .setProperty(Constant.LOG_OPERATION, header(Exchange.HTTP_PATH))
                   .setProperty(Constant.LOG_DIRECTION, constant(HeaderFilterStrategy.Direction.OUT))
                   .setProperty(Constant.LOG_STEP, constant("RESPONSE"))
                   .wireTap("direct:loggingRoute").copy(true).end()
                .end();
    }
}
