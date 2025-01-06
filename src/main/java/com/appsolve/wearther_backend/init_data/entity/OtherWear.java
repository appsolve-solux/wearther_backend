package com.appsolve.wearther_backend.init_data.entity;

import com.appsolve.wearther_backend.closet.entity.ClosetOther;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder @Getter
@Table(name="other_wear")
public class OtherWear {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="other_wear_id")
    private Long id;

    @Column(name = "other_wear_name", length = 100) @NotNull
    private String name;

    @Builder.Default
    @OneToMany(mappedBy = "otherWear", fetch = LAZY)
    private List<TasteOtherWear> tasteOtherWears = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy ="otherWear", fetch = LAZY)
    private List<WeatherOtherWear> weathers = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "otherWear", fetch = LAZY)
    private List<ClosetOther> closets = new ArrayList<>();
}