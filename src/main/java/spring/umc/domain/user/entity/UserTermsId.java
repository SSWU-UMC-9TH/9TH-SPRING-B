package spring.umc.domain.user.entity;

import lombok.*;
import java.io.Serializable;

// 복합키 표현용 클래스
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
public class UserTermsId implements Serializable {
    private Long userId;
    private Long termsId;
}
