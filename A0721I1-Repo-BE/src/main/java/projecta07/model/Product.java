package projecta07.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private Long idProduct;

    @Column(name = "code_product")
    private String codeProduct;

    @Column(name = "name_product")
    private String nameProduct;

    @Column(name = "quatity_product")
    private Integer quatityProduct;

    @Column(name = "price_product")
    private Double priceProduct;

    @Column(name = "image_product")
    private String imageProduct;

    @Column(name = "description_product")
    private String descriptionProduct;
    @Column(name = "create_at")
    private Date createAt;


    @ManyToOne(targetEntity = TypeProduct.class)
    @JoinColumn(name = "id_type_product", nullable = false)

    private TypeProduct typeProduct;

    public Integer getQuatityProduct() {
        return quatityProduct;
    }

    public void setQuatityProduct(Integer quatityProduct) {
        this.quatityProduct = quatityProduct;
    }

    @OneToMany(mappedBy = "product")
    @JsonBackReference(value = "product_orderDetail")
    private List<OrderDetail> orderDetailList;

    public TypeProduct getTypeProduct() {
        return typeProduct;
    }

    public void setTypeProduct(TypeProduct typeProduct) {
        this.typeProduct = typeProduct;
    }

    public List<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    public Product() {
    }


    public Product(Long idProduct, String codeProduct, String nameProduct, Integer quatityProduct, Double priceProduct, String imageProduct, String descriptionProduct, Date createAt, TypeProduct typeProduct, List<OrderDetail> orderDetailList) {
        this.idProduct = idProduct;
        this.codeProduct = codeProduct;
        this.nameProduct = nameProduct;
        this.quatityProduct = quatityProduct;
        this.priceProduct = priceProduct;
        this.imageProduct = imageProduct;
        this.descriptionProduct = descriptionProduct;
        this.createAt = createAt;
        this.typeProduct = typeProduct;
        this.orderDetailList = orderDetailList;
    }


    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public String getCodeProduct() {
        return codeProduct;
    }

    public void setCodeProduct(String codeProduct) {
        this.codeProduct = codeProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public Double getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(Double priceProduct) {
        this.priceProduct = priceProduct;
    }

    public String getImageProduct() {
        return imageProduct;
    }

    public void setImageProduct(String imageProduct) {
        this.imageProduct = imageProduct;
    }

    public String getDescriptionProduct() {
        return descriptionProduct;
    }

    public void setDescriptionProduct(String descriptionProduct) {
        this.descriptionProduct = descriptionProduct;
    }
}
