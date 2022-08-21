package projecta07.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import projecta07.dto.DetailOrderTableDTO;
import projecta07.dto.TableDTO;
import projecta07.model.Order;
import projecta07.model.OrderDetail;
import projecta07.model.Status;
import projecta07.service.IStatusService;
import projecta07.dto.TableUpdateDTO;
import projecta07.model.*;
import projecta07.service.*;
import projecta07.model.*;
import projecta07.service.*;
import projecta07.validate.ValidateTableDTO;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/manager")
@CrossOrigin(origins = "*")
public class TableController {

    @Autowired
    private IEmployeeService iEmployeeService;

    @Autowired
    private ValidateTableDTO validateTableDTO;

    @Autowired
    private IStatusService iStatusService;

    @Autowired
    private ITableService iTableService;

    @Autowired
    private IOrderService iOrderService;

    @Autowired
    private IOrderDetailService iOrderDetailService;

    /* BinTK */
    @RequestMapping(value = "/order/{idTable}" , method = RequestMethod.GET)
    public ResponseEntity<Order> getOrderById(@PathVariable Long idTable) {
        return new ResponseEntity<>(iOrderService.findOrderOfTableById(idTable) , HttpStatus.OK);
    }

    //BinTK
    @GetMapping("/emptyTable")
    public ResponseEntity<List<Table>> findAllEmptyTable() {
        List<Table> tables = iTableService.findAll();
        return new ResponseEntity<>(tables , HttpStatus.OK);
/*  Phương thức trả về emptyTable nếu có order ....
        List<Table> tables = iTableService.findAll();
        Order order = new Order();
        if (tables.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            for (Table table : tables) {
                order = iOrderService.findOrderOfTableById(table.getIdTable());
                if (order == null) {
                    table.setEmptyTable(true);
                    iTableService.save(table);
                    continue;
                }
                table.setEmptyTable(false);
                iTableService.save(table);
            }
            return new ResponseEntity<>(tables, HttpStatus.OK);
        }*/
    }


    /* BinTK */
    @PostMapping("/emptyTable/saveOrderInTable/{idUser}/{idTable}")
    public ResponseEntity<Order> saveOrderInTable(@PathVariable("idUser") Long idUser, @PathVariable("idTable") Long idTable) {
        Employee employee = iEmployeeService.findEmployeeByUser(idUser);
        Table table = iTableService.findTableById(idTable);
        table.setEmptyTable(false);
        iTableService.save(table);

        Order order = new Order();
        /* lấy order trả về sau khi lưu */
        Order ordered;

        /* Đặt giá trị cho order */
        order.setTable(table);
        order.setStatusOrder(false);
        order.setEmployee(employee);
        order.setDateOrder(String.valueOf((LocalDate.now())));
        ordered = iOrderService.saveOrder(order);

        return new ResponseEntity<>(ordered , HttpStatus.CREATED);
    }

