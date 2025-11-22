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

    @Enumerated(EnumType.STRING)
    @Column(name = "native_language")
    private Language nativeLanguage;

    @Enumerated(EnumType.STRING)
    @Column(name = "target_language")
    private Language targetLanguage;

    public User(Language nativeLanguage, Language targetLanguage) {
        this.nativeLanguage = nativeLanguage;
        this.targetLanguage = targetLanguage;
    }
}
