package ru.job4j.cars.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cars")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "engine_id")
    private Engine engine;

    @ManyToOne()
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @NotBlank
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "history_owners",
            joinColumns = @JoinColumn(name = "car_id"),
            inverseJoinColumns = @JoinColumn(name = "owner_id")
    )
    private Set<Owner> owners = new HashSet<>();

    public Car(Engine engine, Owner owner, String name) {
        this.engine = engine;
        this.owner = owner;
        this.name = name;
    }
}
