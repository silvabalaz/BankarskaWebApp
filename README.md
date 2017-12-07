# BankarskaWebApp

Kreiranje korisničkog računa

    public Client(String username, String password) {
    	
        this.username = username;
        this.password = PasswordService.getPasswordHash(password);
        
        this.account = new Account();
    }
