package uos.capstone.backend.place.domain.repository;

import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import uos.capstone.backend.place.domain.Place;

public interface PlaceRepository extends JpaRepository<Place,Long>,PlaceRepositoryCustom {
}
