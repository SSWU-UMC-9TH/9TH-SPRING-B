package spring.umc.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.umc.domain.member.entity.Food;

public interface FoodRepository extends JpaRepository<Food, Long> {
}
