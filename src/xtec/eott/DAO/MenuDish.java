package xtec.eott.DAO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.util.Objects;

@Entity
@IdClass(MenuDishPK.class)
public class MenuDish extends DAO{
    private int idMenu;
    private int idDish;

    @Id
    @Column(name = "idMenu", nullable = false)
    public int getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
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
        MenuDish menuDish = (MenuDish) o;
        return idMenu == menuDish.idMenu &&
                idDish == menuDish.idDish;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMenu, idDish);
    }
}
