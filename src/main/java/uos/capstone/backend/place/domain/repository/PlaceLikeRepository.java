package uos.capstone.backend.place.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import uos.capstone.backend.place.domain.Place;
import uos.capstone.backend.place.domain.PlaceLike;
import uos.capstone.backend.user.domain.User;

public interface PlaceLikeRepository extends JpaRepository<PlaceLike,Long> {

	Optional<PlaceLike> deleteByUserAndPlace(User user, Place place);

}
