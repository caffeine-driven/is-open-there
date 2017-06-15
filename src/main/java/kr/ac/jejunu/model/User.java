package kr.ac.jejunu.model;
import javax.persistence.*;

/**
 * Created by ghost9087 on 06/06/2017.
 */
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String password;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        if (name == null)
            return "";
        else
            return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        if (password == null)
            return "";
        else
            return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
