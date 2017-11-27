package bankaccount.web;

public class TransactionDto {
	

    private int sourceIban;
    
    private int destinationIban;
    
    private double amount;

    public TransactionDto(){

    }

    public TransactionDto(int sIban, int dIban , double amount) {

        this.sourceIban = sIban;
        this.destinationIban = dIban;
        this.amount = amount;
    }

    public int getSourceIban() {
        return sourceIban;
    }

    public void setSourceIban(int iban) {
        this.sourceIban = iban;
    }
    
    public int getDestinationIban() {
        return destinationIban;
    }

    public void setDestinationIban(int iban) {
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
