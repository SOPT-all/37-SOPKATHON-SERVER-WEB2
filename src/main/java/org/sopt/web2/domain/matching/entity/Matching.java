package org.sopt.web2.domain.matching.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.sopt.web2.domain.user.entity.User;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Matching {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
    @JoinColumn(name = "match_user")
    private User matchUser;

    //매칭 상대방
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
    @JoinColumn(name = "match_partner")
    private User matchPartner;

    @Column
    private boolean isCanceled;

    public Matching(User matchUser, User matchPartner) {
        this.matchUser = matchUser;
        this.matchPartner = matchPartner;
        this.isCanceled = false;
    }
}
