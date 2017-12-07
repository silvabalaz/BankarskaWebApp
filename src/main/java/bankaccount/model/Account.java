package bankaccount.model;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

@Entity
public class Account {
	
    @Id
    @NotNull
    @Column(name="ACCOUNT_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
  
    //@Size(max=10)
    private long iban; 
    
    private double balance;
    
    @OneToMany(mappedBy="sourceAccount", cascade = CascadeType.ALL)
    private List<Transaction> transactions; 
   
    public Account() {
    	
        this.iban = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
        this.balance = 1000;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
	public long getIban() {
		return iban;
	}
	
	public void setIban(long iban) {
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
                "Account[id=%d,balance = %f']", id,balance);
    }


}
