package ru.job4j.cars.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.domain.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> { }
