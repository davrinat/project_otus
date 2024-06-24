package ru.project.otus.da_dataservice.common.constant;

import lombok.Getter;

@Getter
public enum PathConstant {
    GEO_BY_ADDRESS("/api/v1/clean/address"),
    ADDRESS_BY_GEO("/suggestions/api/4_1/rs/geolocate/address"),
    POSTAL_UNIT("/suggestions/api/4_1/rs/findById/postal_unit"),
    DELIVERY("/suggestions/api/4_1/rs/findById/delivery"),
    BY_INN("/suggestions/api/4_1/rs/findById/party"),
    AFFILIATED("/suggestions/api/4_1/rs/findAffiliated/party"),
    BY_EMAIL("/suggestions/api/4_1/rs/findByEmail/company"),
    FNS_UNIT("/suggestions/api/4_1/rs/suggest/fns_unit"),
    FTS_UNIT("/suggestions/api/4_1/rs/suggest/fts_unit"),
    GOODS("/suggestions/api/4_1/rs/suggest/mktu"),
    CURRENCY("/suggestions/api/4_1/rs/suggest/currency"),
    BANK("/suggestions/api/4_1/rs/findById/bank");

    private final String path;

    PathConstant(String path) {
        this.path = path;
    }
}
