# BankarskaWebApp

Za kreiranje korisničkog računa

    public Client(String username, String password) {
    	
        this.username = username;
        this.password = PasswordService.getPasswordHash(password);
        
        this.account = new Account();
    }
    
koristim klasu Client, sa svim potrebnim getterima i setterima za svojstva klijenta: id (automatsk se generira) , username, password tipa String (password je kriptiran u heksadecimalan broj, metodom sha1hex u PaswordService-u) ,te tip Account (pripadni korisnički račun koji mu je automatski dodjeljen na način da se instancira pozivom konstruktora objekta tipa Client) implementrian klasom Account. 
Account ima inicjalnu vrijednost 1000 novaca

    public Account() {
    	
        this.iban = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
        this.balance = 1000;
    }
    
jer mu je u konstruktoru balance svojstvo inicijalizirano na 1000, a svojstvo iban je broj tipa long , duljine 10 znamenaka koristeći funkciju random() koja bira nasumično prirodne brojeve, a množeći ju sa velikim brojem određene duljine dobivamo duljinu broja od 10 znamenaka.


