package ru.project.otus.da_dataservice.model.response_model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Management {
    private String name;
    private String post;
    private String disqualified;
}
