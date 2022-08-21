package projecta07.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projecta07.model.Role;
import projecta07.repository.RoleRepository;
import projecta07.service.IRoleService;

import java.util.List;

@Service
public class RoleService implements IRoleService {

    @Autowired
    RoleRepository roleRepository;
    @Override
    public Role findByName(String roleName) {
        return roleRepository.findByNameRole(roleName);
    }

    @Override
    public List<Role> findAll() {
        return (List<Role>) roleRepository.findAll();
    }

    @Override
    public Role findById(long id) {
        return roleRepository.findById(id).orElse(null);
    }
}
