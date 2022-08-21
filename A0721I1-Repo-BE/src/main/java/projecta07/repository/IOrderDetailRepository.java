package projecta07.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import projecta07.model.OrderDetail;

import java.util.List;
import java.util.Optional;

@Repository
public interface IOrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    @Query(value = "select * from orderdetail inner join `order` on `order`.id_order=orderdetail.id_order where `order`.id_order=:id",nativeQuery = true)
    List<OrderDetail> findOrderDetailByIdOrder(Long id);

    @Query(value = "select * from orderdetail where id_order = ?1", nativeQuery = true)
    List<OrderDetail> getAllOrderDetailByOrderId(Long id);
}
