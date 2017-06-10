package kr.ac.jejunu.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

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

    private String startTime;
    private String endTime;

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