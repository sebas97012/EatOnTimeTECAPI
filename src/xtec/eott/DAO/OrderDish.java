package xtec.eott.DAO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.util.Objects;

@Entity
@IdClass(OrderDishPK.class)
public class OrderDish extends DAO {
    private int idOrder;
    private int idDish;

    @Id
    @Column(name = "idOrder", nullable = false)
    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    @Id
    @Column(name = "idDish", nullable = false)
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
        OrderDish orderDish = (OrderDish) o;
        return idOrder == orderDish.idOrder &&
                idDish == orderDish.idDish;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrder, idDish);
    }
}
