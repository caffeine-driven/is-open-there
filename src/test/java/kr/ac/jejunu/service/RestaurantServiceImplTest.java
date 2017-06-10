package kr.ac.jejunu.service;

import kr.ac.jejunu.exceptions.ObjectDuplicatedException;
import kr.ac.jejunu.exceptions.RestaurantNotExistException;
import kr.ac.jejunu.model.Restaurant;
import kr.ac.jejunu.model.UpdateRequestLog;
import kr.ac.jejunu.repository.RestaurantRepository;
import kr.ac.jejunu.repository.UpdateRequestLogRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;

/**
 * Created by ghost9087 on 07/06/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class RestaurantServiceImplTest {
    @Configuration
    static class RestaurantServiceTestContextConfiguration {
        @Bean
        public RestaurantService restaurantService() {
            return new RestaurantServiceImpl();
        }

        @Bean
        public RestaurantRepository restaurantRepository() {
            return mock(RestaurantRepository.class);
        }

        @Bean
        public UpdateRequestLogRepository updateRequestLogRepository() {
            return mock(UpdateRequestLogRepository.class);
        }
    }

    @Autowired
    private RestaurantService sut;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private UpdateRequestLogRepository updateRequestLogRepository;

    private List<UpdateRequestLog> mockList;

    private Restaurant testRestaurant;

    @Before
    public void setUp() throws Exception {
        mockList = mock(List.class);
        testRestaurant = mock(Restaurant.class);
        when(restaurantRepository.findOne(anyInt())).thenReturn(testRestaurant);
        when(updateRequestLogRepository.findUpdateRequestLogsByRestaurantIdAndCurrentStatus(anyInt(), anyBoolean())).thenReturn(mockList);
        when(testRestaurant.getId()).thenReturn(1);
        when(testRestaurant.isOpen()).thenReturn(false);
    }

    @Test(expected = RestaurantNotExistException.class)
    public void testUpdateRequestForRestaurantNonExist() throws Exception {
        reset(restaurantRepository);
        when(restaurantRepository.findOne(anyInt())).thenReturn(null);

        sut.requestStatusUpdate(testRestaurant.getId());
    }

    @Test
    public void testUpdateRequestLessThanRequired() throws Exception {
        when(mockList.size()).thenReturn(1);

        sut.requestStatusUpdate(testRestaurant.getId());

        verify(testRestaurant, times(0)).setOpen(anyBoolean());
        verify(restaurantRepository, times(0)).save(any(Restaurant.class));
        verify(updateRequestLogRepository, times(1)).findUpdateRequestLogsByRestaurantIdAndCurrentStatus(testRestaurant.getId(), testRestaurant.isOpen());
        verify(updateRequestLogRepository, times(1)).save(any(UpdateRequestLog.class));
    }

    @Test
    public void testUpdateRequestEqualToRequired() throws Exception {
        when(mockList.size()).thenReturn(4);

        sut.requestStatusUpdate(testRestaurant.getId());

        verify(testRestaurant, times(1)).setOpen(true);
        verify(restaurantRepository, times(1)).save(any(Restaurant.class));
        verify(updateRequestLogRepository, times(1)).findUpdateRequestLogsByRestaurantIdAndCurrentStatus(testRestaurant.getId(), testRestaurant.isOpen());
        verify(updateRequestLogRepository, times(1)).save(any(UpdateRequestLog.class));
    }

    @Test
    public void testUpdate() throws Exception {
        Integer restaurantId = 1;
        Restaurant restaurant = new Restaurant();
        restaurant.setOpen(false);
        restaurant.setId(restaurantId);
        restaurant.setImage("/static/test/test-img.jpg");
        restaurant.setName("test restaurant");
        restaurant.setStartTime("09:30");
        restaurant.setEndTime("18:30");

        Restaurant restaurantForUpdate = new Restaurant();
        restaurantForUpdate.setId(restaurantId);
        restaurantForUpdate.setName("blahblah");

        when(restaurantRepository.findOne(restaurantId)).thenReturn(restaurant);
        when(restaurantRepository.save(any(Restaurant.class))).then(invocation -> invocation.getArgument(0));

        Restaurant updatedRestaurant = sut.updateRestaurant(restaurantForUpdate);

        assertThat(updatedRestaurant.getId(), is(restaurantId));
        assertThat(updatedRestaurant.getName(), is(restaurantForUpdate.getName()));
        assertThat(updatedRestaurant.isOpen(), is(restaurant.isOpen()));
        assertThat(updatedRestaurant.getImage(), is(restaurant.getImage()));
        assertThat(updatedRestaurant.getStartTime(), is(restaurant.getStartTime()));
        assertThat(updatedRestaurant.getEndTime(), is(restaurant.getEndTime()));
    }

    @Test(expected = ObjectDuplicatedException.class)
    public void testAddRestaurantWithDuplicatedName() throws Exception {
        when(restaurantRepository.save(any(Restaurant.class))).thenThrow(DataIntegrityViolationException.class);
        Restaurant mockRestaurant = mock(Restaurant.class);

        sut.addRestaurant(mockRestaurant);
    }

    @Test(expected = RestaurantNotExistException.class)
    public void testUpdateNotExistRestaurant() throws Exception {
        Restaurant mockRestaurant = mock(Restaurant.class);
        Integer restaurantId = 1;
        when(mockRestaurant.getId()).thenReturn(restaurantId);
        when(restaurantRepository.findOne(restaurantId)).thenReturn(null);

        sut.updateRestaurant(mockRestaurant);
    }

    @After
    public void tearDown() throws Exception {
        reset(
                restaurantRepository,
                updateRequestLogRepository,
                mockList,
                testRestaurant
        );
    }
}
