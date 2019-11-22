package xtec.eott.DAO;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class OrderStatus extends DAO {
    private int StatusId;
    private int IdOrder;
    private int OrderStatus = 0;

    public OrderStatus() { }

    @Id
    @Column(name = "StatusId", nullable = false)
    public int getStatusId() {
        return StatusId;
    }

    public void setStatusId(int statusId) {
        StatusId = statusId;
    }

    @Basic
    @Column(name = "IdOrder", nullable = false)
    public int getIdOrder() {
        return IdOrder;
    }

    public void setIdOrder(int idOrder) {
        this.IdOrder = idOrder;
    }

    @Basic
    @Column(name = "OrderStatus", nullable = false)
    public int getOrderStatus() {
        return OrderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        OrderStatus = orderStatus;
    }
}
