package org.sopt.web2.domain.user.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "native_language")
    private Language nativeLanguage;

    @Column(name = "target_language")
    private Language targetLanguage;

    @Column
    private String job;

    public User(Language nativeLanguage, Language targetLanguage, String job) {
        this.nativeLanguage = nativeLanguage;
        this.targetLanguage = targetLanguage;
        this.job = job;
    }
}
