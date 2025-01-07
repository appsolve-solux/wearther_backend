package com.appsolve.wearther_backend.init_data.entity;

import com.appsolve.wearther_backend.closet.entity.ClosetUpper;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder @Getter
@Table(name="upper_wear")
public class UpperWear {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "upper_wear_id")
    private Long id;

    @Column(name = "upper_wear_name", length=100) @NotNull
    private String name;

    @Column(name="upper_wear_type") @NotNull
    private int type;

    @Builder.Default
    @OneToMany(mappedBy = "upperWear", fetch = LAZY)
    private List<TasteUpperWear> tastes = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "upperWear", fetch = LAZY)
    private List<WeatherUpperWear> weathers = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "upperWear", fetch = LAZY)
    private List<ClosetUpper> closets = new ArrayList<>();
}
