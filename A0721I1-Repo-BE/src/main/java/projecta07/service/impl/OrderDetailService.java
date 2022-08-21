package projecta07.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projecta07.model.OrderDetail;
import projecta07.repository.IOrderDetailRepository;
import projecta07.service.IOrderDetailService;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailService implements IOrderDetailService {
    @Autowired
    private IOrderDetailRepository orderDetailRepository;

    @Override

    public List<OrderDetail> findByIdOrder(Long id) {
        return orderDetailRepository.findOrderDetailByIdOrder(id);
    }


    /* getOrderDetailByOrderId */
    public List<OrderDetail> getOrderDetailByOrderId(Long id) {
        return orderDetailRepository.getAllOrderDetailByOrderId(id);
    }

    @Override
    public void deleteOrderDetailInTable(Long id) {
    }

    @Override
    public Iterable<OrderDetail> findAll() {
        return null;
    }

    @Override
    public List<OrderDetail> findAllWithList() {
        return orderDetailRepository.findAll();
    }


    @Override
    public Optional<OrderDetail> findById(Long id) {
        return orderDetailRepository.findById(id);
    }

    @Override
    public OrderDetail save(OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }

    @Override
    public void delete(Long id) {
        orderDetailRepository.deleteById(id);
    }

    /* get Order Detail by Order Id*/
    public List<OrderDetail> getOrderDetailsByOrderId(Long id) {
        return this.orderDetailRepository.getAllOrderDetailByOrderId(id);
    }

    /* Delete order detail by id and order id */
    public void deleteById(Long orderDetailId) {
        this.orderDetailRepository.deleteById(orderDetailId);
    }
}