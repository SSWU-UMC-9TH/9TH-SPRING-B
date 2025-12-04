package spring.umc.domain.auth.service.query;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.umc.domain.auth.dto.AuthReqDto;
import spring.umc.domain.auth.dto.AuthResDto;

@Service
public interface AuthQueryService {
    AuthResDto.LoginDTO login(
            AuthReqDto.@Valid LoginDTO dto
    );
}
