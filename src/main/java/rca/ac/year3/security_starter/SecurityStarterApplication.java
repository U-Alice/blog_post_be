package rca.ac.year3.security_starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import rca.ac.year3.security_starter.enums.Erole;
import rca.ac.year3.security_starter.models.Role;
import rca.ac.year3.security_starter.repository.IRoleRepository;
import rca.ac.year3.security_starter.services.IRoleService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@SpringBootApplication
public class SecurityStarterApplication {
	private final IRoleRepository roleRepository;
	private final IRoleService roleService;

    public SecurityStarterApplication(IRoleRepository roleRepository, IRoleService roleService) {
        this.roleRepository = roleRepository;
        this.roleService = roleService;
    }

    public static void main(String[] args) {
		SpringApplication.run(SecurityStarterApplication.class, args);
	}
	@Bean
	public String insertRolesFromEnum(){
		Set<Erole> roles = new HashSet<>();
		roles.add(Erole.ADMIN);
		roles.add(Erole.AUTHOR);

		for (Erole role : roles){
			roleService.createRole(role.name());
		}
		return "saved";
	}

}
