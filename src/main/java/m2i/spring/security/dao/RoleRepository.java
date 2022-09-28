package m2i.spring.security.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import m2i.spring.security.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	public Optional<Role> findByName(String string);

}
