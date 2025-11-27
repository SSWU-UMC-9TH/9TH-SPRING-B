package spring.umc.domain.user.entity;

import jakarta.persistence.*;
import lombok.*;
import spring.umc.domain.common.entity.BaseEntity;
import spring.umc.domain.mission.entity.Mission;

@Entity
@Table(name = "user_mission")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserMission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_mission_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MissionStatus status = MissionStatus.NOT_STARTED;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;

    @Builder
    public UserMission(User user, Mission mission, MissionStatus status) {
        this.user = user;
        this.mission = mission;
        this.status = (status != null) ? status : MissionStatus.NOT_STARTED;
    }
}
