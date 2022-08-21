package projecta07.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projecta07.model.Position;

@Repository
public interface IPositionRepository extends JpaRepository<Position,Long> {
}
