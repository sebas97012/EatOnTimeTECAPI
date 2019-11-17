package xtec.eott.DAO;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class OrderDishPK implements Serializable {
    private int idOrder;
    private int idDish;

    @Column(name = "idOrder", nullable = false)
    @Id
    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    @Column(name = "idDish", nullable = false)
    @Id
    public int getIdDish() {
        return idDish;
    }

    public void setIdDish(int idDish) {
        this.idDish = idDish;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDishPK that = (OrderDishPK) o;
        return idOrder == that.idOrder &&
                idDish == that.idDish;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrder, idDish);
    }
}
