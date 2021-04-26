package spring.boot.endterm.service.role;

import spring.boot.endterm.entity.Role;

public interface RoleService {
    Role findByName(String roleName);
}
