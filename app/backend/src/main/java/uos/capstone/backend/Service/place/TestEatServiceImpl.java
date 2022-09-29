package uos.capstone.backend.Service.place;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uos.capstone.backend.Model.Eat;
import uos.capstone.backend.Model.Result;
import uos.capstone.backend.Repository.place.TestEatRepository;

import java.util.List;

@Service
public class TestEatServiceImpl implements TestEatService {

    @Autowired
    TestEatRepository repository;

    @Override
    public Result retrieveEatList() {
        List<Eat> list = repository.findAll();
        Result result = new Result();
        result.setPayload(list);
        return result;
    }
}
