package xtec.eott.DAO;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Menu {
    private int idMenu;
    private Timestamp timestamp;

    @Id
    @Column(name = "idMenu", nullable = false)
    public int getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }

    @Basic
    @Column(name = "timestamp", nullable = false)
    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return idMenu == menu.idMenu &&
                Objects.equals(timestamp, menu.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMenu, timestamp);
    }
}
