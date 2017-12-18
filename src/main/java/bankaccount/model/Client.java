package bankaccount.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import bankaccount.model.security.Role;
import bankaccount.service.Util;

@Entity
public class Client {
	
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @NotEmpty(message = "Lozinka i korisničko ime su obavezna polja")
    private String username;

    @NotEmpty(message = "Lozinka i korisničko ime su obavezna polja")
    private String password;
    
    @OneToOne(cascade = {CascadeType.ALL})
    private Account account;
    
    @Enumerated(EnumType.STRING)
    private Role role;

    
    public Client() {
    }

    public Client(String username, String password, Role role) {
    	
        this.username = username;
        this.password = Util.getPasswordHash(password);
        if (role != Role.ROLE_ADMIN)
        	this.account = new Account();
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) { this.password = Util.getPasswordHash(password); }
    
    
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
