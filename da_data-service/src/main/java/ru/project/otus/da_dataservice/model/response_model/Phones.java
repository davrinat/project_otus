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
public class Phones {
    private String value;
    private Phone data;

    @JsonProperty("unrestricted_value")
    private String unrestrictedValue;
}
