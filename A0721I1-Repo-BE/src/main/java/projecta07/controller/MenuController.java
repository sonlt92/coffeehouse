package projecta07.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projecta07.dto.MenuOrderDTO;
import projecta07.model.*;
import projecta07.service.impl.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("menu")
@CrossOrigin(origins = "*")
public class MenuController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ProductService productService;

    @Autowired
    private TypeProductService typeProductService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;


    @Autowired
    private TableService tableService;

    /* Get length of products */
    @RequestMapping(value = "amount-products", method = RequestMethod.GET)
    public ResponseEntity<Integer> getAmountOfProducts() {
        return new ResponseEntity<>(this.productService.getAmountOfProducts(), HttpStatus.OK);
    }

    /* Get length of products by id type product */
    @RequestMapping(value = "amount-products/idType={idType}", method = RequestMethod.GET)
    public ResponseEntity<Integer> getAmountOfProductsByIdType(@PathVariable("idType") Long idType) {
        return new ResponseEntity<>(this.productService.getAmountOfProductsByTypeId(idType), HttpStatus.OK);
    }

    /*  Get values for menu page */
    @RequestMapping(value = "current={currentPage}&size={sizePage}", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> getProducts(@PathVariable("currentPage") int currentPage
            , @PathVariable("sizePage") int sizePage) {
        List<Product> products = productService.getProductsWithPagination(currentPage, sizePage);
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @RequestMapping(value = "product-type", method = RequestMethod.GET)
    public ResponseEntity<List<TypeProduct>> getTypesProduct() {
        List<TypeProduct> typeProducts = typeProductService.getTypesProduct();
        if (typeProducts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(typeProducts, HttpStatus.OK);
    }

    /* Get products by product type id */
    @RequestMapping(value = "product-type/{idType}/{currentPage}&{sizePage}", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> getProductsByTypeId(@PathVariable("idType") Long idType
            , @PathVariable("currentPage") int currentPage, @PathVariable("sizePage") int sizePage) {
        List<Product> products = productService.getProductsByTypeProductId(idType, currentPage, sizePage);
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /* Get Data DTO for table */
    @RequestMapping(value = "table/{idTable}/{currentPage}&{sizePage}", method = RequestMethod.GET)
    public ResponseEntity<List<MenuOrderDTO>> getMenuOrderDTO(@PathVariable("idTable") Long idTable
            , @PathVariable("currentPage") int currentPage, @PathVariable("sizePage") int sizePage) {
        List<MenuOrderDTO> menuOrderDTOS = new ArrayList<>();
        MenuOrderDTO menuOrderDTO = null;

        /* Get order by table id */
        Order order = orderService.getOrderByTableId(idTable);

        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            /* Get orders detail by order id */
            List<OrderDetail> orderDetails = orderDetailService.getOrderDetailsByOrderId(order.getIdOrder());
            if (orderDetails.isEmpty()) {
                menuOrderDTO = new MenuOrderDTO();
                menuOrderDTO.setOrderId(order.getIdOrder());
                menuOrderDTOS.add(menuOrderDTO);
            } else {
                for (OrderDetail orderDetail : orderDetails) {
                    menuOrderDTO = new MenuOrderDTO();
                    menuOrderDTO.setOrderId(order.getIdOrder());
                    if (orderDetail.getOrder().getIdOrder() == menuOrderDTO.getOrderId()) {
                        menuOrderDTO.setOrderId(orderDetail.getOrder().getIdOrder());
                        menuOrderDTO.setOrderDetailId(orderDetail.getIdOrderDetail());
                        menuOrderDTO.setNameProduct(orderDetail.getProduct().getNameProduct());
                        menuOrderDTO.setQuantity(orderDetail.getNumberProduct());
                        menuOrderDTO.setPrice(orderDetail.getProduct().getPriceProduct());
                        menuOrderDTO.setTotalPrice(orderDetail.getTotalProduct());
                        menuOrderDTO.setProductId(orderDetail.getProduct().getIdProduct());
                    }
                    menuOrderDTOS.add(menuOrderDTO);
                }
            }
            /* Pagination for DTO*/
            Pageable dtoPageable = PageRequest.of(currentPage, sizePage);
            int start = (int) dtoPageable.getOffset();
            int end = Math.min((start + dtoPageable.getPageSize()), menuOrderDTOS.size());
            Page<MenuOrderDTO> menuOrderDTOPage = new PageImpl<>(menuOrderDTOS.subList(start, end), dtoPageable, sizePage);

            /* Get and set total page */
            for (MenuOrderDTO mn : menuOrderDTOS) {
                mn.setTotalPageDTO(menuOrderDTOS.size());
            }
            menuOrderDTOS.add(menuOrderDTO);

            return new ResponseEntity<>(menuOrderDTOPage.getContent(), HttpStatus.OK);
        }
    }

    /* Click button Order*/
    @RequestMapping(value = "table/{idTable}/{idEmployee}", method = RequestMethod.POST)
    public ResponseEntity<Order> handleOrder(@PathVariable("idTable") Long idTable, @RequestBody List<MenuOrderDTO> menuOrderDTOInput
            , @PathVariable("idEmployee") Long idEmployee) {
        /* Define object */
        Order orderSaved = new Order();
        OrderDetail orderDetail;
        Order order;
        Employee employee = employeeService.getEmployeeById(idEmployee);
        Table table = tableService.getTableById(idTable);
        Product product;
        List<OrderDetail> orderDetails = new ArrayList<>();

        /* Calculate total price in order detail */
        double totalPriceOrderDetail;
        double totalPriceOrder;

        /* Check existing of order */
        order = orderService.getOrderByTableId(table.getIdTable());

        for (MenuOrderDTO menuOrderDTO : menuOrderDTOInput) {
            orderDetail = new OrderDetail();
            orderDetail.setNumberProduct(menuOrderDTO.getQuantity());
            /* Get product */
            product = productService.getProductById(menuOrderDTO.getProductId());

            if (order == null) {
                order = new Order();
                /* set value for order */
                order.setTable(table);
                order.setEmployee(employee);
                order.setDateOrder(String.valueOf(LocalDate.now()));
            }

            /* not payment yet */
            table.setEmptyTable(false);
            order.setStatusOrder(false);

            /* set value for order detail */
            orderDetail.setOrder(order);
            orderDetail.setProduct(product);

            /* Save order */
            orderSaved = orderService.saveOrder(order);

            /* Get total price in order detail */
            totalPriceOrderDetail = orderDetail.calculateTotalPriceOrderDetail(orderDetail);
            orderDetail.setTotalProduct(totalPriceOrderDetail);
            orderDetails.add(orderDetail);

            orderDetailService.save(orderDetail);

            /* Set total price for order */
            totalPriceOrder = order.calculateTotalPriceInOrder(orderDetails, orderSaved);
            orderSaved.setTotalOrder(totalPriceOrder);

            /* Update total price for order */
            orderService.saveOrder(orderSaved);
        }

        return new ResponseEntity<>(orderSaved, HttpStatus.OK);
    }

    /* Click button Payment */
    @RequestMapping(value = "table/{idTable}/payment", method = RequestMethod.PATCH)
    public ResponseEntity<Order> handlePayment(@PathVariable("idTable") Long idTable) {
        /* Get order */
        Table table = tableService.getTableById(idTable);
        Order order = orderService.getOrderByTableId(table.getIdTable());

        table.setEmptyTable(true);
        tableService.saveTable(table);
        /* Edit table */

        order.setStatusOrder(true);
        orderService.saveOrder(order);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /* Click button delete food */
    @RequestMapping(value = "table/delete/{idOrderDetail}/{idOrder}", method = RequestMethod.DELETE)
    public ResponseEntity<OrderDetail> handleDelete(@PathVariable("idOrderDetail") Long idOrderDetail
            , @PathVariable("idOrder") Long idOrder) {
        Order order = orderService.getOrderById(idOrder);
        double totalPriceOrderDetail;
        Optional<OrderDetail> orderDetail = orderDetailService.findById(idOrderDetail);

        /* Get list order detail after remove */
        List<OrderDetail> orderDetails = orderDetailService.getOrderDetailsByOrderId(idOrder);

        /* Edit quantity product after remove food */
        Product product;

        if(orderDetail.isPresent()) {
            for (OrderDetail ord : orderDetails) {
                product = productService.getProductById(ord.getProduct().getIdProduct());

                if(product.getIdProduct() == orderDetail.get().getProduct().getIdProduct()) {
                    product.setQuatityProduct(ord.getNumberProduct() + product.getQuatityProduct());
                    productService.save(product);
                }
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        /* Delete food */
        orderDetailService.deleteById(idOrderDetail);
        orderDetails = orderDetailService.getOrderDetailsByOrderId(idOrder);

        /* Edit total price in order */
        totalPriceOrderDetail = order.calculateTotalPriceInOrder(orderDetails, order);
        order.setTotalOrder(totalPriceOrderDetail);

        /* Save order */
            orderService.saveOrder(order);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /* Get table by id */
    @RequestMapping(value = "/table/{idTable}", method = RequestMethod.GET)
    public ResponseEntity<Table> getTable(@PathVariable("idTable") Long idTable) {
        return new ResponseEntity<>(this.tableService.findTableById(idTable), HttpStatus.OK);
    }

    /* Get order by id */
    @RequestMapping(value = "/order/{idOrder}", method = RequestMethod.GET)
    public ResponseEntity<Order> getOrder(@PathVariable("idOrder") Long idOrder) {
        return new ResponseEntity<>(orderService.getOrderById(idOrder), HttpStatus.OK);
    }
}