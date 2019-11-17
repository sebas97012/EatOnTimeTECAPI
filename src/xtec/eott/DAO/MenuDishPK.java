package xtec.eott.DAO;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class MenuDishPK implements Serializable {
    private int idMenu;
    private int idDish;

    @Column(name = "idMenu", nullable = false)
    @Id
    public int getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
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
        MenuDishPK that = (MenuDishPK) o;
        return idMenu == that.idMenu &&
                idDish == that.idDish;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMenu, idDish);
    }
}
