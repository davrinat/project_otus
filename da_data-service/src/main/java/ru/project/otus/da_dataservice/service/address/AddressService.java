package ru.project.otus.da_dataservice.service.address;

import reactor.core.publisher.Mono;
import ru.project.otus.da_dataservice.model.Suggestion;
import ru.project.otus.da_dataservice.model.response_model.Data;

public interface AddressService {
    Mono<Data[]> findBySource(String search);

    Mono<Suggestion> findByGeo(String body);

    Mono<Suggestion> findDeliveryId(String body);

    Mono<Suggestion> findPostalOffice(String body);
}
