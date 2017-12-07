package bankaccount.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

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
    
    public Client() {
    }

    public Client(String username, String password) {
    	
        this.username = username;
        this.password = Util.getPasswordHash(password);
        
        this.account = new Account();
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

}
