package projecta07.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projecta07.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByNameRole(String roleName);
}
