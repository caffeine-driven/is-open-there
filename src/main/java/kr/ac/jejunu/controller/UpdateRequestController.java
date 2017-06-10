package kr.ac.jejunu.controller;

import kr.ac.jejunu.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Map<String, Boolean> requestUpdate(@RequestBody Map<String, Integer> requestBody) {
        Integer restaurantId = requestBody.get("restaurant-id");
        restaurantService.requestStatusUpdate(restaurantId);

        Map<String, Boolean> resultMap = new HashMap<>();
        resultMap.put("result", true);

        return resultMap;
    }
}
