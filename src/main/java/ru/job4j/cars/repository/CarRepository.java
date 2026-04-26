package ru.job4j.cars.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.job4j.cars.domain.Car;

public interface CarRepository extends JpaRepository<Car, Long> { }
