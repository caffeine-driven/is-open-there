package kr.ac.jejunu.controller.web;

import kr.ac.jejunu.exceptions.RestaurantNotExistException;
import kr.ac.jejunu.handler.FileHandler;
import kr.ac.jejunu.model.Comment;
import kr.ac.jejunu.model.Restaurant;
import kr.ac.jejunu.service.CommentService;
import kr.ac.jejunu.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by ghost9087 on 12/06/2017.
 */
@Controller("/")
public class RestaurantWebController {
    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private FileHandler fileHandler;

    @RequestMapping(value = {"/", "restaurant"})
    public String index(Model model) {
        List<Restaurant> restaurantList = restaurantService.getRestaurantList();

        model.addAttribute("restaurantList", restaurantList);

        return "index";
    }

    @GetMapping("/restaurant/detail/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        Restaurant restaurant = restaurantService.getRestaurantById(id);

        if (restaurant == null)
            throw new RestaurantNotExistException();

        List<Comment> comments = commentService.getCommentOfRestaurant(id);

        model.addAttribute("restaurant", restaurant);
        model.addAttribute("comments", comments);

        return "detail";
    }

    @GetMapping("/restaurant/add")
    public String add() {
        return "add";
    }

    @PostMapping("/restaurant/add")
    public String addRestaurant(@ModelAttribute Restaurant restaurant, @RequestParam("image-file") MultipartFile file) throws IOException {
        String fileName = fileHandler.handleUploadedFile(file);

        restaurant.setImage("/" + fileName);

        Restaurant savedRestaurant = restaurantService.addRestaurant(restaurant);

        return "redirect:/restaurant/detail/" + savedRestaurant.getId();
    }

    @GetMapping("/restaurant/edit/{id}")
    public String editRestaurant(@PathVariable Integer id, Model model) {
        Restaurant restaurant = restaurantService.getRestaurantById(id);

        if (restaurant == null)
            throw new RestaurantNotExistException();

        model.addAttribute("restaurant", restaurant);
        return "edit";
    }

    @PostMapping("/restaurant/edit/{id}")
    public String editRestaurant(@PathVariable Integer id, @ModelAttribute Restaurant restaurant) throws IOException {
        restaurant.setId(id);

        Restaurant savedRestaurant = restaurantService.updateRestaurant(restaurant);

        return "redirect:/restaurant/detail/" + savedRestaurant.getId();
    }


    @ExceptionHandler(RestaurantNotExistException.class)
    public String onNotExistRestaurant() {
        return "error";
    }

    @PostMapping("/restaurant/update-request")
    public String requestUpdate(@RequestParam("restaurant-id") Integer restaurantId) {
        restaurantService.requestStatusUpdate(restaurantId);

        return "redirect:/restaurant/detail/" + restaurantId;
    }
}
