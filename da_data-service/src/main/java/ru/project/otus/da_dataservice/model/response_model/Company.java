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
public class Company {
    private String domain;
    private String name;
    private String inn;
    private String ogrn;
    private String okved;

    @JsonProperty("okved_name")
    private String okvedName;

    @JsonProperty("employee_count")
    private String employeeCount;

    private String income;
    private String city;
    private String timezone;
}
