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
            Long storeId,
            Integer page
    ){
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));

        // 조건: 한 페이지에 10개씩
        PageRequest pageRequest = PageRequest.of(page, 10);
        Page<Review> result = reviewRepository.findAllByStore(store, pageRequest);

        return ReviewConverter.toReviewPreviewListDTO(result);
    }
}
