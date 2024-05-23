package ru.project.otus.databaseservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.project.otus.databaseservice.service.ReceiverService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/db")
public class ReceiverController {
    private final ReceiverService service;

    @PostMapping("/save")
    public void save(@RequestBody String body) {
        service.save(body);
    }
}
