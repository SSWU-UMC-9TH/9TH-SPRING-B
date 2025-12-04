package spring.umc.domain.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.umc.domain.store.entity.FoodCategory;

public interface FoodCategoryRepository extends JpaRepository<FoodCategory, Long> {
}
