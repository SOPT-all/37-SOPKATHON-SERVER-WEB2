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
    @Enumerated(EnumType.STRING)
    private Language nativeLanguage;

    @Column(name = "target_language")
    @Enumerated(EnumType.STRING)
    private Language targetLanguage;

    /*
    @Column
    private String job;

     */

    public User(Language nativeLanguage, Language targetLanguage) {
        this.nativeLanguage = nativeLanguage;
        this.targetLanguage = targetLanguage;
        //this.job = job;
    }
}
