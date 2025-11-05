package spring.umc.domain.user.entity;

import lombok.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
public class UserFoodId implements Serializable {
    private Long userId;
    private Long foodCategoryId;
}
