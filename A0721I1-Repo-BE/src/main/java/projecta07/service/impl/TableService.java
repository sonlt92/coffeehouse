package projecta07.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import projecta07.model.Status;
import projecta07.model.Table;
import projecta07.repository.ITableRepository;
import projecta07.service.ITableService;

import java.util.List;

@Service
public class TableService implements ITableService {
    @Autowired
    private ITableRepository tableRepository;

    @Autowired
    private ITableRepository iTableRepository;

    public Table saveTable(Table table) {
        return this.tableRepository.save(table);
    }

    public Table getTableById(Long id) {
        return tableRepository.findById(id).orElse(null);
    }

    @Override
    public Table save(Table table) {
        return tableRepository.save(table);
    }

    @Override
    public List<Table> findAll() {
        return tableRepository.findAll();
    }

    @Override
    public void deleteTableById(Long id) {
        tableRepository.deleteById(id);
    }

    @Override
    public Table findTableById(Long id) {
        return tableRepository.findTableById(id);
    }

    @Override
    public Page<Table> findAll(Pageable pageable) {
        return iTableRepository.findAll(pageable);
    }

    @Override
    public Page<Table> findAllByCodeTableContaining(String codeTable, Pageable pageable) {
        return iTableRepository.findAllByCodeTableContaining(codeTable, pageable);
    }

    @Override
    public Page<Table> findAllByEmptyTable(Boolean emptyTable, Pageable pageable) {
        return iTableRepository.findAllByEmptyTable(emptyTable, pageable);
    }

    @Override
    public Page<Table> findAllByStatus(Status status, Pageable pageable) {
        return iTableRepository.findAllByStatus(status, pageable);
    }

    @Override
    public Page<Table> findAllByEmptyTableAndStatus(Boolean emptyTable, Status status, Pageable pageable) {
        return iTableRepository.findAllByEmptyTableAndStatus(emptyTable, status, pageable);
    }
}
