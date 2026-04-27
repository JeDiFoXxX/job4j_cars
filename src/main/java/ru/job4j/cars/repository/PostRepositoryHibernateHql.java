package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.job4j.cars.domain.Post;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class PostRepositoryHibernateHql {
    private final CrudRepository cd;

    public List<Post> findAllByCreatedBetween(LocalDateTime after, LocalDateTime before) {
        return cd.optional("""
                        FROM Post po
                        WHERE po.created > :after AND po.created < :before""",
                Post.class,
                Map.of("after", after, "before", before)
        );
    }

    public List<Post> findAllWithPhotosOrderByCreatedDesc() {
        return cd.optional(
                """
                        FROM Post po
                        WHERE EXISTS (SELECT 1 FROM Photo ph WHERE ph.post.id = po.id)
                        ORDER BY po.created DESC""",
                Post.class,
                Map.of()
        );
    }

    public List<Post> findAllByCarIdOrderByCreatedDesc(Long id) {
        return cd.optional(
                """
                        FROM Post po
                        WHERE po.car.id = :id
                        ORDER BY po.created DESC
                        """,
                Post.class,
                Map.of("id", id)
        );
    }
}
