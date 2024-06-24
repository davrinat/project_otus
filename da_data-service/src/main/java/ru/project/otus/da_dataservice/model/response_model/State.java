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
public class State {
    private String status;
    private String code;

    @JsonProperty("actuality_date")
    private String actualityDate;

    @JsonProperty("registration_date")
    private String registrationDate;

    @JsonProperty("liquidation_date")
    private String liquidationDate;
}
