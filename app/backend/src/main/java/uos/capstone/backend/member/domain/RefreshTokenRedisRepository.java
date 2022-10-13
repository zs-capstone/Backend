package uos.capstone.backend.member.domain;

import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRedisRepository extends CrudRepository<RefreshToken,String> {
}
