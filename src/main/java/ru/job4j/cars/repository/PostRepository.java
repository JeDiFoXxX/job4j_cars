package ru.job4j.cars.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.cars.domain.Post;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByCreatedBetween(LocalDateTime after, LocalDateTime before);

    @Query(value = """
            SELECT po.* FROM posts po
            WHERE EXISTS (SELECT 1 FROM photos ph WHERE ph.post_id = po.id)
            ORDER BY created DESC
            """, nativeQuery = true)
    List<Post> findAllWithPhotosOrderByCreatedDesc();

    List<Post> findAllByCarIdOrderByCreatedDesc(Long id);
}
