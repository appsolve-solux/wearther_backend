package com.appsolve.wearther_backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder @Getter @Setter
@Table(name="taste")
public class Taste {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="taste_id")
    private Long id;

    @Column(name="taste_name", length=100)
    @NotNull
    private String name;

    @OneToMany(mappedBy = "taste", fetch = LAZY)
    private List<TasteUpperWear> upperWears = new ArrayList<>();
}
