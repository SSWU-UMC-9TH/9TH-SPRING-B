package spring.umc.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.umc.domain.member.entity.Address;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {

    /**
     * (홈화면) 사용자 법정동 조회
     */
    Optional<Address> findByMemberId(Long memberId);
}
