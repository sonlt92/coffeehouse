package projecta07.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import projecta07.model.Feedback;

import java.util.List;

@Repository
public interface IFeedbackRepository extends JpaRepository<Feedback,Long> {
    @Query (value="select * from feedback where date_feedback=?1",
            countQuery="select count (id) from feedback where date_feedback=?1",
            nativeQuery=true)
    Page<Feedback> findAllFeedbackByDateFeedback(String date, Pageable pageable);
    @Query (value="select * from feedback where date_feedback=?1",
            countQuery="select count (id) from feedback where date_feedback=?1",
            nativeQuery=true)
    List<Feedback> findAllFeedbackByDateFeedbackNotPagination(String date);
    List<Feedback> findAll();
}
