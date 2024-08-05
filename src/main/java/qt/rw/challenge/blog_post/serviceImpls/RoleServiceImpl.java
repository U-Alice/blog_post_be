package qt.rw.challenge.blog_post.serviceImpls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qt.rw.challenge.blog_post.enums.Erole;
import qt.rw.challenge.blog_post.exceptions.ResourceNotFoundException;
import qt.rw.challenge.blog_post.models.Role;
import qt.rw.challenge.blog_post.repository.IRoleRepository;
import qt.rw.challenge.blog_post.services.IRoleService;
import qt.rw.challenge.blog_post.utils.ExceptionUtils;

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
