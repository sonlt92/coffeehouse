package projecta07.service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import projecta07.model.Status;
import projecta07.model.Table;

import java.util.List;
public interface ITableService {
    List<Table> findAll();
    void deleteTableById(Long id);
    Table findTableById(Long id);
    Table save(Table table);

    //HuyNN search method
    Page<Table> findAll(Pageable pageable);

    Page<Table> findAllByCodeTableContaining(String codeTable, Pageable pageable);

    Page<Table> findAllByEmptyTable(Boolean emptyTable, Pageable pageable);

    Page<Table> findAllByStatus(Status status, Pageable pageable);

    Page<Table> findAllByEmptyTableAndStatus(Boolean emptyTable, Status status, Pageable pageable);
}
