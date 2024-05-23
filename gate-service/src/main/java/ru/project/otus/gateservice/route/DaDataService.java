package ru.project.otus.gateservice.route;

import org.apache.camel.Predicate;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class DaDataService extends RouteBuilder {
    private final Predicate isLog = simple("{{log.enable}}").isEqualToIgnoreCase("true");

    @Override
    public void configure() {
        errorHandler(noErrorHandler());

        from("direct:daDataServiceRoute")
                .routeId("DaDataServiceRouteId")

                .filter(isLog)
                    .wireTap("direct:requestBackLogger").copy(true).end()
                .end()

                .toD("{{schema}}://{{host}}:{{da_data.port}}${header.CamelHttpUri}"
                        + "?bridgeEndpoint=true"
                        + "&httpClientConfigurer=#selfSignedHttpClientConfig"
                        + "&throwExceptionOnFailure=false"
                        + "&connectionPerRoute={{connection.pool.size}}"
                        + "&httpClient.connectTimeout={{connection.timeout}}"
                        + "&httpClient.socketTimeout={{socket.timeout}}"
                )

                .filter(isLog)
                    .wireTap("direct:responseBackLogger").copy(true).end()
                .end()

                .wireTap("direct:databaseServiceRoute").copy(true).end()
        .end();
    }
}
