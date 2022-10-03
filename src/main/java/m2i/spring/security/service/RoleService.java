package m2i.spring.security.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import m2i.spring.security.dao.RoleRepository;
import m2i.spring.security.model.Privilege;
import m2i.spring.security.model.Role;

public class RoleService {
	
	@Autowired
	RoleRepository roleRepository;
	
	public void createRoleIfNotExists(String roleName ) {
		Optional<Role> roleOptional = roleRepository.findByName(roleName);
	
		if (roleOptional.isEmpty()) {
			Role role1 = new Role(roleName, new ArrayList<Privilege>());
			roleRepository.save(role1);
		}
	}

}
