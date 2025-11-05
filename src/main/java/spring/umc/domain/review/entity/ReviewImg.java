package spring.umc.domain.review.entity;

import jakarta.persistence.*;
import lombok.*;
import spring.umc.domain.common.BaseEntity;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "review_img")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewImg extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "img_id")
    private Long id;

    @Column(nullable = false)
    private String imgUrl;

    @Column(name = "orders")
    private Integer order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id", nullable = false)
    private Review review;

    public void setReview(Review review) {
        this.review = review;
        if (!review.getImages().contains(this)) {
            review.getImages().add(this);
        }
    }
}
