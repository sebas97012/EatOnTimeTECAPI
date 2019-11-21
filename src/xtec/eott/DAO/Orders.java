package xtec.eott.DAO;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Orders extends DAO {
    private int idOrder;
    private int idClient;
    private int idCook;
    private Timestamp date;
    private Integer rating;
    private String observation;

    @Id
    @Column(name = "idOrder", nullable = false)
    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    @Basic
    @Column(name = "idClient", nullable = false)
    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    @Basic
    @Column(name = "idCook", nullable = false)
    public int getIdCook() {
        return idCook;
    }

    public void setIdCook(int idCook) {
        this.idCook = idCook;
    }

    @Basic
    @Column(name = "date", nullable = false)
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Basic
    @Column(name = "rating", nullable = true)
    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Basic
    @Column(name = "observation", nullable = true, length = -1)
    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orders orders = (Orders) o;
        return idOrder == orders.idOrder &&
                idClient == orders.idClient &&
                idCook == orders.idCook &&
                Objects.equals(date, orders.date) &&
                Objects.equals(rating, orders.rating) &&
                Objects.equals(observation, orders.observation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrder, idClient, idCook, date, rating, observation);
    }
}
