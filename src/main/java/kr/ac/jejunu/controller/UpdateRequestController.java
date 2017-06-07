package kr.ac.jejunu.controller;

import kr.ac.jejunu.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Created by ghost9087 on 06/06/2017.
 */
@Controller
@RequestMapping("/update-request")
public class UpdateRequestController {
    @Autowired
    private RestaurantService restaurantService;

    @PostMapping
    public void requestUpdate(@RequestParam("restaurant-id") Integer restaurantId, Model model){
        restaurantService.requestStatusUpdate(restaurantId);

        model.addAttribute("result", true);
    }
}
