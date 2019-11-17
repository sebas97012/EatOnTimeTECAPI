package xtec.eott.DAO;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class UserPreferencePK implements Serializable {
    private int idUser;
    private int idPreference;

    @Column(name = "idUser", nullable = false)
    @Id
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Column(name = "idPreference", nullable = false)
    @Id
    public int getIdPreference() {
        return idPreference;
    }

    public void setIdPreference(int idPreference) {
        this.idPreference = idPreference;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPreferencePK that = (UserPreferencePK) o;
        return idUser == that.idUser &&
                idPreference == that.idPreference;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, idPreference);
    }
}
