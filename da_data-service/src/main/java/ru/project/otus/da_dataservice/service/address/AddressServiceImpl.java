package ru.project.otus.da_dataservice.service.address;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import ru.project.otus.da_dataservice.common.constant.BaseURL;
import ru.project.otus.da_dataservice.common.constant.PathConstant;
import ru.project.otus.da_dataservice.common.utility.Utils;
import ru.project.otus.da_dataservice.model.Suggestion;
import ru.project.otus.da_dataservice.model.response_model.Data;
import ru.project.otus.da_dataservice.service.RemoteService;

@Component
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final RemoteService remoteService;

    @Override
    public Mono<Data[]> findBySource(String search) {
        String baseURL = BaseURL.CLEANER_DADATA.getBase();
        String path = PathConstant.GEO_BY_ADDRESS.getPath();
        return remoteService.connect(baseURL, path, Utils.toArray(search)).bodyToMono(Data[].class);
    }

    @Override
    public Mono<Suggestion> findByGeo(String body) {
        String baseURL = BaseURL.SUGGESTION_DADATA.getBase();
        String path = PathConstant.ADDRESS_BY_GEO.getPath();
        return remoteService.connect(baseURL, path, body).bodyToMono(Suggestion.class);
    }

    @Override
    public Mono<Suggestion> findDeliveryId(String body) {
        String baseURL = BaseURL.SUGGESTION_DADATA.getBase();
        String path = PathConstant.DELIVERY.getPath();
        return remoteService.connect(baseURL, path, body).bodyToMono(Suggestion.class);
    }

    @Override
    public Mono<Suggestion> findPostalOffice(String body) {
        String baseURL = BaseURL.SUGGESTION_DADATA.getBase();
        String path = PathConstant.POSTAL_UNIT.getPath();
        return remoteService.connect(baseURL, path, body).bodyToMono(Suggestion.class);
    }
}
