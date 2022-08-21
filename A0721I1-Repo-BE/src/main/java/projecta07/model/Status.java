package projecta07.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_status")
    @NotEmpty(message = "test")
    private Long idStatus;

    @Column(name = "name_status")
    private String nameStatus;

    @OneToMany(mappedBy = "status")
    @JsonBackReference(value = "status_table")
    private List<Table> tableList;

    public Status() {
    }

    public Status(Long idStatus, String nameStatus) {
        this.idStatus = idStatus;
        this.nameStatus = nameStatus;
    }

    public List<Table> getTableList() {
        return tableList;
    }

    public void setTableList(List<Table> tableList) {
        this.tableList = tableList;
    }

    public Long getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Long idStatus) {
        this.idStatus = idStatus;
    }

    public String getNameStatus() {
        return nameStatus;
    }

    public void setNameStatus(String nameStatus) {
        this.nameStatus = nameStatus;
    }
}
