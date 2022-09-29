package uos.capstone.backend.Controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import uos.capstone.backend.Model.Result;
import uos.capstone.backend.Service.test.TestEatServiceImpl;
import uos.capstone.backend.Service.test.TestHotelServiceImpl;
import uos.capstone.backend.Service.test.TestPlaceServiceImpl;
import uos.capstone.backend.Service.test.TestService;

@Controller
public class TestController {

    @Autowired
    TestEatServiceImpl eatService;

    @Autowired
    TestHotelServiceImpl hotelService;

    @Autowired
    TestPlaceServiceImpl placeService;

    @GetMapping("/test/eat")
    @ResponseBody
    public Result eatlist(){
        Result result = eatService.retrieveList();
        return result;
    }

    @GetMapping("/test/hotel")
    @ResponseBody
    public Result hotellist(){
        Result result = hotelService.retrieveList();
        return result;
    }

    @GetMapping("/test/place")
    @ResponseBody
    public Result placelist(){
        Result result = placeService.retrieveList();
        return result;
    }
}
