package projecta07.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;


@Entity
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order_detail")
    private Long idOrderDetail;


    @Min(value = 1, message = "Số lượng tối thiểu là 1!")
    @Column(name = "number_product")
    private int numberProduct;

    @Column(name = "total_product")
    private Double totalProduct;

    @ManyToOne
    @JoinColumn(name = "id_order", nullable = false)
    private Order order;

    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name = "id_product", nullable = false)
    private Product product;

    public double calculateTotalPriceOrderDetail(OrderDetail orderDetail) {
        double totalProduct = orderDetail.getProduct().getPriceProduct() * orderDetail.getNumberProduct();
        return totalProduct;
    }

    public Double getTotalProduct() {
        return totalProduct;
    }

    public void setTotalProduct(Double totalProduct) {
        this.totalProduct = totalProduct;
    }

    public OrderDetail(Long idOrderDetail, int numberProduct, Double totalProduct, Order order, Product product) {
        this.idOrderDetail = idOrderDetail;
        this.numberProduct = numberProduct;
        this.totalProduct = totalProduct;
        this.order = order;
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public OrderDetail() {
    }

    public OrderDetail(Long idOrderDetail, int numberProduct) {
        this.idOrderDetail = idOrderDetail;
        this.numberProduct = numberProduct;
    }

    public OrderDetail(int numberProduct, Order order, Product product) {
        this.numberProduct = numberProduct;
        this.order = order;
        this.product = product;
    }

    public OrderDetail(Long idOrderDetail, int numberProduct, Order order, Product product) {
        this.idOrderDetail = idOrderDetail;
        this.numberProduct = numberProduct;
        this.order = order;
        this.product = product;
    }

    public Long getIdOrderDetail() {
        return idOrderDetail;
    }

    public void setIdOrderDetail(Long idOrderDetail) {
        this.idOrderDetail = idOrderDetail;
    }

    public int getNumberProduct() {
        return numberProduct;
    }

    public void setNumberProduct(int numberProduct) {
        this.numberProduct = numberProduct;
    }

}
