package ru.project.otus.da_dataservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.project.otus.da_dataservice.exception.IncorrectRequestException;

@Component
@RequiredArgsConstructor
public class RemoteService {
    @Value("${da_data.source.apikey}")
    String apiKey;
    @Value("${da_data.source.secret}")
    String xSecret;
    private final WebClient.Builder webclientBuilder;

    public WebClient.ResponseSpec connect(String base, String path, String request) {
        try {
            return webclientBuilder
                    .baseUrl(base)
                    .build()
                    .post()
                    .uri(path)
                    .body(Mono.just(request), String.class)
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                    .header(HttpHeaders.AUTHORIZATION, "Token " + apiKey)
                    .header("X-Secret", xSecret)
                    .retrieve();

        } catch (Exception e) {
            throw new IncorrectRequestException(e.getMessage());
        }
    }
}
