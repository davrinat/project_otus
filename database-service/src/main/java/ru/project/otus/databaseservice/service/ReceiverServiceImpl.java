package ru.project.otus.databaseservice.service;

import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.project.otus.databaseservice.model.JsonContent;
import ru.project.otus.databaseservice.repository.ReceiverRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReceiverServiceImpl implements ReceiverService {
    private final ReceiverRepository repository;

    @Override
    public void save(String body) {

        var gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        var json = gson.toJson(body);

        try {
            repository.save(new JsonContent(body));
            log.info("Operation completed :: content -> {}", json);
        } catch (Exception e) {
            log.error("Operation failed :: message -> {}", e.getMessage());
        }
    }
}
