package ru.project.otus.da_dataservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.project.otus.da_dataservice.model.response_model.Data;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SuggestionDTO {
    private String value;

    @JsonProperty("unrestricted_value")
    private String unrestrictedValue;

    private Data data;
}
