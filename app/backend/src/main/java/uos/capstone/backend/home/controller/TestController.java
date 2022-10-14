package uos.capstone.backend.home.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import uos.capstone.backend.common.domain.Result;
import uos.capstone.backend.Service.test.TestEatServiceImpl;
import uos.capstone.backend.Service.test.TestHotelServiceImpl;
import uos.capstone.backend.Service.test.TestPlaceServiceImpl;

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
    public Result testEat(){
//        System.out.println("accessToken 값: "+accessToken);
        Result result = eatService.retrieveList();
        return result;
    }

    @GetMapping("/test/hotel")
    @ResponseBody
    public Result testHotel(){
        Result result = hotelService.retrieveList();
        return result;
    }

    @GetMapping("/test/place")
    @ResponseBody
    public Result testPlace(){
        Result result = placeService.retrieveList();
        return result;
    }
}
