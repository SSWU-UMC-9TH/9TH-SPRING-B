package spring.umc.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.umc.domain.user.entity.UserFood;

public interface UserFoodRepository extends JpaRepository<UserFood, Long> {
}
