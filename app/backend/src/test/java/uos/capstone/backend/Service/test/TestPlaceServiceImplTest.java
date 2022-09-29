package uos.capstone.backend.Service.test;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import uos.capstone.backend.Model.Place;
import uos.capstone.backend.Repository.test.TestPlaceRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TestPlaceServiceImplTest {

    @Autowired
    TestPlaceRepository repository;

    @Test
    public void list_all(){
        List<Place> list = repository.findAll();
        System.out.println(list.get(0));
        Assertions.assertThat(list.size()).isEqualTo(1089);
    }

}