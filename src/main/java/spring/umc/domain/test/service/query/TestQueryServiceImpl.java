package spring.umc.domain.test.service.query;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.umc.domain.test.exception.TestException;
import spring.umc.domain.test.exception.code.TestErrorCode;

@Service
@RequiredArgsConstructor
public class TestQueryServiceImpl implements TestQueryService {

    @Override
    public void checkFlag(Long flag) {
        if(flag == 1){
            throw new TestException(TestErrorCode.TEST_EXCEPTION);
        }
    }

}
