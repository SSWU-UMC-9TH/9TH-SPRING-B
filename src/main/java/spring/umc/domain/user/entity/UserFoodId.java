package spring.umc.domain.user.entity;

import lombok.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserFoodId implements Serializable {
    private Long userId;
    private Long foodCategoryId;
}
