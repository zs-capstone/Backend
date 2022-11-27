package uos.capstone.backend.place.domain.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import uos.capstone.backend.place.domain.Place;
import uos.capstone.backend.place.domain.PlaceLike;
import uos.capstone.backend.place.domain.PlaceReview;
import uos.capstone.backend.user.domain.User;

public interface PlaceReviewRepository extends JpaRepository<PlaceReview,Long> {

	Slice<PlaceReview> findByPlace(Place place, Pageable pageable);

	void deleteAllByUser(User user);

}
