package spring.umc.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import spring.umc.domain.user.dto.MyPageDto;
import spring.umc.domain.user.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // 마이 페이지 화면 쿼리
    @Query("SELECT new spring.umc.domain.user.dto.MyPageDto(u.nickname, u.email, u.phoneNumber, u.phoneVerified, u.point) FROM User u WHERE u.id = :userId")
    Optional<MyPageDto> findUserForMyPage(@Param("userId") Long userId);
}
