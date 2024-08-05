package qt.rw.challenge.blog_post.services;


import qt.rw.challenge.blog_post.enums.Erole;
import qt.rw.challenge.blog_post.models.Role;


public interface IRoleService {

    Role findByName(Erole role);

    Role createRole(String roleName);
}
