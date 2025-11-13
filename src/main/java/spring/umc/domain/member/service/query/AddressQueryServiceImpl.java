package spring.umc.domain.member.service.query;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.umc.domain.member.converter.MemberConverter;
import spring.umc.domain.member.dto.res.MemberResDto;
import spring.umc.domain.member.entity.Address;
import spring.umc.domain.member.exception.AddressException;
import spring.umc.domain.member.exception.code.AddressErrorCode;
import spring.umc.domain.member.repository.AddressRepository;

@Service
@RequiredArgsConstructor
public class AddressQueryServiceImpl implements AddressQueryService {

    private final AddressRepository addressRepository;

    /**
     * (홈화면) 사용자 법정동 조회
     */
    @Override
    @Transactional(readOnly = true)
    public MemberResDto.LegalDong getLegalDongInfo(Long memberId) {
        Address a = addressRepository.findByMemberId(memberId)
                .orElseThrow(() -> new AddressException(AddressErrorCode.ADDRESS404_1));

        return MemberConverter.toLegalDong(a);
    }
}
