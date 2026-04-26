package ru.job4j.cars.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "owners")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull
    @OneToOne
    @JoinColumn(name = "auto_user_id")
    private User user;

    @NotBlank
    private String name;

    public Owner(User user, String name) {
        this.user = user;
        this.name = name;
    }
}
