package kr.ac.jejunu.controller.api;

import kr.ac.jejunu.handler.FileHandler;
import kr.ac.jejunu.model.ActionResult;
import kr.ac.jejunu.model.Restaurant;
import kr.ac.jejunu.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by ghost9087 on 06/06/2017.
 */
@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private FileHandler fileHandler;

    @GetMapping
    public List<Restaurant> restaurantList(){
        return restaurantService.getRestaurantList();
    }

    @GetMapping("/{id}")
    public Restaurant restaurant(@PathVariable Integer id){
        return restaurantService.getRestaurantById(id);
    }

    @PostMapping
    public Restaurant addRestaurant(@ModelAttribute Restaurant restaurant, @RequestParam("image-file") MultipartFile file) throws IOException {
        String fileName = fileHandler.handleUploadedFile(file);

        restaurant.setImage("/" + fileName);

        return restaurantService.addRestaurant(restaurant);
    }

    @PutMapping("/{id}")
    public Restaurant updateRestaurant(@RequestBody Restaurant restaurant, @PathVariable Integer id) throws IOException {
        restaurant.setId(id);
        return restaurantService.updateRestaurant(restaurant);
    }

    @DeleteMapping("/{id}")
    public ActionResult deleteRestaurant(@PathVariable Integer id) {
        restaurantService.deleteRestaurantById(id);

        return new ActionResult(true);
    }
}
