package projecta07.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class Table {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_table")
    private Long idTable;

    @Column(name = "code_table")
    private String codeTable;

    @Column(name = "empty_table")
    private Boolean emptyTable;

    @OneToMany(mappedBy = "table")
    @JsonBackReference(value = "table_order")
    private List<Order> orderList;

    @ManyToOne(targetEntity = Status.class)
    @JoinColumn(name = "id_status", nullable = false)
    private Status status;

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public Status getStatus() {
        return status;
    }



    public Table() {
    }

    public Table(Long idTable, String codeTable, Boolean emptyTable) {
        this.idTable = idTable;
        this.codeTable = codeTable;
        this.emptyTable = emptyTable;
    }

    public Long getIdTable() {
        return idTable;
    }

    public void setIdTable(Long idTable) {
        this.idTable = idTable;
    }

    public String getCodeTable() {
        return codeTable;
    }

    public void setCodeTable(String codeTable) {
        this.codeTable = codeTable;
    }

    public Boolean getEmptyTable() {
        return emptyTable;
    }

    public void setEmptyTable(Boolean emptyTable) {
        this.emptyTable = emptyTable;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
