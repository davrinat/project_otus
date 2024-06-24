package ru.project.otus.da_dataservice.service.common;

import reactor.core.publisher.Mono;
import ru.project.otus.da_dataservice.model.Suggestion;

public interface CommonService {

    Mono<Suggestion> findBank(String body);

    Mono<Suggestion> findFnsUnit(String body);

    Mono<Suggestion> findFtsUnit(String body);

    Mono<Suggestion> findGoodsBySource(String search);

    Mono<Suggestion> findCurrency(String query);
}
