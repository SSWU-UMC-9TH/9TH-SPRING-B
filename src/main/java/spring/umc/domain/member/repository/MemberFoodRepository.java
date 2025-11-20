package spring.umc.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.umc.domain.member.entity.mapping.MemberFood;

public interface MemberFoodRepository extends JpaRepository<MemberFood, Long> {
}
