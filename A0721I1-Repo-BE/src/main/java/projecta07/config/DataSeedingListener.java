package projecta07.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import projecta07.model.Role;
import projecta07.model.User;
import projecta07.repository.RoleRepository;
import projecta07.repository.UserRepository;
import projecta07.ultil.EncrypPasswordUtils;

import java.util.HashSet;

/**
 * Seeding data test for table users and roles
 */
@Component
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (roleRepository.findByNameRole("ROLE_MANAGER")==null){
            roleRepository.save(new Role("ROLE_MANAGER"));
        }

        if (roleRepository.findByNameRole("ROLE_STAFF")==null){
            roleRepository.save(new Role("ROLE_STAFF"));
        }
        if (roleRepository.findByNameRole("ROLE_BLOCK")==null){
            roleRepository.save(new Role("ROLE_BLOCK"));
        }

        if (userRepository.findByUsername("manager102") == null){
            User manager = new User();
            manager.setUsername("manager102");
            manager.setPassword(EncrypPasswordUtils.EncrypPasswordUtils("123456"));
            HashSet<Role> roles = new HashSet<>();
            roles.add(roleRepository.findByNameRole("ROLE_MANAGER"));
            roles.add(roleRepository.findByNameRole("ROLE_STAFF"));
            manager.setRoles(roles);
            userRepository.save(manager);
        }
//
////        //Them Nhân viên
//        if (userRepository.findByUsername("staff1") == null){
//            User staff = new User();
//            staff.setUsername("staff1");
//            staff.setPassword(EncrypPasswordUtils.EncrypPasswordUtils("123456"));
//            HashSet<Role> roles = new HashSet<>();
//            roles.add(roleRepository.findByNameRole("ROLE_STAFF"));
//            staff.setRoles(roles);
//            userRepository.save(staff);
//        }
//
//        if (userRepository.findByUsername("staff2") == null){
//            User staff = new User();
//            staff.setUsername("staff2");
//            staff.setPassword(EncrypPasswordUtils.EncrypPasswordUtils("123456"));
//            HashSet<Role> roles = new HashSet<>();
//            roles.add(roleRepository.findByNameRole("ROLE_STAFF"));
//            staff.setRoles(roles);
//            userRepository.save(staff);
//        }
//
//        if (userRepository.findByUsername("staff3") == null){
//            User staff = new User();
//            staff.setUsername("staff3");
//            staff.setPassword(EncrypPasswordUtils.EncrypPasswordUtils("123456"));
//            HashSet<Role> roles = new HashSet<>();
//            roles.add(roleRepository.findByNameRole("ROLE_STAFF"));
//            staff.setRoles(roles);
//            userRepository.save(staff);
//        }
//
//        if (userRepository.findByUsername("staff4") == null){
//            User staff = new User();
//            staff.setUsername("staff4");
//            staff.setPassword(EncrypPasswordUtils.EncrypPasswordUtils("123456"));
//            HashSet<Role> roles = new HashSet<>();
//            roles.add(roleRepository.findByNameRole("ROLE_STAFF"));
//            staff.setRoles(roles);
//            userRepository.save(staff);
//        }
    }
}