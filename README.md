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


Početna stranica aplikacije ostvarena templeat-om *index.html* nudi tri linka: kreiranje klijenta, login i napravi transakciju.
Korištenje elemenata bootstrapa kroz ostale templeate ostvarujem tako da includam Bootstrap u *index.html*, a datoteke Javascripta i Bootstrapa se nalaze u static folderu projekta.


**NewClientController** označen notacijom *@Controller* povezan je s rutom */clientcreate* uz notaciju *RequestMapping* i koristi metode *ClientService*-a korištenjem notacije *@Autowired* prije deklaracije servisa.

Get metoda *clientCreate* kreira objekt *ClientDto* za prijenos (DTO- *Data transfer object*) podataka novostvorenog klijenta (username i password) i vraća *view* kako bi korisnik unio svoje podatke u formu i otvorio račun pritiskom gumba. 
Post metoda *create* podatke iz forme na pritisak gumba prosljeđuje podatke iz *ClientDto* objekta u kontruktor Client objekta kako bi se generirao novi korisnik i njegov račun (ako već korisnik ne postoji u bazi, a to provjerim metodom servisa ClientService, *findByUsername* koja vrati različito od *null* ako postoji osoba s tim usernameom u bazi). Spremanje korisnika u bazu radim metodom *save* servisa *ClientService*. 

****
