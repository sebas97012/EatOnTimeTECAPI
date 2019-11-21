package xtec.eott.DAO;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Preference extends DAO {
    private int idPreference;
    private String preference;

    @Id
    @Column(name = "idPreference", nullable = false)
    public int getIdPreference() {
        return idPreference;
    }

    public void setIdPreference(int idPreference) {
        this.idPreference = idPreference;
    }

    @Basic
    @Column(name = "preference", nullable = false, length = 45)
    public String getPreference() {
        return preference;
    }

    public void setPreference(String preference) {
        this.preference = preference;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Preference that = (Preference) o;
        return idPreference == that.idPreference &&
                Objects.equals(preference, that.preference);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPreference, preference);
    }
}
