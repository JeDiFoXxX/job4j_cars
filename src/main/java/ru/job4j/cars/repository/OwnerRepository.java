package ru.job4j.cars.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.job4j.cars.domain.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Long> { }
