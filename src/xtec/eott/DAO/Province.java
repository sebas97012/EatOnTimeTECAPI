package xtec.eott.DAO;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Province {
    private int idProvince;
    private String provinceName;

    public Province() {
    }

    public Province(int idProvince) {
        this.idProvince = idProvince;
    }

    @Id
    @Column(name = "idProvince", nullable = false)
    public int getIdProvince() {
        return idProvince;
    }

    public void setIdProvince(int idProvince) {
        this.idProvince = idProvince;
    }

    @Basic
    @Column(name = "provinceName", nullable = true, length = 45)
    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Province province = (Province) o;
        return idProvince == province.idProvince &&
                Objects.equals(provinceName, province.provinceName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProvince, provinceName);
    }
}
