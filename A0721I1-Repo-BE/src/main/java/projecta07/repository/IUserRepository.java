package projecta07.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projecta07.model.User;

@Repository
public interface IUserRepository extends JpaRepository<User,Long> {

}
