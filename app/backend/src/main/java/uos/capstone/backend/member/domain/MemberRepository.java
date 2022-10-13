package uos.capstone.backend.member.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
    public Optional<Member> findByEmail(String email);

    public Optional<Member> findByNickname(String nickname);
    boolean existsByEmail(String email);

    @Query("select m from Member m join fetch m.authorities a where m.nickname = :nickname")
    Optional<Member> findByNicknameWithAuthority(String nickname);
}
