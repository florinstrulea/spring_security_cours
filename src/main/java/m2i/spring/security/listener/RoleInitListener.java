package m2i.spring.security.listener;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.stereotype.Component;

import m2i.spring.security.dao.RoleRepository;
import m2i.spring.security.model.Privilege;
import m2i.spring.security.model.Role;

@Component
public class RoleInitListener implements ApplicationListener<ApplicationContextEvent> {

	@Autowired
	RoleRepository roleRepository;

	@Override
	public void onApplicationEvent(ApplicationContextEvent event) {
		
		createRoleIfNotExists("ADMIN");
		createRoleIfNotExists("USER");

	}

	private void createRoleIfNotExists(String roleName ) {
		Optional<Role> roleOptional = roleRepository.findByName(roleName);
	
		if (roleOptional.isEmpty()) {
			Role role1 = new Role(roleName, new ArrayList<Privilege>());
			roleRepository.save(role1);
		}
	}

}
