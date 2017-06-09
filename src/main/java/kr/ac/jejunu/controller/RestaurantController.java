package kr.ac.jejunu.controller;

import kr.ac.jejunu.model.Restaurant;
import kr.ac.jejunu.repository.RestaurantRepository;
import kr.ac.jejunu.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ghost9087 on 06/06/2017.
 */
@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @GetMapping
    @ResponseBody
    public List<Restaurant> restaurantList(){
        return restaurantService.getRestaurantList();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Restaurant restaurant(@PathVariable Integer id){
        return restaurantService.getRestaurantById(id);
    }

    @PostMapping
    @ResponseBody
    public Restaurant addRestaurant(@ModelAttribute Restaurant restaurant, @RequestParam("image-file") MultipartFile file) throws IOException {
        //TODO: 이거 어디로 뺄것
        FileOutputStream fileOutputStream = new FileOutputStream(new File("src/main/resources/static/" + file.getOriginalFilename()));
        BufferedOutputStream outputStream = new BufferedOutputStream(fileOutputStream);
        outputStream.write(file.getBytes());
        outputStream.close();

        restaurant.setImage("/"+file.getOriginalFilename());

        return restaurantService.addRestaurant(restaurant);
    }
    @PutMapping("/{id}")
    @ResponseBody
    public Restaurant updateRestaurant(@ModelAttribute Restaurant restaurant, @PathVariable Integer id) throws IOException {
        restaurant.setId(id);
        return restaurantService.updateRestaurant(restaurant);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public Map<String, Boolean> deleteRestaurant(@PathVariable Integer id){
        restaurantService.deleteRestaurantById(id);

        Map<String, Boolean> resultMap = new HashMap<>();
        resultMap.put("result", true);

        return resultMap;
    }
}
