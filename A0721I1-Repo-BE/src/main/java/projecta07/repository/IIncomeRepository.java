package projecta07.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import projecta07.model.Order;

import java.util.List;

@Repository
public interface IIncomeRepository extends JpaRepository<Order,Long> {

    Order findOrderByIdOrder(Long id);

//    @Query(value = "select * " +
//            "from order " +
//            "where id_oder = :id and;"
//            , nativeQuery = true)
//    List<Order> findOrder(@Param("id") Long id);

    // TỪ NGÀY NÀY ĐẾN NGÀY NÀY
    @Query(value="SELECT sum(total_order)\n" +
            "FROM `order`\n" +
            "WHERE date_order BETWEEN :startDay  AND :endDay ;", nativeQuery=true)
    String sumTotalStartDayToEndDay(String startDay, String endDay);

    // THEO NGÀY
    @Query(value="select sum(total_order)\n" +
            "from `order`\n" +
            "where date_order = curdate()\n" +
            "group by date_order ;", nativeQuery =true)
    String sumTotalOrderDay();



    // THEO TUẦN
    @Query(value="select sum(total_order)\n" +
            "from `order`\n" +
            "where week(date_order) = week(curdate()) \n" +
            "group by week(date_order);", nativeQuery =true)
    String sumTotalOrderWeek();

    // THEO THÁNG
    @Query(value="select sum(total_order) \n" +
            "from `order`\n" +
            "where month(date_order) = month(curdate()) AND year(date_order) = year(curdate())\n" +
            "group by month(date_order), year(date_order);", nativeQuery= true)
    String sumTotalOrderMonth();

    // THEO NĂM
    @Query(value="select  sum(total_order) \n" +
            "from `order`\n" +
            "where year(date_order) = year(curdate()) -- and status = 'Đã thanh toán' -- có điều kiện đã thanh toán\n" +
            "group by year(date_order);", nativeQuery =true)
    String sumTontalOrderYear();

    // tổng món cafe
    @Query(value="select count(p.id_product) from product as p where p.id_type_product like '1';", nativeQuery= true)
    String countProductCafe();

    //    tổng món trà
    @Query(value="select count(p.id_product) from product as p where p.id_type_product like '2';", nativeQuery= true)
    String countProductTea();

    // tổng món bánh
    @Query(value="select count(p.id_product) from product as p where p.id_type_product like '3';", nativeQuery= true)
    String countProductCake();

    // món khác
    @Query(value="select count(p.id_product) from product as p where p.id_type_product like '4';", nativeQuery= true)
    String countProductOther();
}
