package ru.project.otus.gateservice.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
import ru.project.otus.gateservice.common.constant.Constant;
import ru.project.otus.gateservice.common.processor.ConvertPath;

@Component
public class DatabaseService extends RouteBuilder {

    @Override
    public void configure() {
        errorHandler(noErrorHandler());

        from("direct:databaseServiceRoute")
                .routeId("databaseServiceRouteId")
                .convertBodyTo(String.class)
                .process(new ConvertPath())

                .toD("{{schema}}://{{host}}:{{db.port}}" + Constant.DB_URL
                        + "?bridgeEndpoint=true"
                        + "&httpClientConfigurer=#selfSignedHttpClientConfig"
                        + "&throwExceptionOnFailure=false"
                        + "&connectionPerRoute={{connection.pool.size}}"
                        + "&httpClient.connectTimeout={{connection.timeout}}"
                        + "&httpClient.socketTimeout={{socket.timeout}}")

        .end();
    }
}
