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

    @Column
    @JoinColumn(name = "native_language")
    private String nativeLanguage;

    @Column
    @JoinColumn(name = "target_language")
    private String targetLanguage;

    @Column
    private String job;

    public User(String nativeLanguage, String targetLanguage, String job) {
        this.nativeLanguage = nativeLanguage;
        this.targetLanguage = targetLanguage;
        this.job = job;
    }
}
