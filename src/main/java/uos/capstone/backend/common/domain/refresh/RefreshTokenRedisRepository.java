package uos.capstone.backend.common.domain.refresh;

import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRedisRepository extends CrudRepository<RefreshToken,String> {
}
