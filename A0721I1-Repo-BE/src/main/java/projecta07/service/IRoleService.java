package projecta07.service;

import projecta07.model.Role;

import java.util.List;

public interface IRoleService {
    Role findByName(String name);
    List<Role> findAll();
    Role findById(long id);
}
