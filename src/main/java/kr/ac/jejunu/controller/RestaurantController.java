package kr.ac.jejunu.controller;

import kr.ac.jejunu.model.Restaurant;
import kr.ac.jejunu.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

/**
 * Created by ghost9087 on 06/06/2017.
 */
@Controller
@RequestMapping("/restaurant")
public class RestaurantController {
    @Autowired
    private RestaurantRepository restaurantRepository;

    @GetMapping
    public void restaurantList(Model model){
        List<Restaurant> restaurantList = (List<Restaurant>) restaurantRepository.findAll();

        model.addAttribute("restaurants", restaurantList);
    }

    @GetMapping("/{id}")
    public void restaurant(@PathVariable Integer id, Model model){
        Restaurant restaurant = restaurantRepository.findOne(id);
        model.addAttribute("restaurant", restaurant);
    }

    @PostMapping
    public void addRestaurant(@ModelAttribute Restaurant restaurant, @RequestParam("image-file") MultipartFile file, Model model) throws IOException {
        saveRestaurant(restaurant, file, model);
    }
    @PutMapping
    public void updateRestaurant(@ModelAttribute Restaurant restaurant, Model model) throws IOException {
//        restaurant.setId(id);
        System.out.println(restaurant);

//        saveRestaurant(restaurant, file, model);
    }

    @DeleteMapping("/{id}")
    public void deleteRestaurant(@PathVariable Integer id, Model model){
        restaurantRepository.delete(id);
        model.addAttribute("deleted", true);
    }

    private void saveRestaurant(Restaurant restaurant, MultipartFile file, Model model) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(new File("src/main/resources/static/" + file.getOriginalFilename()));
        BufferedOutputStream outputStream = new BufferedOutputStream(fileOutputStream);
        outputStream.write(file.getBytes());
        outputStream.close();

        restaurant.setImage("/"+file.getOriginalFilename());

        restaurantRepository.save(restaurant);

        model.addAttribute("restaurant", restaurant);
    }
}
