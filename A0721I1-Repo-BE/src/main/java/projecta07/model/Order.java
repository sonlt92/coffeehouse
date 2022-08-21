package projecta07.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order")
    private Long idOrder;

    @Column(name = "date_order")
    private String dateOrder;

    @Column(name = "status_order")
    private Boolean statusOrder;

    @Column(name = "total_order")
    private Double totalOrder;

    @ManyToOne(targetEntity = Table.class)
    @JoinColumn(name = "id_table", nullable = false)
    private Table table;


//    @JsonIgnore
    @ManyToOne(targetEntity = Employee.class)
    @JoinColumn(name = "id_employee", nullable = false)
    private Employee employee;

//    @Column(name = "status")
//    private String status;

    @OneToMany(mappedBy = "order")
    @JsonBackReference(value = "order_ordeDetail")
    private List<OrderDetail> orderDetailList;

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    /* Calculate total price in order*/
    public double calculateTotalPriceInOrder(List<OrderDetail> orderDetails, Order order) {
        double totalPrice = 0;
        for (OrderDetail ord : orderDetails) {
            if (ord.getOrder().getIdOrder() == order.getIdOrder()) {
                totalPrice += ord.getTotalProduct();
            }
        }
        return totalPrice;
    }

    public Order() {
    }

    public Order(Long idOrder, LocalDate dateOrder, Double totalOrder) {

    }

    public Boolean getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(Boolean statusOrder) {
        this.statusOrder = statusOrder;
    }


    public boolean isStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(boolean statusOrder) {
        this.statusOrder = statusOrder;
    }

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public String getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(String dateOrder) {
        this.dateOrder = dateOrder;
    }

    public Double getTotalOrder() {
        return totalOrder;
    }

    public void setTotalOrder(Double totalOrder) {
        this.totalOrder = totalOrder;
    }
}
