package bankaccount.web;

public class ClientDto {
	

    private String username;
    private String password;

    public ClientDto(){

    }

    public ClientDto(String username, String password) {

        this.username = username;
        this.password = password;
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

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return String.format(
                "ClientDto[user='%s', password='%s']", username, password);
    }
	

}
