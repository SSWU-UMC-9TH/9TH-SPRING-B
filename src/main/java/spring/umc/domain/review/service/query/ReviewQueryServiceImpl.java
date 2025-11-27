package spring.umc.domain.review.service.query;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import spring.umc.domain.review.converter.ReviewConverter;
import spring.umc.domain.review.dto.ReviewResponseDto;
import spring.umc.domain.review.entity.Review;
import spring.umc.domain.review.repository.ReviewRepository;
import spring.umc.domain.store.entity.Store;
import spring.umc.domain.store.exception.StoreException;
import spring.umc.domain.store.exception.code.StoreErrorCode;
import spring.umc.domain.store.repository.StoreRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public List<Review> searchReview(String filter, String type) throws Exception {
        return List.of();
    }

    @Override
    public ReviewResponseDto.ReviewPreViewListDTO findReview(
            String storeName,
            Integer page
    ){
        // - 가게를 가져온다 (가게 존재 여부 검증)
        Store store = storeRepository.findByName(storeName)
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));

        //- 가게에 맞는 리뷰를 가져온다 (Offset 페이징)
        PageRequest pageRequest = PageRequest.of(page, 5);
        Page<Review> result = reviewRepository.findAllByStore(store, pageRequest);

        //- 결과를 응답 DTO로 변환한다 (컨버터 이용)
        return ReviewConverter.toReviewPreviewListDTO(result);
    }
}
