package xtec.eott.DAO;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Dish extends DAO{
    private int idDish;
    private String name;
    private Integer points;
    private String ingredients;
    private Integer calories;
    private Double price;
    private Integer preference;

    @Id
    @Column(name = "idDish", nullable = false)
    public int getIdDish() {
        return idDish;
    }

    public void setIdDish(int idDish) {
        this.idDish = idDish;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "points", nullable = true)
    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    @Basic
    @Column(name = "ingredients", nullable = true, length = -1)
    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    @Basic
    @Column(name = "calories", nullable = true)
    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    @Basic
    @Column(name = "price", nullable = true, precision = 0)
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "preference", nullable = true)
    public Integer getPreference() {
        return preference;
    }

    public void setPreference(Integer preference) {
        this.preference = preference;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return idDish == dish.idDish &&
                Objects.equals(name, dish.name) &&
                Objects.equals(points, dish.points) &&
                Objects.equals(ingredients, dish.ingredients) &&
                Objects.equals(calories, dish.calories) &&
                Objects.equals(price, dish.price) &&
                Objects.equals(preference, dish.preference);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDish, name, points, ingredients, calories, price, preference);
    }
}
