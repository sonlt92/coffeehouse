package projecta07.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projecta07.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//    @Query("select u from User u where u.username = ?1")
    User findByUsername(String username);

    User findAllByUsername(String username);

//    @Query("SELECT u.id FROM User u WHERE u.username = ?1")
    Integer getUserIdByUsername(String username);

//    @Query("SELECT u.id FROM User u WHERE u.nickName = ?1")
    User getUserByUsername(String username);

    Boolean existsUsersByUsername(String username);

}
