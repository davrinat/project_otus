package ru.project.otus.da_dataservice.service.company;

import reactor.core.publisher.Mono;
import ru.project.otus.da_dataservice.model.Suggestion;

public interface CompanyService {

    Mono<Suggestion> findByInn(String body);

    Mono<Suggestion> findAffiliated(String body);

    Mono<Suggestion> findByEmail(String body);
}
