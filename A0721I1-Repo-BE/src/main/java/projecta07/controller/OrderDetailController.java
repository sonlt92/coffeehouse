package projecta07.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import projecta07.model.Order;
import projecta07.model.OrderDetail;
import projecta07.model.Product;
import projecta07.model.Table;
import projecta07.service.IOrderDetailService;
import projecta07.service.IProductService;
import projecta07.service.impl.OrderDetailService;
import projecta07.service.impl.OrderService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import projecta07.service.impl.TableService;

import projecta07.service.impl.ProductService;


@RestController
@RequestMapping("/api/order-detail")
@CrossOrigin(origins = "*")
public class OrderDetailController {
    @Autowired
    private IProductService productService;

    @Autowired
    private IOrderDetailService orderDetailService;

    @Autowired
    private OrderDetailService ordService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private TableService tableService;

    //Make by HauNT view detail order
    @RequestMapping(value = "/orderDetail/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<OrderDetail>> getOrder(@PathVariable("id") long id) {
        List<OrderDetail> orderDetail = ordService.findByIdOrder(id);
        if (orderDetail == null) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<OrderDetail>>(orderDetail, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Iterable<OrderDetail>> findAll() {
        List<OrderDetail> orderDetails = orderDetailService.findAllWithList();
        if (orderDetails.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(orderDetails, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDetail> findById(@PathVariable Long id) {
        Optional<OrderDetail> orderDetail = orderDetailService.findById(id);
        if (!orderDetail.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(orderDetail.get(), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/add-to-cart/{idOrder}", method = RequestMethod.POST)
    public ResponseEntity<OrderDetail> saveOrderDetail(@RequestBody OrderDetail orderDetail,
                                                       @PathVariable("idOrder") Long idOrder) {
        /* Get order by id and get list order detail */
        Order order = orderService.getOrderById(idOrder);
        Long idProduct = orderDetail.getProduct().getIdProduct();
        Integer quantity = orderDetail.getNumberProduct();

        /* Get quantity product */
        Optional<Product> product = productService.findById(idProduct);
        int quantityProduct;
        if (product.isPresent()) {
            quantityProduct = product.get().getQuatityProduct();
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        /* Get class order detail to calculate total price */
        OrderDetail calTotalPrice;

        /* Check existing product*/
        boolean productExisting = false;

        /* Get table from order */
        Table table = tableService.getTableById(order.getTable().getIdTable());

        /* Set empty table */
        table.setEmptyTable(false);

        /* Edit table */
        tableService.saveTable(table);

        List<OrderDetail> orderDetails = ordService.getOrderDetailsByOrderId(idOrder);
        /*Check product quantity*/
        if (quantityProduct > 0) {
            productService.subQuantity(idProduct, quantity);
            if (orderDetails.isEmpty()) {
                order.setTotalOrder(orderDetail.getTotalProduct());
                /* Save and add order detail to list */
                orderDetails.add(ordService.save(orderDetail));
            } else {
                for (OrderDetail ord : orderDetails) {
                    calTotalPrice = new OrderDetail();
                    if (ord.getProduct().getIdProduct() == orderDetail.getProduct().getIdProduct()) {
                        /* Edit total price and quantity */
                        ord.setNumberProduct(ord.getNumberProduct() + orderDetail.getNumberProduct());
                        ord.setTotalProduct(calTotalPrice.calculateTotalPriceOrderDetail(ord));

                        /* Edit in list */
                        orderDetails.set(orderDetails.indexOf(ord), ord);

                        /* Check existing product */
                        productExisting = true;
                        break;
                    } else {
                        productExisting = false;
                    }
                }
                /* Edit */
                if (!productExisting) {
                    orderDetails.add(ordService.save(orderDetail));
                }
            }
            /* Update total price in order */
            order.setTotalOrder(new Order().calculateTotalPriceInOrder(orderDetails, order));
            orderService.saveOrder(order);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<OrderDetail> updateOrderDetail(@PathVariable Long id, @RequestBody @Valid OrderDetail orderDetail, BindingResult bindingResult) {
        Optional<OrderDetail> orderDetailOptional = orderDetailService.findById(id);
        if (!orderDetailOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            if (bindingResult.hasFieldErrors()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            orderDetail.setIdOrderDetail(orderDetailOptional.get().getIdOrderDetail());
            return new ResponseEntity<>(orderDetailService.save(orderDetail), HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OrderDetail> deleteOrderDetail(@PathVariable Long id) {
        Optional<OrderDetail> orderDetailOptional = orderDetailService.findById(id);
        if (orderDetailOptional.isPresent()) {
            orderDetailService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}