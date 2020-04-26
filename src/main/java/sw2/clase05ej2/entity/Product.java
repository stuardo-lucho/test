package sw2.clase05ej2.entity;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Entity
@Table(name="products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ProductID")
    private int id;
    @Column(nullable = false)
    @NotBlank
    @Size(max = 40,message = "El texto no puede ser mayor a 40 caracteres")
    private String productname;
    @ManyToOne
    @JoinColumn(name = "SupplierID")
    private Supplier supplier;
    @ManyToOne
    @JoinColumn(name = "CategoryID")
    private Category category;
    private String quantityperunit;

    @Digits(integer = 10, fraction = 4)
    @Positive
    private BigDecimal unitprice;

    @Digits(integer = 5,fraction = 0)
    @Min(value=0)
    @Max(value = 32767)
    private int unitsinstock;

    @Digits(integer = 5,fraction = 0)
    @Min(value=0)
    @Max(value = 32767)
    private int unitsonorder;


    private int reorderlevel;
    @Column(nullable = false)
    private boolean discontinued;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getQuantityperunit() {
        return quantityperunit;
    }

    public void setQuantityperunit(String quantityperunit) {
        this.quantityperunit = quantityperunit;
    }

    public BigDecimal getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(BigDecimal unitprice) {
        this.unitprice = unitprice;
    }

    public int getUnitsinstock() {
        return unitsinstock;
    }

    public void setUnitsinstock(int unitsinstock) {
        this.unitsinstock = unitsinstock;
    }

    public int getUnitsonorder() {
        return unitsonorder;
    }

    public void setUnitsonorder(int unitsonorder) {
        this.unitsonorder = unitsonorder;
    }

    public int getReorderlevel() {
        return reorderlevel;
    }

    public void setReorderlevel(int reorderlevel) {
        this.reorderlevel = reorderlevel;
    }

    public boolean isDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(boolean discontinued) {
        this.discontinued = discontinued;
    }
}
