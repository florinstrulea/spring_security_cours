package m2i.spring.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import m2i.spring.security.dao.RoleRepository;
import m2i.spring.security.dao.UserRepository;
import m2i.spring.security.dto.UserDto;
import m2i.spring.security.model.Role;
import m2i.spring.security.model.User;

@Service
public class UserService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	public User createUser(UserDto userDto) {
		
		Role userRole = roleRepository.findByName("USER").get(); 
		
		List<Role> roles = new ArrayList<>();
		
		roles.add(userRole);
		
		User user = new User(userDto.getEmail(), passwordEncoder.encode(userDto.getPassword()), true, roles);
		
		userRepository.save(user);
		return null;
	}

}
