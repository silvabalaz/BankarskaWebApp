package bankaccount.model;
import org.hibernate.annotations.Table;
import org.hibernate.validator.constraints.NotEmpty;
import bankaccount.service.PasswordService;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Account {
	
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotEmpty(message = "Lozinka i korisničko ime su obavezna polja")
    private String username;

    @NotEmpty(message = "Lozinka i korisničko ime su obavezna polja")
    private String password;
    
   
    private int iban; 
    
    private double balance;
   

    public Account() {
    }

    public Account(String username, String password, int iban) {
        this.username = username;
        this.password = PasswordService.getPasswordHash(password);
        this.iban = iban;
        this.balance = 1000;
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

    public void setPassword(String password) { this.password = PasswordService.getPasswordHash(password); }
    
	public int getIban() {
		return iban;
	}
	
	public void setIban(int iban) {
		this.iban = iban;
	}
	
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return String.format(
                "Account[id=%d, user='%s', password='%s iban=%d balance = %f']",
                id, username, password,iban,balance);
    }


}
