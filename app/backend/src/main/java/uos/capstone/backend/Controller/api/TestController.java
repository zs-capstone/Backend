package uos.capstone.backend.Controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import uos.capstone.backend.Model.Result;
import uos.capstone.backend.Repository.place.TestEatRepository;
import uos.capstone.backend.Service.place.TestEatService;

@Controller
public class TestController {

    @Autowired
    TestEatService eatService;

    @GetMapping("/test")
    @ResponseBody
    public Result list(){
        Result result = eatService.retrieveEatList();
        return result;
    }
}
