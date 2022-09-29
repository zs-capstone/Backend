package uos.capstone.backend.Repository.test;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uos.capstone.backend.Model.Hotel;
import uos.capstone.backend.Model.Place;

import java.util.List;

@Repository
public interface TestPlaceRepository extends JpaRepository<Place, Integer> {
    public List<Place> findAll();
}
