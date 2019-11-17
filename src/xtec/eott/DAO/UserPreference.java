package xtec.eott.DAO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.util.Objects;

@Entity
@IdClass(UserPreferencePK.class)
public class UserPreference {
    private int idUser;
    private int idPreference;

    @Id
    @Column(name = "idUser", nullable = false)
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Id
    @Column(name = "idPreference", nullable = false)
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
        UserPreference that = (UserPreference) o;
        return idUser == that.idUser &&
                idPreference == that.idPreference;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, idPreference);
    }
}
