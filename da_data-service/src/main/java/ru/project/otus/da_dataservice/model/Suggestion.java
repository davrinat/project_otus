package ru.project.otus.da_dataservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Suggestion {
    @JsonProperty("suggestions")
    private List<SuggestionDTO> listSuggestions;
}
