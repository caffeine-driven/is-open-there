package kr.ac.jejunu.service;

import kr.ac.jejunu.exceptions.RestaurantNotExistException;
import kr.ac.jejunu.model.Restaurant;

import java.util.List;

/**
 * Created by ghost9087 on 07/06/2017.
 */
public interface RestaurantService {
    void requestStatusUpdate(Integer id) throws RestaurantNotExistException;
    List<Restaurant> getRestaurantList();
    Restaurant getRestaurantById(Integer id);
    Restaurant addRestaurant(Restaurant restaurant);
    Restaurant updateRestaurant(Restaurant restaurant);
    void deleteRestaurantById(Integer id);
}
