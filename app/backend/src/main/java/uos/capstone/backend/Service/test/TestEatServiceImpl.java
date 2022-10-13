package uos.capstone.backend.Service.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uos.capstone.backend.Model.Eat;
import uos.capstone.backend.common.domain.Result;
import uos.capstone.backend.Repository.test.TestEatRepository;

import java.util.List;

@Service
public class TestEatServiceImpl implements TestService {

    @Autowired
    TestEatRepository repository;

    @Override
    public Result retrieveList() {
        List<Eat> list = repository.findAll();
        Result result = new Result();
        result.setPayload(list);
        return result;
    }
}
