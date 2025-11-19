package spring.umc.domain.review.entity;

import jakarta.persistence.*;
import lombok.*;
import spring.umc.domain.common.entity.BaseEntity;
import spring.umc.domain.user.entity.User;
import java.io.Serializable;

@Entity
@Table(name = "review_reply")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewReply extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_id")
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id", nullable = false)
    private Review review;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public void setReview(Review review) {
        this.review = review;
        if (!review.getReplies().contains(this)) {
            review.getReplies().add(this);
        }
    }

}
