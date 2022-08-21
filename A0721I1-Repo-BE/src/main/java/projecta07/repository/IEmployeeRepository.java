package projecta07.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projecta07.model.Employee;
import org.springframework.data.jpa.repository.Query;
import java.util.List;


@Repository
public interface IEmployeeRepository extends JpaRepository<Employee , Long> {
    //VinhTQ
    @Query(value = "select * from employee  where id_employee =?1", nativeQuery = true)
    Employee findEmployeeById(long id);

    //VinhTQ
    @Query(value = "SELECT  id_employee,email_employee, reset_password_token, name_employee, address_employee, date_of_birth_employee, gender_employee, phone_employee, salary_employee,position.id_position, position.name_position, \n " +
            "user.id_user,user.username " +
            "FROM employee \n" +
            "inner join position on employee.id_position = position.id_position \n" +
            "inner join user on employee.id_user = user.id_user \n" +
            "where user.username like concat('%' ,?1,'%') " +
            "and employee.name_employee like concat('%' ,?2,'%') " +
            "and employee.phone_employee like concat('%' ,?3,'%') ",
    countQuery = "SELECT  id_employee, name_employee, address_employee, date_of_birth_employee, gender_employee, phone_employee, salary_employee,position.id_position, position.name_position, \n " +
            "user.id_user,user.username " +
            "FROM employee \n" +
            "inner join position on employee.id_position = position.id_position \n" +
            "inner join user on employee.id_user = user.id_user \n" +
            "where user.username like concat('%' ,?1,'%') " +
            "and employee.name_employee like concat('%' ,?2,'%') " +
            "and employee.phone_employee like concat('%' ,?3,'%') "
    ,nativeQuery = true)
    Page<Employee> searchAllEmployee(String username, String name, String phone, Pageable pageable);

    //HauLST
    @Query(value = "select id_employee, email_employee, reset_password_token, name_employee, address_employee, date_of_birth_employee, gender_employee, phone_employee, salary_employee,position.id_position, position.name_position, \n" +
            "user.id_user,user.username, user.password, role.id_role,role.name_role\n" +
            "            from employee \n" +
            "            inner join position on employee.id_position = position.id_position\n" +
            "            inner join user on employee.id_user = user.id_user\n" +
            "            inner join user_role on user.id_user = user_role.id_user\n" +
            "            inner join role on user_role.id_role = role.id_role\n" +
            "            where employee.id_user=?1", nativeQuery = true)
    Employee findEmployeeByIdUser(Long idUser);

    //  Bach LT
    @Query("SELECT e FROM Employee e WHERE e.emailEmployee = ?1")
    public Employee findByEmail(String email);

    @Query("SELECT e FROM Employee e WHERE e.resetPasswordToken = ?1")
    public Employee findByResetPasswordToken(String token);

    //Phương thức của bin a hậu đừng xóa nha
    @Query(value = "select id_employee, name_employee, email_employee, reset_password_token, address_employee, date_of_birth_employee, gender_employee, phone_employee, salary_employee,position.id_position, position.name_position, \n" +
            "user.id_user,user.username, user.password, role.id_role,role.name_role\n" +
            "            from employee \n" +
            "            inner join position on employee.id_position = position.id_position\n" +
            "            inner join user on employee.id_user = user.id_user\n" +
            "            inner join user_role on user.id_user = user_role.id_user\n" +
            "            inner join role on user_role.id_role = role.id_role\n" +
            "            where user.id_user=?1", nativeQuery = true)
    Employee findEmployeeById_User(Long idUser);

}




