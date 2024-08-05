package rca.ac.year3.security_starter.serviceImpls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rca.ac.year3.security_starter.enums.Erole;
import rca.ac.year3.security_starter.exceptions.ResourceNotFoundException;
import rca.ac.year3.security_starter.models.Role;
import rca.ac.year3.security_starter.repository.IRoleRepository;
import rca.ac.year3.security_starter.services.IRoleService;
import rca.ac.year3.security_starter.utils.ExceptionUtils;

import java.util.Optional;


@Service
public class RoleServiceImpl implements IRoleService {
    private final IRoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(IRoleRepository iRoleRepository){
        this.roleRepository = iRoleRepository;
    }

    @Override
    public Role findByName(Erole role) {
        return roleRepository.findByName(role).orElseThrow(() -> new ResourceNotFoundException("Role", "name", role.toString()));
    }
    @Override
    public Role createRole(String roleName) {
        Role role = new Role();
        role.setName(Erole.valueOf(roleName));
        try {
            Optional<Role> role1= roleRepository.findByName(Erole.valueOf(roleName));
            if (role1.isEmpty()){
                return roleRepository.save(role);
            }
            else{
                return null;
            }
        }catch (Exception e){
            ExceptionUtils.handleServiceExceptions(e);
            return null;
        }
    }
}
