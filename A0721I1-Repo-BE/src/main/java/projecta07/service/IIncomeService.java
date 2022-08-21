package projecta07.service;

import org.springframework.data.domain.Page;
import projecta07.model.Order;
import projecta07.model.OrderDetail;

import java.awt.print.Pageable;
import java.util.List;

public interface IIncomeService {
//    List<Order> findOrder(Long id);
//
//    Order findOrderByIdOrder(String id_order, Pageable pageable);
//
//    Page<Order> findAll(Pageable pageable);
//    void delete(int id);
//
//    void save(Bill bill);
//
//    void saveDetail(ProductBill productBill);

    String sumTotalStartDayToEndDay(String startDay, String endDay);

    String sumTotalOderDay();

    String sumTotalOrderWeek();

    String sumTotalOrderMonth();

    String sumTontalOrderYear();

    String countProductCafe();
    String countProductTea();
    String countProductCake();
    String countProductOther();
}
