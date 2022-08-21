package projecta07.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import projecta07.model.Status;
import projecta07.model.Table;

import java.util.List;

@Repository
public interface ITableRepository extends JpaRepository<Table,Long> {
    @Query(value = "select * from `table` where `table`.id_table = ?1" , nativeQuery = true)
    Table findTableById(Long id);

    // HuyNN searh and paging method
    Page<Table> findAll(Pageable pageable);

    Page<Table> findAllByCodeTableContaining(String codeTable, Pageable pageable);

    Page<Table> findAllByEmptyTable(Boolean emptyTable, Pageable pageable);

    Page<Table> findAllByStatus(Status status, Pageable pageable);

    Page<Table> findAllByEmptyTableAndStatus(Boolean emptyTable, Status status, Pageable pageable);

    @Query(value = "select id_table, code_table, empty_table, id_status from `Table` where id_status = :idStatus", nativeQuery = true)
    List<Table> findAllByStatus(Long idStatus);

    @Query(value = "select id_table, code_table, empty_table, id_status from `Table` where empty_table  = :emptyTable", nativeQuery = true)
    List<Table> findAllByEmptyTable(Boolean emptyTable);
}
