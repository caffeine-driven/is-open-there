package kr.ac.jejunu.controller;

import kr.ac.jejunu.model.Restaurant;
import kr.ac.jejunu.model.UpdateRequestLog;
import kr.ac.jejunu.repository.RestaurantRepository;
import kr.ac.jejunu.repository.UpdateRequestLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;


/**
 * Created by ghost9087 on 06/06/2017.
 */
@Controller
@RequestMapping("/update-request")
public class UpdateRequestController {
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private UpdateRequestLogRepository updateRequestLogRepository;
    //설정에서 값을 주입해보자
    private Integer requiredForToggle = 3;


    @PostMapping
    public void requestUpdate(@RequestParam("restaurant-id") Integer restaurantId, Model model){
        Date currentTime = new Date();

        Restaurant restaurant = restaurantRepository.findOne(restaurantId);

        List<UpdateRequestLog> logs = updateRequestLogRepository.findUpdateRequestLogsByRestaurantIdAndCurrentStatus(restaurantId, restaurant.isOpen());

        UpdateRequestLog log = new UpdateRequestLog();
        log.setCurrentStatus(restaurant.isOpen());
        log.setRestaurant(restaurant);
        log.setRequestedDate(currentTime);
        updateRequestLogRepository.save(log);
        logs.add(log);

        if (logs.size() > requiredForToggle){
            restaurant.setOpen(!restaurant.isOpen());
            restaurantRepository.save(restaurant);
        }
    }
}
