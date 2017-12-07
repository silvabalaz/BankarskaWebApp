# BankarskaWebApp

Za kreiranje korisničkog računa

    public Client(String username, String password) {
    	
        this.username = username;
        this.password = PasswordService.getPasswordHash(password);
        
        this.account = new Account();
    }
    
koristim klasu Client, sa svim potrebnim getterima i setterima za svojstva klijenta: id (automatsk se generira) , username, password tipa String te tip Account (pripadni korisnički račun koji mu je automatski dodjeljen na način da se instancira pozivom konstruktora objekta tipa Client) implementrian u klasom Account. 
