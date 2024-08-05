package rca.ac.year3.security_starter.services;


import rca.ac.year3.security_starter.enums.Erole;
import rca.ac.year3.security_starter.models.Role;


public interface IRoleService {

    Role findByName(Erole role);

    Role createRole(String roleName);
}