    //BinTK
    @GetMapping("/emptyTable/detailTable/{id}")
    public ResponseEntity<List<DetailOrderTableDTO>> findAllOrderByTableId(@PathVariable Long id) {
        Order order = iOrderService.findOrderOfTableById(id);
        List<DetailOrderTableDTO> orderDetailDTOS = new ArrayList<>();
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            List<OrderDetail> orderDetails = iOrderDetailService.getOrderDetailByOrderId(order.getIdOrder());
            DetailOrderTableDTO detailOrderTableDTO;
            if (orderDetails.size() == 0) {
                detailOrderTableDTO = new DetailOrderTableDTO();
                detailOrderTableDTO.setCodeTable(order.getTable().getCodeTable());
                orderDetailDTOS.add(detailOrderTableDTO);
            } else {
                for (OrderDetail o : orderDetails) {
                    detailOrderTableDTO = new DetailOrderTableDTO();
                    detailOrderTableDTO.setNameProduct(o.getProduct().getNameProduct());
                    detailOrderTableDTO.setPriceProduct(o.getProduct().getPriceProduct());
                    detailOrderTableDTO.setNumberProduct(o.getNumberProduct());
                    detailOrderTableDTO.setTotalOrder(order.getTotalOrder());
                    detailOrderTableDTO.setCodeTable(order.getTable().getCodeTable());
                    orderDetailDTOS.add(detailOrderTableDTO);
                }
            }
            return new ResponseEntity<>(orderDetailDTOS, HttpStatus.OK);
        }
    }

    //BinTK
    @DeleteMapping("/emptyTable/deleteOrderInTable/{idTable}")
    public ResponseEntity<Order> deleteOrderInTable(@PathVariable("idTable") Long id) {
        /* Get table by table id */
        Table table = iTableService.findTableById(id);

        /* Set empty table is true */
        table.setEmptyTable(true);
        iTableService.save(table);

        /* Delete order */
        iOrderService.cancelTable(id);
        findAllEmptyTable();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // QuangNV method create Table
    @PostMapping("/createTable")
    public ResponseEntity<?> createTable(@Valid @RequestBody TableDTO tableDTO, BindingResult bindingResult) {
        Boolean check = true;
        validateTableDTO.validate(tableDTO, bindingResult);
        List<Table> tableList = iTableService.findAll();
        for (Integer i = 0; i < tableList.size(); i++) {
            if (tableList.get(i).getCodeTable().equals(tableDTO.getCodeTable())) {
                check = false;
                break;
            }
        }
        if (bindingResult.hasErrors() || !check) {
            if (bindingResult.hasErrors()) {
                return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.NOT_MODIFIED);
            } else {
                return new ResponseEntity<>("Da ton tai id", HttpStatus.NOT_MODIFIED);
            }
        } else {
            Table table = new Table();
            table.setStatus(tableDTO.getStatus());
            table.setCodeTable(tableDTO.getCodeTable());
            table.setEmptyTable(tableDTO.getEmptyTable());
            return new ResponseEntity<>(iTableService.save(table), HttpStatus.OK);
        }
    }

    //QuangNV method find all status
    @GetMapping("/findAllStatus")
    public ResponseEntity<List<Status>> findAllStatus() {
        List<Status> statusList = iStatusService.findAll();
        if (statusList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(statusList, HttpStatus.OK);
        }
    }

    //QuangNV method find all table
    @GetMapping("/findAllTable")
    public ResponseEntity<Iterable<Table>> findAllTable() {
        List<Table> tables = iTableService.findAll();
        if (tables.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tables, HttpStatus.OK);
    }

    // QuangNV
    @GetMapping("/checkId")
    public ResponseEntity<List<Table>> checkId(@RequestParam String id) {
        List<Table> list = iTableService.findAll();
        List<Table> tables = new ArrayList<>();
        for (Integer i = 0; i < list.size(); i++) {
            if (list.get(i).getCodeTable().equals(id)) {
                tables.add(list.get(i));
                return new ResponseEntity<>(tables, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //HuyNN method find all table with search and paging
    @GetMapping("/findAllTableWithSearchAndPaging")
    public ResponseEntity<Iterable<Table>> findAllTableWithSearch(@RequestParam(value = "codeTable", required = false) Optional<String> codeTable, @RequestParam(value = "idStatus", required = false) Optional<Long> idStatus, @RequestParam(value = "emptyTable", required = false) Optional<Boolean> emptyTable, @RequestParam(value = "pageNumber", required = false) Integer page, @PageableDefault(sort = "codeTable", value = 5) Pageable pageable) {
        Page<Table> tables;
        if (codeTable.isPresent()) {
            tables = iTableService.findAllByCodeTableContaining(codeTable.get(), pageable);
        } else if (emptyTable.isPresent() && idStatus.isPresent()) {
            Optional<Status> status = iStatusService.findStatusById(idStatus.get());
            tables = iTableService.findAllByEmptyTableAndStatus(emptyTable.get(), status.get(), pageable);
        } else if (emptyTable.isPresent()) {
            tables = iTableService.findAllByEmptyTable(emptyTable.get(), pageable);
        } else if (idStatus.isPresent()) {
            Optional<Status> status = iStatusService.findStatusById(idStatus.get());
            if (status == null) {
                tables = null;
            } else {
                tables = iTableService.findAllByStatus(status.get(), pageable);
            }
        } else {
            tables = iTableService.findAll(pageable);
        }
        if (tables.isEmpty()) {
            tables = null;
            return new ResponseEntity<>(tables, HttpStatus.OK);
        }
        return new ResponseEntity<>(tables, HttpStatus.OK);
    }

    //HuyNN method delete table
    @DeleteMapping("/deleteTable/{id}")
    public ResponseEntity<Table> deleteTableById(@PathVariable Long id) {
        Table table = iTableService.findTableById(id);
        if (table == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        iTableService.deleteTableById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // ThaoPTT method find table by Id
    @GetMapping("/findTableById/{id}")
    public ResponseEntity<Table> findTableById(@PathVariable("id") Long id) {
        Table tableOptional = iTableService.findTableById(id);
        if (tableOptional == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tableOptional, HttpStatus.OK);
    }

    // ThaoPTT method update table
    @PutMapping(value = "/updateTable/{id}")
    public ResponseEntity<Table> updateTable(@PathVariable("id") Long id, @RequestBody TableUpdateDTO tableUpdateDTO) {
        Table tableOptional = iTableService.findTableById(id);
        if (tableOptional == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        tableOptional.setStatus(tableUpdateDTO.getStatus());
        return new ResponseEntity<>(iTableService.save(tableOptional), HttpStatus.OK);
    }
}

