package web.dao;

import web.model.Role;

import java.util.List;

public interface RoleDAO {
    List<Role> getRoles();
    Role getRoleById(String id);
    Role getRoleByName(String name);

}
