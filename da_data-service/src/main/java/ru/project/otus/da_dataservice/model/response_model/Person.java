package ru.project.otus.da_dataservice.model.response_model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private String surname;
    private String name;
    private String patronymic;
    private String gender;
    private String source;
    private String qc;
}
