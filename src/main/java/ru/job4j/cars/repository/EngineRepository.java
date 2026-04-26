package ru.job4j.cars.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.job4j.cars.domain.Engine;

public interface EngineRepository extends JpaRepository<Engine, Long> { }
