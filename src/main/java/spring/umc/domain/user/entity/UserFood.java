package spring.umc.domain.user.entity;

import jakarta.persistence.*;
import lombok.*;
import spring.umc.domain.store.entity.FoodCategory;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_food")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(UserFoodId.class)
public class UserFood {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Id
    @Column(name = "food_category_id")
    private Long foodCategoryId;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("foodCategoryId")
    @JoinColumn(name = "food_category_id")
    private FoodCategory foodCategory;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt;
}
