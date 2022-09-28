package m2i.spring.security.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.management.loading.PrivateClassLoader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import m2i.spring.security.dao.UserRepository;
import m2i.spring.security.model.Role;
import m2i.spring.security.model.User;

@Service("userDetailsServices")
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> userOptional = userRepository.findByEmail(email);
		if(userOptional.isEmpty())
			throw new UsernameNotFoundException("Email not found");
		User user= userOptional.get();
		
		List<GrantedAuthority> authorities = user.getRoles()
				.stream()
				.flatMap(this::transformRoleAsStrings).toList();
			
		return new org.springframework.security.core.userdetails.User(user.getEmail(),
				user.getPassword(),
				user.isEnabled(),
				true, //account not expired
				true, //credentials not expired
				true, //account not locked
				authorities);
	}
	
	private Stream<GrantedAuthority> transformRoleAsStrings(Role role) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(role.getPrivileges().size()+1);
		
		authorities.add(new SimpleGrantedAuthority(role.getName()));
		
		List<SimpleGrantedAuthority> privileges = role.getPrivileges().stream().map(p->new SimpleGrantedAuthority(p.getName())).toList();
		
		authorities.addAll(privileges);
		
		return authorities.stream();
	}

}
