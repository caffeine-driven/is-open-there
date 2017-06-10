package kr.ac.jejunu.service;

import kr.ac.jejunu.exceptions.ObjectDuplicatedException;
import kr.ac.jejunu.exceptions.RestaurantNotExistException;
import kr.ac.jejunu.model.Restaurant;
import kr.ac.jejunu.model.UpdateRequestLog;
import kr.ac.jejunu.repository.RestaurantRepository;
import kr.ac.jejunu.repository.UpdateRequestLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by ghost9087 on 07/06/2017.
 */
@Service
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

        if (restaurant == null)
            throw new RestaurantNotExistException();

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
        try {
            restaurant.setOpen(false);
            return restaurantRepository.save(restaurant);
        } catch (DataIntegrityViolationException e) {
            throw new ObjectDuplicatedException("duplicated name on restaurant");
        }
    }

    @Override
    public Restaurant updateRestaurant(Restaurant restaurant) {
        Restaurant originalRestaurant = restaurantRepository.findOne(restaurant.getId());

        if (originalRestaurant == null)
            throw new RestaurantNotExistException();

        originalRestaurant.setName(
                restaurant.getName() == null ? originalRestaurant.getName() : restaurant.getName()
        );
        originalRestaurant.setStartTime(
                restaurant.getStartTime() == null ? originalRestaurant.getStartTime() : restaurant.getStartTime()
        );
        originalRestaurant.setEndTime(
                restaurant.getEndTime() == null ? originalRestaurant.getEndTime() : restaurant.getEndTime()
        );

        return restaurantRepository.save(originalRestaurant);
    }

    @Override
    public void deleteRestaurantById(Integer id) {
        restaurantRepository.delete(id);
    }
}
