package gestionconges.dto;

public class UserDto {
	
	private String email;
	private String password;
	private String role="USER";
	private String fullname;
	private String matricule;
	private String poste;
	private String service;

	public UserDto(String email, String password, String role, String fullname, String matricule, String poste, String service) {
		this.email = email;
		this.password = password;
		this.role = role;
		this.fullname = fullname;
		this.matricule = matricule;
		this.poste = poste;
		this.service = service;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public String getPoste() {
		return poste;
	}

	public void setPoste(String poste) {
		this.poste = poste;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}
}
