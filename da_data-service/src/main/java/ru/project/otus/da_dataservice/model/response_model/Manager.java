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
public class Manager {
    private String inn;
    private String post;
    private String hid;
    private String type;
    private String invalidity;

    @JsonProperty("fio")
    private Person person;
}
