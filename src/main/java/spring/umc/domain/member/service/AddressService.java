package spring.umc.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.umc.domain.member.dto.LegalDongDto;
import spring.umc.domain.member.entity.Address;
import spring.umc.domain.member.repository.AddressRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    /**
     * (홈화면) 사용자 법정동 조회
     */
    @Transactional(readOnly = true)
    public LegalDongDto getLegalDongInfo(Long memberId) {
        Address a = addressRepository.findByMemberId(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원의 주소가 없습니다."));

        return new LegalDongDto(
                a.getLegalDong(),
                a.getLegalDongCode()
        );
    }
}
