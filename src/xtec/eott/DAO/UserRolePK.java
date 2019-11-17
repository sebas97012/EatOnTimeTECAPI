package xtec.eott.DAO;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class UserRolePK implements Serializable {
    private int idUser;
    private int idRole;

    @Column(name = "idUser", nullable = false)
    @Id
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Column(name = "idRole", nullable = false)
    @Id
    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRolePK that = (UserRolePK) o;
        return idUser == that.idUser &&
                idRole == that.idRole;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, idRole);
    }
}
