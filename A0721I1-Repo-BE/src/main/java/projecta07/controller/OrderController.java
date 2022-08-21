package projecta07.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projecta07.model.Order;
import projecta07.model.OrderDetail;
import projecta07.service.impl.OrderService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/order")
@CrossOrigin(origins = "*")
public class OrderController {
    @Autowired
    OrderService orderService;

    //Make by HauNT search date order, idOrder, date order and idOrder, Pagination

    @RequestMapping(value = "/listOrder", method = RequestMethod.GET)
    public ResponseEntity<Page<Order>> showPage(
            @PageableDefault(size = 5)
            Pageable pageable,
//            @RequestParam(value = "page", required = false) String page,
//            @RequestParam(value = "numPage", required = false) String numPage,
            @RequestParam(value = "idOrder", required = false) Optional<Long> idOrder,
            @RequestParam(value = "dateOrder", required = false) Optional<String> dateOrder
    ) {
//        pageable = PageRequest.of(numPage1, 10);
        Page<Order> listOrder;
        if ((idOrder.isPresent()) && dateOrder.isPresent() && !dateOrder.get().equals("")) {
            listOrder = orderService.findOrderByIdOrderAndDateOrder(idOrder, dateOrder, pageable);
        } else if (idOrder.isPresent()) {
            listOrder = orderService.findOrderByIdOrder(idOrder, pageable);
        } else if (dateOrder.isPresent()) {
            listOrder = orderService.findOrderByDateOrder(dateOrder, pageable);
        } else {
            listOrder = orderService.findAll(pageable);
        }
        if (listOrder.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Page<Order>>(listOrder, HttpStatus.OK);
    }

    //
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<List<Order>> showList(
            @RequestParam(value = "idOrder", required = false) Optional<Long> idOrder,
            @RequestParam(value = "dateOrder", required = false) Optional<String> dateOrder) {
        List<Order> listOrder;
        if ((idOrder.isPresent()) && dateOrder.isPresent() && !dateOrder.get().equals("")) {
            listOrder = orderService.findOrderByIdOrderAndDateOrder(idOrder, dateOrder);
        } else if (idOrder.isPresent()) {
            listOrder = orderService.findOrderById(idOrder);
        } else if (dateOrder.isPresent()) {
            listOrder = orderService.findOrderByDateOrder(dateOrder);
        } else {
            listOrder = orderService.findAll();
        }
        if (listOrder.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Order>>(listOrder, HttpStatus.OK);
    }





}
