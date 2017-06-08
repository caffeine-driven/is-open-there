package kr.ac.jejunu.service;

import kr.ac.jejunu.model.Comment;
import kr.ac.jejunu.model.Restaurant;
import kr.ac.jejunu.model.User;
import kr.ac.jejunu.repository.CommentRepository;
import kr.ac.jejunu.repository.RestaurantRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Mockito.*;

/**
 * Created by ghost9087 on 08/06/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class CommentServiceImplTest {
    @Configuration
    static class CommentServiceTestContextConfiguration {
        @Bean
        public CommentService commentService() {
            return new CommentServiceImpl();
        }

        @Bean
        public CommentRepository commentRepository() {
            return mock(CommentRepository.class);
        }

        @Bean
        public RestaurantRepository restaurantRepository() {
            return mock(RestaurantRepository.class);
        }
    }

    @Autowired
    private CommentService sut;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    private Restaurant mockRestaurant;

    private User mockUser;

    @Before
    public void setUp() throws Exception {
        mockRestaurant = mock(Restaurant.class);
        mockUser = mock(User.class);
        when(restaurantRepository.findOne(anyInt())).thenReturn(mockRestaurant);
    }

    @After
    public void tearDown() throws Exception {
        reset(
                commentRepository,
                restaurantRepository,
                mockRestaurant
        );
    }

    @Test
    public void testAddCommentForRestaurant() throws Exception {
        Integer mockRestaurantId = 1;
        when(mockRestaurant.getId()).thenReturn(mockRestaurantId);

        Comment comment = mock(Comment.class);

        sut.addCommentForRestaurant(comment, mockUser, mockRestaurant.getId());

        verify(restaurantRepository, times(1)).findOne(mockRestaurantId);
        verify(comment, times(1)).setRestaurant(mockRestaurant);
        verify(comment, times(1)).setWriter(mockUser);
        verify(commentRepository, times(1)).save(comment);
    }

    @Test
    public void testAddCommentForNonExistRestaurant() throws Exception {
        Integer mockRestaurantId = 999;
        when(restaurantRepository.findOne(mockRestaurantId)).thenReturn(null);
        when(mockRestaurant.getId()).thenReturn(mockRestaurantId);

        Comment comment = mock(Comment.class);

        sut.addCommentForRestaurant(comment, mockUser, mockRestaurant.getId());

        verify(restaurantRepository, times(1)).findOne(mockRestaurantId);
        verify(comment, times(0)).setRestaurant(mockRestaurant);
        verify(comment, times(0)).setWriter(mockUser);
        verify(commentRepository, times(0)).save(comment);
    }
}
