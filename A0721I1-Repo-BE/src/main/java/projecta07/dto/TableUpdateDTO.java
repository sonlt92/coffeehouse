package projecta07.dto;

import projecta07.model.Status;

public class TableUpdateDTO {
    private Long idTable;
    private String codeTable;
    private Boolean emptyTable;
    private Status status;
    public TableUpdateDTO() {
    }

    public TableUpdateDTO(Long idTable, String codeTable, Boolean emptyTable, Status status) {
        this.idTable = idTable;
        this.codeTable = codeTable;
        this.emptyTable = emptyTable;
        this.status = status;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
