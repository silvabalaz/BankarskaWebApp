package bankaccount.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;
import bankaccount.service.Util;

@Entity
public class Transaction {
	
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull(message = "Račun platitelja je obavezan.")
    @ManyToOne
    @JoinColumn(name = "ACCOUNT_ID")
    private Account sourceAccount;

    @NotNull(message = "Broj računa primatelja je obavezno")
    //@Size(min = 10, max = 10, message = "IBAN računa primatelja mora biti deset znamenki.")
    private long destinationIban;
    
    @NotEmpty(message = "Status računa je obavezan.")
    private String status; 
    
    @NotNull(message = "Iznos transakcije je obavezan.")
    private double balance;
   
    private String time;
    
    boolean verified;

    public Transaction() {
    }

    public Transaction(Account source, long dest, String status, double balance) {
    	
        this.sourceAccount = source;
        this.destinationIban = dest;
        this.status = status;
        this.balance = balance;
        this.time = Util.currentTime();
        this.verified = false;
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

    public Account getSourceAccount() {
        return sourceAccount;
    }
    
    public void setSourceAccount(Account sourceAccount) { this.sourceAccount = sourceAccount; }

	public long getDestinationIban() {
		
		return destinationIban;
	}
	
    public void setDestinationIban(long destinationIban) { this.destinationIban = destinationIban; }
    
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
    
    public boolean getVerified() {
        return verified;
    }

    public void setVerified(boolean verified){
        this.verified = verified;
    }

    @Override
    public String toString() {
        return String.format(
                "Transaction[id=%d, sourceIban='%d', destinationIban='%d ,status=%s, balance = %f, time=%s, verified='%b']",
                id, sourceAccount.getIban(),destinationIban,status,balance,time,verified);
    }




}