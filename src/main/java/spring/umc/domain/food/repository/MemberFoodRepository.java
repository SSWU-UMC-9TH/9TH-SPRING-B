package spring.umc.domain.food.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.umc.domain.food.entity.MemberFood;

public interface MemberFoodRepository extends JpaRepository<MemberFood, Long> {
}
