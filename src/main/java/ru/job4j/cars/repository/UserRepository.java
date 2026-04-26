package ru.job4j.cars.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.job4j.cars.domain.User;

public interface UserRepository extends JpaRepository<User, Long> { }
