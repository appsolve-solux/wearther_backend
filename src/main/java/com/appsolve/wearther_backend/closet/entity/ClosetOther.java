package com.appsolve.wearther_backend.closet.entity;

import com.appsolve.wearther_backend.init_data.entity.OtherWear;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder @Getter
@Table(name="closet_other")
public class ClosetOther {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "closet_other_id")
    private Long id;

    @ManyToOne @JoinColumn(name = "closet_id")
    private Closet closet;

    @ManyToOne @JoinColumn(name = "other_wear_id")
    private OtherWear otherWear;
}
