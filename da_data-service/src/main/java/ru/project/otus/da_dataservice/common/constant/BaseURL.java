package ru.project.otus.da_dataservice.common.constant;

import lombok.Getter;

@Getter
public enum BaseURL {
    CLEANER_DADATA("https://cleaner.dadata.ru"),
    SUGGESTION_DADATA("http://suggestions.dadata.ru");

    private final String base;

    BaseURL(String baseURL) {
        this.base = baseURL;
    }
}
