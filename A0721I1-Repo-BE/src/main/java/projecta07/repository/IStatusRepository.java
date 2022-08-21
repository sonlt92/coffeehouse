package projecta07.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import projecta07.model.Status;

import java.util.List;

@Repository
public interface IStatusRepository extends JpaRepository<Status, Long> {
    @Query(value = "select id_status, name_status from status",nativeQuery = true)
    List<Status> findAllStatus();

}
