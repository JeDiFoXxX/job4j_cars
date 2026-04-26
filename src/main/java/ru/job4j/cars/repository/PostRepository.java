package ru.job4j.cars.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.job4j.cars.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long> { }
