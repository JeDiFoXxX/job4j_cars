package ru.job4j.cars.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.job4j.cars.domain.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Long> { }
