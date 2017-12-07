package bankaccount.web;

public class TransactionDto {
	

    private long sourceIban;
    
    private long destinationIban;
    
    private double amount;

    public TransactionDto(){

    }

    public TransactionDto(long sIban, long dIban , double amount) {

        this.sourceIban = sIban;
        this.destinationIban = dIban;
        this.amount = amount;
    }

    public long getSourceIban() {
        return sourceIban;
    }

    public void setSourceIban(long iban) {
        this.sourceIban = iban;
    }
    
    public long getDestinationIban() {
        return destinationIban;
    }

    public void setDestinationIban(long iban) {
        this.destinationIban = iban;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return String.format(
                "TransactionDto[sourceIban='%d', destinationIban = '%d', amount ='%f']",sourceIban, destinationIban, amount);
    }
	

}
