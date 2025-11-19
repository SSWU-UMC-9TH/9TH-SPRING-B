package spring.umc.domain.store.entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Entity
@Table(name = "food_category")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FoodCategory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_category_id")
    private Long id;

    @Column(nullable = false)
    private String name;
}
