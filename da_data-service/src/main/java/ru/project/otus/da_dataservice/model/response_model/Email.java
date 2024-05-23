package ru.project.otus.da_dataservice.model.response_model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Email {
    private String local;
    private String domain;
    private String type;
    private String source;
    private String email;
}
