package bankaccount.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import bankaccount.service.PasswordService;
import bankaccount.service.TimeService;

@Entity
public class Transaction {
	
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull(message = "Broj računa primatelja je obavezno")
    private int sourceIban;

    @NotNull(message = "Broj računa izvršitelja je obavezno")
    private int destinationIban;
    
    @NotEmpty(message = "Status računa je obavezan")
    private String status; 
    
    @NotNull(message = "Iznos transakcije je obavezan")
    private double balance;
   
    private String time;

    public Transaction() {
    }

    public Transaction(int source, int dest, String status, double balance) {
    	
        this.sourceIban = source;
        this.destinationIban = dest;
        this.status = status;
        this.balance = balance;
        this.time = TimeService.currentTime();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCurrentTime() {
        return time;
    }

    public void setCurrentTime(String time) {
        this.time = time;
    }

    public int getSourceIban() {
        return sourceIban;
    }
    
    public void setSourceIban(int iban) { this.sourceIban = sourceIban; }

	public int getDestinationIban() {
		
		return destinationIban;
	}
	
    public void setDestinationIban(int iban) { this.destinationIban = destinationIban; }
    
	public String getStatus() {
		return status;
	}
    
    public void setStatus(String status) {
		this.status = status;
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
                "Transaction[id=%d, sourceIban='%d', destinationIban='%d ,status=%s, balance = %f, time=%s']",
                id, sourceIban,destinationIban,status,balance,time);
    }


}