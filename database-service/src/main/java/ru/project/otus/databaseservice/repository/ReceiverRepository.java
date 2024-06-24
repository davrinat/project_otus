package ru.project.otus.databaseservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.project.otus.databaseservice.model.JsonContent;

public interface ReceiverRepository extends JpaRepository<JsonContent, Long> { }
