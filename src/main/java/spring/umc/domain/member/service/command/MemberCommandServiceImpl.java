package spring.umc.domain.member.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.umc.domain.member.converter.MemberConverter;
import spring.umc.domain.member.dto.req.MemberReqDTO;
import spring.umc.domain.member.dto.res.MemberResDTO;
import spring.umc.domain.member.entity.Address;
import spring.umc.domain.member.entity.Member;
import spring.umc.domain.member.entity.mapping.MemberFood;
import spring.umc.domain.member.exception.FoodException;
import spring.umc.domain.member.exception.code.FoodErrorCode;
import spring.umc.domain.member.repository.FoodRepository;
import spring.umc.domain.member.repository.MemberFoodRepository;
import spring.umc.domain.member.repository.MemberRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService{

    private final MemberRepository memberRepository;
    private final MemberFoodRepository memberFoodRepository;
    private final FoodRepository foodRepository;

    // 회원가입
    @Override
    @Transactional
    public MemberResDTO.JoinDTO signup(
            MemberReqDTO.JoinDTO dto
    ){
        // 사용자 생성
        Member member = MemberConverter.toMember(dto);

        // Address 엔티티 생성 (DTO -> Entity)
        MemberReqDTO.AddressDTO addrDto = dto.address();

        Address address = Address.builder()
                .postalCode(addrDto.postalCode())
                .roadAddress(addrDto.roadAddress())
                .addressDetail(addrDto.addressDetail())
                .fullAddress(addrDto.fullAddress())
                .legalDong(addrDto.legalDong())
                .legalDongCode(addrDto.legalDongCode())
                // 위/경도는 null로 두기 -> 나중에 값 세팅
                .longitude(null)
                .latitude(null)
                .geocodedAt(null)
                .build();

        // 양방향 연관관계 설정
        member.setAddress(address);  // 내부에서 address.setMember(this)까지 처리됨

        // DB 적용 (cascade로 Address도 같이 저장)
        memberRepository.save(member);

        // 선호 음식 존재 여부 확인
        if (dto.preferCategory().size() > 1){
            List<MemberFood> memberFood = dto.preferCategory().stream()
                    .map(id -> MemberFood.builder()
                            .member(member)
                            .food(foodRepository.findById(id)
                                    .orElseThrow(() -> new FoodException(FoodErrorCode.NOT_FOUND)))
                            .build()
                    )
                    .collect(Collectors.toList());

            // 모든 선호 음식 추가: DB 적용
            memberFoodRepository.saveAll(memberFood);
        }


        // 응답 DTO 생성
        return MemberConverter.toJoinDTO(member);
    }
}