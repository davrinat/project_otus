package ru.project.otus.databaseservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "dadata")
@Getter
@Setter
@NoArgsConstructor
public class JsonContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "jsonb_content")
    private String json;

    public JsonContent(String json) {
        this.json = json;
    }
}
