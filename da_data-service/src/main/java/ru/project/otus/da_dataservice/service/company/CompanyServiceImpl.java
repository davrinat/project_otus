package ru.project.otus.da_dataservice.service.company;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import ru.project.otus.da_dataservice.common.constant.BaseURL;
import ru.project.otus.da_dataservice.common.constant.PathConstant;
import ru.project.otus.da_dataservice.model.Suggestion;
import ru.project.otus.da_dataservice.service.RemoteService;

@Component
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final RemoteService remoteService;

    @Override
    public Mono<Suggestion> findByInn(String body) {
        String baseURL = BaseURL.SUGGESTION_DADATA.getBase();
        String path = PathConstant.BY_INN.getPath();
        return remoteService.connect(baseURL, path, body).bodyToMono(Suggestion.class);
    }

    @Override
    public Mono<Suggestion> findAffiliated(String body) {
        String baseURL = BaseURL.SUGGESTION_DADATA.getBase();
        String path = PathConstant.AFFILIATED.getPath();
        return remoteService.connect(baseURL, path, body).bodyToMono(Suggestion.class);
    }

    @Override
    public Mono<Suggestion> findByEmail(String body) {
        String baseURL = BaseURL.SUGGESTION_DADATA.getBase();
        String path = PathConstant.BY_EMAIL.getPath();
        return remoteService.connect(baseURL, path, body).bodyToMono(Suggestion.class);
    }
}
