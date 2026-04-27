package ru.job4j.cars.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.job4j.cars.domain.*;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class PostRepositoryHibernateHqlTest {
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
    @Autowired
    private PostRepositoryHibernateHql postRepositoryHibernateHql;

    private Long postId;
    private Long carId;

    @BeforeEach
    public void setUp() {
        var user = new User("login", "password");
        var engine = new Engine("engine v.1");
        var owner = new Owner(user, "name");
        var car = new Car(engine, owner, "car 1");
        var post = new Post(user, car, "description");
        var photo = new Photo(post, "url123");
        var u = userRepository.saveAndFlush(user);
        var e = engineRepository.saveAndFlush(engine);
        var o = ownerRepository.saveAndFlush(owner);
        var c = carRepository.saveAndFlush(car);
        var p = postRepository.saveAndFlush(post);
        var ph = photoRepository.saveAndFlush(photo);
        postId = p.getId();
        carId = c.getId();
    }

    @AfterEach
    public void cleanUp() {
        photoRepository.deleteAll();
        postRepository.deleteAll();
        carRepository.deleteAll();
        ownerRepository.deleteAll();
        engineRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void whenFindBetweenDatesThenReturnPost() {
        var after = LocalDateTime.now().minusDays(1);
        var before = LocalDateTime.now().plusDays(1);
        var rsl = postRepositoryHibernateHql.findAllByCreatedBetween(after, before);
        assertThat(postId).isEqualTo(rsl.getFirst().getId());
    }

    @Test
    public void whenHasPhotosThenReturnOnlyThosePosts() {
        var rsl = postRepositoryHibernateHql.findAllWithPhotosOrderByCreatedDesc();
        assertThat(postId).isEqualTo(rsl.getFirst().getId());
    }

    @Test
    public void whenFindByCarIdThenReturnSortedPosts() {
        var rsl = postRepositoryHibernateHql.findAllByCarIdOrderByCreatedDesc(carId);
        assertThat(postId).isEqualTo(rsl.getFirst().getId());
    }
}