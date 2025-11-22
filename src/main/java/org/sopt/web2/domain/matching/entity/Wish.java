package org.sopt.web2.domain.matching.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.sopt.web2.domain.user.entity.User;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Wish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private String location;

    @Column(name = "time_slot")
    private String timeSlot;

    @Column(name = "job")
    private String job;

    public Wish(User user, String location, String timeSlot, String job) {
        this.user = user;
        this.location = location;
        this.timeSlot = timeSlot;
        this.job = job;
    }
}
