package spring.umc.domain.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass  // 이 클래스를 상속받는 엔티티는 컬럼 정보를 물려받는다.
@EntityListeners(AuditingEntityListener.class) // 필드를 자동으로 채워준다.
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BaseEntity {

    @CreatedDate
    @Column(updatable = false, nullable = false)    // 최초 생성 때만 업데이트
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;
}
