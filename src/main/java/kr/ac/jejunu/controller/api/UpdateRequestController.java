package kr.ac.jejunu.controller.api;

import kr.ac.jejunu.model.ActionResult;
import kr.ac.jejunu.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


/**
 * Created by ghost9087 on 06/06/2017.
 */
@RestController
@RequestMapping("/api/update-request")
public class UpdateRequestController {
    @Autowired
    private RestaurantService restaurantService;

    @PostMapping
    public ActionResult requestUpdate(@RequestBody Map<String, Integer> requestBody) {
        Integer restaurantId = requestBody.get("restaurant-id");
        restaurantService.requestStatusUpdate(restaurantId);

        return new ActionResult(true);
    }
}
