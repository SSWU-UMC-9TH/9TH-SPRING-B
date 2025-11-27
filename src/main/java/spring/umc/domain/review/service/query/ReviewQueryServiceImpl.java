package spring.umc.domain.review.service.query;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.umc.domain.review.converter.ReviewConverter;
import spring.umc.domain.review.dto.res.ReviewResDTO;
import spring.umc.domain.review.entity.QReview;
import spring.umc.domain.review.entity.Review;
import spring.umc.domain.review.repository.ReviewRepository;
import spring.umc.domain.store.entity.QStore;
import spring.umc.domain.store.entity.Store;
import spring.umc.domain.store.exception.code.StoreErrorCode;
import spring.umc.domain.store.exception.code.StoreException;
import spring.umc.domain.store.repository.StoreRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;

    // 내가 작성한 리뷰 목록 조회
    public ReviewResDTO.MyReviewListDTO findMyReviews(
            Long memberId,
            Long storeId,
            String storeName,
            Integer star,
            Integer page // 1-based
    ) {

        // Q클래스 정의
        QReview review = QReview.review;
        QStore store = QStore.store;

        // BooleanBuilder 정의
        BooleanBuilder builder = new BooleanBuilder();

        // BooleanBuilder 사용 (동적 쿼리)

        builder.and(review.member.id.eq(memberId)) // 기본 조건 (필수), 로그인한 사용자가 작성한 리뷰만 조회
                .and(eqStoreId(storeId)) // 가게 필터
                .and(containsStoreName(storeName)) //가게 이름 필터
                .and(filterStarRange(star)); // 별점 필터


        // BooleanBuilder로 조건을 누적한 후,
        // Predicate로 조립하여 Repository로 전달
        Predicate predicate = builder;

        // 0-based로 변환
        PageRequest pageRequest = PageRequest.of(page - 1, 10); 

        // 조립된 조건(Predicate)을 기반으로 QueryDSL 쿼리 실행
        List<ReviewResDTO.MyReviewDTO> contents =
                reviewRepository.findMyReviews(predicate, pageRequest);

        long totalElements = reviewRepository.countMyReviews(predicate);

        return ReviewConverter.toMyReviewListDTO(contents, pageRequest, totalElements);

    }

    @Override
    public List<Review> searchReview(String filter, String type) throws Exception {
        // TODO: 실제 검색 로직
        //return reviewRepository.searchReview(filter, type);
        return null;
    }


    // 가게의 리뷰 목록
    @Override
    public ReviewResDTO.ReviewPreViewListDTO findReview(
            String storeName,
            Integer page
    ){
        // - 가게를 가져온다 (가게 존재 여부 검증)
        Store store = storeRepository.findByName(storeName)
                //    - 없으면 예외 터뜨린다
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        //- 가게에 맞는 리뷰를 가져온다 (Offset 페이징)
        // 프론트에서 전달되는 page는 1-based -> PageRequest는 0-based 이므로 -1 처리
        PageRequest pageRequest = PageRequest.of(page - 1, 10);
        Page<Review> result = reviewRepository.findAllByStore(store, pageRequest);

        //- 결과를 응답 DTO로 변환한다 (컨버터 이용)
        return ReviewConverter.toReviewPreviewListDTO(result);
    }


    // 가게 필터 (선택)
    private BooleanExpression eqStoreId(Long storeId) {
        QStore store = QStore.store;
        if (storeId == null) return null;   // null이면 조건 안 붙이게 함
        return store.id.eq(storeId);
    }

    // 가게 이름 필터 (선택)
    private BooleanExpression containsStoreName(String storeName) {
        QStore store = QStore.store;
        if (storeName == null || storeName.isBlank()) return null;
        return store.name.containsIgnoreCase(storeName.trim());
    }

    // 별점대 필터 (선택)
    private BooleanExpression filterStarRange(Integer star) {
        QReview review = QReview.review;
        if (star == null) return null;

        if (star == 5) {
            return review.star.eq(5.0);
        } else if (star >= 0 && star <= 4) {
            return review.star.goe(star.doubleValue())
                    .and(review.star.lt(star.doubleValue() + 1.0));
        }
        return null; // 범위 밖이면 조건 안 붙임
    }

}
