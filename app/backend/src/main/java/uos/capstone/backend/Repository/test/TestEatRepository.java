package uos.capstone.backend.Repository.test;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uos.capstone.backend.Model.Eat;

import java.util.List;

@Repository
public interface TestEatRepository extends JpaRepository<Eat, Integer> {
    public List<Eat> findAll();
}
