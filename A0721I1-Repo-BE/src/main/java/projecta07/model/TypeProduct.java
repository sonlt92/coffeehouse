package projecta07.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import java.util.List;

@Entity
public class TypeProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_type_product")

    private Long idTypeProduct;

    @Column(name = "code_type_product")

    private String codeTypeProduct;

    @Column(name = "name_type_product")

    private String nameTypeProduct;

    @OneToMany(mappedBy = "typeProduct")
    @JsonBackReference(value = "typeProduct_product")
    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public TypeProduct() {
    }

    public TypeProduct(Long idTypeProduct, String codeTypeProduct, String nameTypeProduct) {
        this.idTypeProduct = idTypeProduct;
        this.codeTypeProduct = codeTypeProduct;
        this.nameTypeProduct = nameTypeProduct;
    }

    public Long getIdTypeProduct() {
        return idTypeProduct;
    }

    public void setIdTypeProduct(Long idTypeProduct) {
        this.idTypeProduct = idTypeProduct;
    }

    public String getCodeTypeProduct() {
        return codeTypeProduct;
    }

    public void setCodeTypeProduct(String codeTypeProduct) {
        this.codeTypeProduct = codeTypeProduct;
    }

    public String getNameTypeProduct() {
        return nameTypeProduct;
    }

    public void setNameTypeProduct(String nameTypeProduct) {
        this.nameTypeProduct = nameTypeProduct;
    }
}
