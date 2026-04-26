package ru.job4j.cars.repository;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.job4j.cars.domain.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class PostRepositoryTest {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EngineRepository engineRepository;
    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private PhotoRepository photoRepository;

    @Test
    public void whenFindBetweenDatesThenReturnPost() {
        var user = new User("login", "password");
        var engine = new Engine("engine v.1");
        var owner = new Owner(user, "name");
        var car = new Car(engine, owner, "car 1");
        var post = new Post(user, car, "description");
        var after = LocalDateTime.now().minusDays(1);
        var before = LocalDateTime.now().plusDays(1);
        userRepository.save(user);
        engineRepository.save(engine);
        ownerRepository.save(owner);
        carRepository.save(car);
        postRepository.save(post);
        var rsl = postRepository.findAllByCreatedBetween(after, before);
        assertThat(post.getId()).isEqualTo(rsl.getFirst().getId());
    }

    @Test
    public void whenHasPhotosThenReturnOnlyThosePosts() {
        var user = new User("login", "password");
        var engine = new Engine("engine v.1");
        var owner = new Owner(user, "name");
        var car = new Car(engine, owner, "car 1");
        var firstPost = new Post(user, car, "first description");
        var secondPost = new Post(user, car, "second description");
        var photo = new Photo(secondPost, "url123");
        userRepository.save(user);
        engineRepository.save(engine);
        ownerRepository.save(owner);
        carRepository.save(car);
        postRepository.saveAll(List.of(firstPost, secondPost));
        photoRepository.save(photo);
        var rsl = postRepository.findAllWithPhotosOrderByCreatedDesc();
        assertThat(1).isEqualTo(rsl.size());
        assertThat(secondPost.getId()).isEqualTo(rsl.getFirst().getId());
    }

    @Test
    public void whenFindByCarIdThenReturnSortedPosts() {
        var user = new User("login", "password");
        var engine = new Engine("engine v.1");
        var owner = new Owner(user, "name");
        var car = new Car(engine, owner, "car 1");
        var firstPost = new Post(user, car, "first description");
        var secondPost = new Post(user, car, "second description");
        userRepository.save(user);
        engineRepository.save(engine);
        ownerRepository.save(owner);
        carRepository.save(car);
        postRepository.saveAll(List.of(firstPost, secondPost));
        var rsl = postRepository.findAllByCarIdOrderByCreatedDesc(car.getId());
        assertThat(2).isEqualTo(rsl.size());
        assertThat(List.of(secondPost, firstPost)).containsExactlyInAnyOrderElementsOf(rsl);
    }
}