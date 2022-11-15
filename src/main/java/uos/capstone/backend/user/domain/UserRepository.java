package uos.capstone.backend.user.domain;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByname(String name);

	Optional<User> findBynickname(String nickname);

	Optional<User> findByemail(String email);

	Boolean existsByEmail(String email);

	Boolean existsByNickname(String nickname);

	Slice<User> findByNicknameContaining(String content, Pageable pageable);
}
