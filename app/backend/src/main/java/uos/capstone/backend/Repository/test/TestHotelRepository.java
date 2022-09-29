package uos.capstone.backend.Repository.test;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uos.capstone.backend.Model.Eat;
import uos.capstone.backend.Model.Hotel;

import java.util.List;

@Repository
public interface TestHotelRepository extends JpaRepository<Hotel, Integer> {
    public List<Hotel> findAll();
}
