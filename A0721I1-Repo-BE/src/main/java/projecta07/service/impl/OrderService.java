package projecta07.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import projecta07.model.Order;
import org.springframework.transaction.annotation.Transactional;
import projecta07.model.OrderDetail;
import projecta07.repository.IOrderRepository;
import projecta07.service.IOrderService;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class OrderService implements IOrderService {
    @Autowired
    private IOrderRepository iOrderService;

    @Autowired
    private IOrderRepository orderRepository;

    @Override
    public List<Order> findOrderById(Optional<Long> idOrder) {
        return orderRepository.findOrderByIdOrder(idOrder);
    }

    @Override
    public List<Order> findOrderByDateOrder(Optional<String> dateOrder) {
        return orderRepository.findAllByDateOrder(dateOrder);
    }

    @Override
    public List<Order> findOrderByIdOrderAndDateOrder(Optional<Long> idOrder, Optional<String> dateOrder) {
        return orderRepository.findAllByIdOrderAndDateOrder(idOrder, dateOrder);
    }


    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Page<Order> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Override
    public Page<Order> findOrderByIdOrder(Optional<Long> idOrder, Pageable pageable) {
        return orderRepository.findByIdOrder(idOrder, pageable);
    }

    @Override
    public Page<Order> findOrderByDateOrder(Optional<String> dateOrder, Pageable pageable) {
        return orderRepository.findByDateOrder(dateOrder, pageable);
    }

    @Override
    public Page<Order> findOrderByIdOrderAndDateOrder(Optional<Long> idOrder, Optional<String> dateOrder, Pageable pageable) {
        return orderRepository.findByIdOrderAndDateOrder(idOrder, dateOrder, pageable);
    }

    public Order getOrderByTableId(Long id) {
        return orderRepository.getOrderByTableId(id);
    }

    public Order saveOrder(Order order) {
        return this.orderRepository.save(order);
    }

    public Order getOrderById(Long id) {
        return this.orderRepository.findById(id).orElse(null);
    }

    @Override
    public Order findOrderOfTableById(Long id) {
        return iOrderService.getOrderByTableId(id);
    }

    @Override
    public void cancelTable(Long id) {
        iOrderService.removeOrderToTable(id);
    }


}