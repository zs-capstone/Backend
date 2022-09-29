package uos.capstone.backend.Service.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uos.capstone.backend.Model.Eat;
import uos.capstone.backend.Model.Hotel;
import uos.capstone.backend.Model.Result;
import uos.capstone.backend.Repository.test.TestEatRepository;
import uos.capstone.backend.Repository.test.TestHotelRepository;

import java.util.List;

@Service
public class TestHotelServiceImpl implements TestService {

    @Autowired
    TestHotelRepository repository;

    @Override
    public Result retrieveList() {
        List<Hotel> list = repository.findAll();
        Result result = new Result();
        result.setPayload(list);
        return result;
    }
}
