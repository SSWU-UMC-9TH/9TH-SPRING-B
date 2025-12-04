package spring.umc.domain.food.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.umc.domain.food.entity.Food;

public interface FoodRepository extends JpaRepository<Food, Long> {
}
