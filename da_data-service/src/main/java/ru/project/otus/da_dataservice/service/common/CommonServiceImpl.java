package ru.project.otus.da_dataservice.service.common;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import ru.project.otus.da_dataservice.common.constant.BaseURL;
import ru.project.otus.da_dataservice.common.constant.PathConstant;
import ru.project.otus.da_dataservice.common.utility.Utils;
import ru.project.otus.da_dataservice.model.Suggestion;
import ru.project.otus.da_dataservice.service.RemoteService;

@Component
@RequiredArgsConstructor
public class CommonServiceImpl implements CommonService{
    private final RemoteService remoteService;

    @Override
    public Mono<Suggestion> findBank(String body) {
        String baseURL = BaseURL.SUGGESTION_DADATA.getBase();
        String path = PathConstant.BANK.getPath();
        return remoteService.connect(baseURL, path, body).bodyToMono(Suggestion.class);
    }

    @Override
    public Mono<Suggestion> findFnsUnit(String body) {
        String baseURL = BaseURL.SUGGESTION_DADATA.getBase();
        String path = PathConstant.FNS_UNIT.getPath();
        return remoteService.connect(baseURL, path, body).bodyToMono(Suggestion.class);
    }

    @Override
    public Mono<Suggestion> findFtsUnit(String body) {
        String baseURL = BaseURL.SUGGESTION_DADATA.getBase();
        String path = PathConstant.FTS_UNIT.getPath();
        return remoteService.connect(baseURL, path, body).bodyToMono(Suggestion.class);
    }

    @Override
    public Mono<Suggestion> findGoodsBySource(String query) {
        String baseURL = BaseURL.SUGGESTION_DADATA.getBase();
        String path = PathConstant.GOODS.getPath();
        return remoteService.connect(baseURL, path, Utils.toJson(query)).bodyToMono(Suggestion.class);
    }

    @Override
    public Mono<Suggestion> findCurrency(String query) {
        String baseURL = BaseURL.SUGGESTION_DADATA.getBase();
        String path = PathConstant.CURRENCY.getPath();
        return remoteService.connect(baseURL, path, Utils.toJson(query)).bodyToMono(Suggestion.class);
    }
}
