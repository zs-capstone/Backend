package uos.capstone.backend.member.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
    public Optional<Member> findByEmail(String email);

    public Optional<Member> findByNickname(String nickname);

    public Optional<Member> findByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByNickname(String nickname);

    @Query("select m from Member m join fetch m.authorities a where m.username = :username")
    Optional<Member> findByUsernameWithAuthority(String username);

//    @Query("update member set nickname= :nickname where username= :username")
//    void updateNickname(String username,String nickname);
}
