package kr.ac.jejunu.controller;

import kr.ac.jejunu.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by ghost9087 on 06/06/2017.
 */
@RestController
@RequestMapping("/update-request")
public class UpdateRequestController {
    @Autowired
    private RestaurantService restaurantService;

    @PostMapping
    @ResponseBody
    public Map<String, Boolean> requestUpdate(@RequestParam("restaurant-id") Integer restaurantId){
        restaurantService.requestStatusUpdate(restaurantId);

        Map<String, Boolean> resultMap = new HashMap<>();
        resultMap.put("result", true);

        return resultMap;
    }
}
