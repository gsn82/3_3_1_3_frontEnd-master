package myWeb.service;

import myWeb.model.Role;

import java.util.List;

public interface RoleService {
    void saveRole(Role role);
    Role getAuthByName(String name);
    Role getRoleById(Long id);
    List<Role> getAllRoles();
}
