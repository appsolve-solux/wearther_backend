package com.appsolve.wearther_backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name="lower_wear")
public class LowerWear {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="lower_wear_id")
    private Long id;

    @Column(name = "lower_wear_name")
    @NotNull
    private String name;

    @Column(name="lower_close_type")
    @NotNull
    private int type;

    @OneToMany(mappedBy = "lowerWear", fetch = LAZY)
    private List<TasteLowerWear> tasteLowerWears = new ArrayList<>();
}
