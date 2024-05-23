package ru.project.otus.da_dataservice.model.response_model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Phone {
    private String contact;
    private String source;
    private String type;
    private String provider;
    private String region;
    private String timezone;

    @JsonProperty("country_code")
    private String countryCode;

    @JsonProperty("city_code")
    private String cityCode;
}
