package kr.ac.jejunu.repository;

import kr.ac.jejunu.model.UpdateRequestLog;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by ghost9087 on 06/06/2017.
 */
public interface UpdateRequestLogRepository extends CrudRepository<UpdateRequestLog, Integer>{
    List<UpdateRequestLog> findUpdateRequestLogsByRequestedDateIsAfterAndRestaurantId(Date date, Integer id);
    List<UpdateRequestLog> findUpdateRequestLogsByRequestedDateIsAfterAndRestaurantIdAndCurrentStatus(Date date, Integer id, Boolean status);
    List<UpdateRequestLog> findUpdateRequestLogsByRestaurantIdAndCurrentStatus(Integer id, Boolean status);
}
