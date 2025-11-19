package spring.umc.domain.review.entity;

import jakarta.persistence.*;
import lombok.*;
import spring.umc.domain.common.entity.BaseEntity;
import spring.umc.domain.user.entity.User;
import spring.umc.domain.store.entity.Store;
import spring.umc.domain.user.entity.UserMission;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "review")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @Column(nullable = false)
    private Integer star;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // orphanRemoval = true 추가: 부모 엔티티가 삭제되면 자식 엔티티도 삭제 (고아 레코드 방지)
    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReviewReply> replies = new ArrayList<>();;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReviewImg> images = new ArrayList<>();;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_mission_id", unique = true)
    private UserMission userMission;

    @Builder
    public Review(User user, Store store, UserMission userMission, String content, int star) {
        this.user = user;
        this.store = store;
        this.userMission = userMission;
        this.content = content;
        this.star = star;
    }

    public void addReply(ReviewReply reply) {
        replies.add(reply);
        reply.setReview(this);
    }

    public void addImage(ReviewImg image) {
        images.add(image);
        image.setReview(this);
    }
}