package qt.rw.challenge.blog_post;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import qt.rw.challenge.blog_post.enums.Erole;
import qt.rw.challenge.blog_post.repository.IRoleRepository;
import qt.rw.challenge.blog_post.services.IRoleService;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class BlogPostApplication {
	private final IRoleRepository roleRepository;
	private final IRoleService roleService;

    public BlogPostApplication(IRoleRepository roleRepository, IRoleService roleService) {
        this.roleRepository = roleRepository;
        this.roleService = roleService;
    }

    public static void main(String[] args) {
		SpringApplication.run(BlogPostApplication.class, args);
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
