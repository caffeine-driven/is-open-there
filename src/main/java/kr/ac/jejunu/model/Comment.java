package kr.ac.jejunu.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ghost9087 on 08/06/2017.
 */
@Entity
@Data
@Accessors(chain = true)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String text;
    private Date date;
    @JoinColumn(name = "writer_id")
    @ManyToOne
    private User writer;
    @JoinColumn(name = "restaurant_id")
    @ManyToOne
    private Restaurant restaurant;
}
