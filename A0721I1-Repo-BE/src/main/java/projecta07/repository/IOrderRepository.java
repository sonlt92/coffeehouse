package projecta07.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import projecta07.model.Order;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import projecta07.model.OrderDetail;
import java.util.List;
import java.util.Optional;

import java.util.List;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Long> {
    @Query(value = "select * from `order` where status_order = 0 and id_table = ?1" , nativeQuery = true)
    List<Order> findOrderByIdOrder(Optional<Long> idOrder);
    List<Order> findAllByDateOrder(Optional<String> dateOrder);

    List<Order> findAllByIdOrderAndDateOrder(Optional<Long> idOrder, Optional<String> dateOrder);

    @Query(value = "select * from `order` where date_order=:dateOrder", nativeQuery = true)
    Page<Order> findByDateOrder(Optional<String> dateOrder, Pageable pageable);

    @Query(value = "select * from `order` where id_order=:idOrder and date_order like %:dateOrder%", nativeQuery = true)
    Page<Order> findByIdOrderAndDateOrder(Optional<Long> idOrder, @Param("dateOrder") Optional<String> dateOrder, Pageable pageable);

    @Query(value = "select * from `order` where  id_order=:idOrder", nativeQuery = true)
    Page<Order> findByIdOrder(Optional<Long> idOrder, Pageable pageable);

    //ex
//    @Query(value = "select id_order, date_order, total_order, id_table, id_employee from `order`", nativeQuery = true)
//    Page<Order> getAllOrder(Pageable pageable);

    @Query(value = "select * from `order` where status_order =0 and id_table = ?1" , nativeQuery = true)
    Order getOrderByTableId(Long id);

    @Query(value = "select * from `order` where status_order = 0 and id_table = ?1" , nativeQuery = true)
    List<Order> getOrdersByTableId(Long id);

    /* @Query("delete order khỏi table ")*/
    @Modifying
    @Transactional
    @Query(value = "delete from `order` where `order`.id_table = ?1 and `order`.status_order = false", nativeQuery = true)
    void removeOrderToTable(Long id);
    /* Get order with status order is false by table id */
//    @Query(value = "select * from `order` where `order`.status_order = 0 and id_table =?1" , nativeQuery = true)
}