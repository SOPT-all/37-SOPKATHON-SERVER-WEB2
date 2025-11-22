package org.sopt.web2.domain.user.repository;

import org.sopt.web2.domain.user.entity.Language;
import org.sopt.web2.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Modifying
	@Query("""
        UPDATE User u
        SET
            u.nativeLanguage = :nativeLanguage,
            u.targetLanguage = :targetLanguage
        WHERE u.id = :userId
    """)
	void patchUserByUserId(
		@Param("userId") Long userId,
		@Param("nativeLanguage") Language nativeLanguage,
		@Param("targetLanguage") Language targetLanguage
	);
}
