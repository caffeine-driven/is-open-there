package kr.ac.jejunu.model;

import lombok.Data;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Time;
import java.util.Calendar;

/**
 * Created by ghost9087 on 06/06/2017.
 */
@Entity
@Table(name = "restaurant")
@Data
@Accessors(chain = true)
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Boolean open;
    private String image;

    @DateTimeFormat(pattern = "HH:mm")
    private Calendar startTime;
    @DateTimeFormat(pattern = "HH:mm")
    private Calendar endTime;

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", open=" + open +
                ", image='" + image + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }

    public Boolean isOpen(){
        return open;
    }
}