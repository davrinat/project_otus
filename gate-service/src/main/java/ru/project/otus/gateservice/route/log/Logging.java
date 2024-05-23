package ru.project.otus.gateservice.route.log;

import org.apache.camel.ExchangePattern;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Predicate;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
import ru.project.otus.gateservice.common.constant.Constant;
import ru.project.otus.gateservice.common.processor.log.LogConverter;

@Component
public class Logging extends RouteBuilder {
    private final Predicate isTrace = exchangeProperty(Constant.LOG_LEVEL).isEqualToIgnoreCase("TRACE");
    private final Predicate isDebug = exchangeProperty(Constant.LOG_LEVEL).isEqualToIgnoreCase("DEBUG");
    private final Predicate isInfo = exchangeProperty(Constant.LOG_LEVEL).isEqualToIgnoreCase("INFO");
    private final Predicate isWarn = exchangeProperty(Constant.LOG_LEVEL).isEqualToIgnoreCase("WARN");
    private final Predicate isError= exchangeProperty(Constant.LOG_LEVEL).isEqualToIgnoreCase("ERROR");
    private final Predicate isOff = exchangeProperty(Constant.LOG_LEVEL).isEqualToIgnoreCase("OFF");

    @Override
    public void configure() {
        errorHandler(noErrorHandler());

        from("direct:loggingRoute")
                .routeId("LoggingRouteId")
                .setExchangePattern(ExchangePattern.InOnly)

                .process(new LogConverter())
                .choice()
                    .when(isTrace)
                        .log(LoggingLevel.TRACE, Constant.PROJECT_NAME, "${body}")
                    .when(isDebug)
                        .log(LoggingLevel.DEBUG, Constant.PROJECT_NAME, "${body}")
                    .when(isInfo)
                        .log(LoggingLevel.INFO, Constant.PROJECT_NAME, "${body}")
                    .when(isWarn)
                        .log(LoggingLevel.WARN, Constant.PROJECT_NAME, "${body}")
                    .when(isError)
                        .log(LoggingLevel.ERROR, Constant.PROJECT_NAME, "${body}")
                    .when(isOff)
                        .log(LoggingLevel.OFF, Constant.PROJECT_NAME, "${body}")
                    .otherwise()
                        .log("Unknown log level: ${exchangeProperty(" + Constant.LOG_LEVEL + ")}")
                        .log(LoggingLevel.OFF, Constant.PROJECT_NAME, "${body}")
                .end()
        .end();

    }
}
