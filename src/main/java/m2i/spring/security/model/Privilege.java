package m2i.spring.security.model;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Privilege {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(unique = true, nullable = false)
	private String name;
	
	@ManyToMany(mappedBy = "privileges")
    private List<Role> roles;

	public Privilege() {
		super();
	}

	public Privilege(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Integer getIdInteger() {
		return id;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Privilege other = (Privilege) obj;
		return Objects.equals(id, other.id);
	}
	
	

}
