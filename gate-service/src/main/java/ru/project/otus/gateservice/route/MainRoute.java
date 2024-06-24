package ru.project.otus.gateservice.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
import ru.project.otus.gateservice.common.constant.Constant;

@Component
public class MainRoute extends RouteBuilder {

    @Override
    public void configure() {
        errorHandler(noErrorHandler());

        from("direct:mainRoute")
                .routeId("MainRouteId")
                .log("into main route ${header_" + Constant.CAMEL_CONTEXT_PATH + "}")

                .to("direct:daDataServiceRoute")

        .end();
    }
}
