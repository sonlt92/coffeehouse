package projecta07.service;

import org.springframework.security.core.userdetails.UserDetails;
import projecta07.model.Employee;
import projecta07.model.User;
import java.util.List;
import java.util.Optional;

public interface IUserService {
    User save(User user);

    Iterable<User> saveAll(Iterable<User> users);

    Optional<User> findById(Long id);

    boolean existsById(Long id);

    List<User> findAll();

    Iterable<User> findAllById(Iterable<Long> iterable);

    User findUserByUsername(String username);

    long count();

    void deleteById(Long id);

    void delete(User user);

    void deleteAll(Iterable<? extends User> iterable);

    void deleteAll();

    void saveUser(User user);

    UserDetails loadUserByUsername(String username);

    //    @Query("SELECT u.id FROM User u WHERE u.username = ?1")
    Integer getUserIdByUsername(String username);

    //    @Query("SELECT u.username FROM User u WHERE u.id = ?1")
    User getUserByUsername(String username);

    Boolean checkExistUser(String username);

}
