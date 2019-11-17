package xtec.eott.DAO;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class User extends DAO{
    private int idUser;
    private String name;
    private String phone;
    private Province province;
    private String email;
    private Integer points = 0;

    public User() {
    }

    public User(int idUser, String name, String phone, Province province, String email) {
        this.idUser = idUser;
        this.name = name;
        this.phone = phone;
        this.province = province;
        this.email = email;
    }

    @Id
    @Column(name = "idUser", nullable = false)
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Basic
    @Column(name = "name", nullable = false, length = -1)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "phone", nullable = false, length = 45)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @ManyToOne
    @JoinColumn(name = "idProvince")
    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 45)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "points", nullable = false)
    public Integer getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return idUser == user.idUser &&
                province == user.province &&
                Objects.equals(name, user.name) &&
                Objects.equals(phone, user.phone) &&
                Objects.equals(email, user.email) &&
                Objects.equals(points, user.points);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, name, phone, province, email, points);
    }



}
