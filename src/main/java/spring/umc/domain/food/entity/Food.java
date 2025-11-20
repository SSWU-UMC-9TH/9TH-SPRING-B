package spring.umc.domain.food.entity;

import jakarta.persistence.*;
import lombok.*;
import spring.umc.domain.food.enums.FoodType;
import spring.umc.domain.member.entity.Member;
import spring.umc.global.entity.BaseEntity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "food")
public class Food extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false)
    private FoodType name;
}
