package spring.umc.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.umc.domain.member.entity.Member;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    /**
     * 마이페이지 화면 (메서드 생성 방식)
      */
    // 없어도 됨. JpaRepository에 이미 정의돼 있음
    Optional<Member> findById(Long id);

}
