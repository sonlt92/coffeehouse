package projecta07.service;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import projecta07.model.Order;
import java.util.List;
import java.util.Optional;


public interface IOrderService {
    List<Order> findAll();

    List<Order> findOrderById(Optional<Long> id);

    List<Order> findOrderByDateOrder(Optional<String> dateOrder);

    List<Order> findOrderByIdOrderAndDateOrder(Optional<Long> id, Optional<String> dateOrder);


    Page<Order> findAll(Pageable pageable);

    Page<Order> findOrderByIdOrder(Optional<Long> idOrder, Pageable pageable);

    Page<Order> findOrderByDateOrder(Optional<String> dateOrder, Pageable pageable);

    Page<Order> findOrderByIdOrderAndDateOrder(Optional<Long> idOrder, Optional<String> dateOrder, Pageable pageable);

    //ex
//    List<Order>getAllOrder();

    Order findOrderOfTableById(Long id);

    void cancelTable(Long id);


    Order saveOrder(Order order);


}
