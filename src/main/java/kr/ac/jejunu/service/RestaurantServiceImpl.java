package kr.ac.jejunu.service;

import kr.ac.jejunu.model.Restaurant;
import kr.ac.jejunu.model.UpdateRequestLog;
import kr.ac.jejunu.repository.RestaurantRepository;
import kr.ac.jejunu.repository.UpdateRequestLogRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * Created by ghost9087 on 07/06/2017.
 */
public class RestaurantServiceImpl implements RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private UpdateRequestLogRepository updateRequestLogRepository;
    //설정에서 값을 주입해보자
    private Integer requiredForToggle = 3;

    @Override
    public void requestStatusUpdate(Integer restaurantId) {
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

    @Override
    public List<Restaurant> getRestaurantList() {
        return (List<Restaurant>) restaurantRepository.findAll();
    }

    @Override
    public Restaurant getRestaurantById(Integer id) {
        return restaurantRepository.findOne(id);
    }

    @Override
    public Restaurant addRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteRestaurantById(Integer id) {
        restaurantRepository.delete(id);
    }
}
