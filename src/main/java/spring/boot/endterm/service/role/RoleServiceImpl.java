package spring.boot.endterm.service.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.boot.endterm.entity.Role;
import spring.boot.endterm.exceptions.CustomNotFoundException;
import spring.boot.endterm.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService{

    private final RoleRepository repository;

    @Autowired
    public RoleServiceImpl(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Role findByName(String roleName) {
        return repository.findByName(roleName).orElseThrow(() -> new CustomNotFoundException(String.format("" +
                "Role with name : %s does not exist" , roleName )));
    }
}
