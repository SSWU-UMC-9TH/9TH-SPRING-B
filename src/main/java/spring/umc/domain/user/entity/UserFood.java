package spring.umc.domain.user.entity;

import jakarta.persistence.*;
import lombok.*;
import spring.umc.domain.common.BaseEntity;
import spring.umc.domain.store.entity.FoodCategory;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_food")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(UserFoodId.class)
public class UserFood extends BaseEntity {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Id
    @Column(name = "food_category_id")
    private Long foodCategoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("foodCategoryId")
    @JoinColumn(name = "food_category_id")
    private FoodCategory foodCategory;
}
