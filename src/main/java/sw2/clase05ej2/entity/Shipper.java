package sw2.clase05ej2.entity;

import javax.persistence.*;

@Entity
@Table(name = "shippers")
public class Shipper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shipperid")
    private int shipperId;
    @Column(name = "companyname", nullable = false)
    private String companyName;
    private String phone;

    public int getShipperId() {
        return shipperId;
    }

    public void setShipperId(int shipperId) {
        this.shipperId = shipperId;
    }

    public String getCompanyname() {
        return companyName;
    }

    public void setCompanyname(String companyname) {
        this.companyName = companyname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
