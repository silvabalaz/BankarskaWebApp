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


    @Autowired
    public ClientService service; 
   
    @RequestMapping(method = RequestMethod.GET)
    public String clientCreate( Model model) {
    	
        model.addAttribute("clientInfo", new ClientDto());
      
      
        return CREATE_VIEW;
    }
    
    
Get metoda *clientCreate* kreira objekt *ClientDto* za prijenos (DTO- *Data transfer object*) podataka novostvorenog klijenta (username i password) i vraća *view* kako bi korisnik unio svoje podatke u formu i otvorio račun pritiskom gumba. 
Post metoda *create* podatke iz forme na pritisak gumba prosljeđuje podatke iz *ClientDto* objekta u kontruktor Client objekta kako bi se generirao novi korisnik i njegov račun (ako već korisnik ne postoji u bazi, a to provjerim metodom servisa ClientService, *findByUsername* koja vrati različito od *null* ako postoji osoba s tim username-om u bazi). Spremanje korisnika u bazu radim metodom *save* servisa *ClientService*. U bazi se nalazi generirana tablica *Client* koja ima vezu jedan klijent sa jednim računom (svojstvo account u Client objektu sa id-jem u Account objektu).

**LoginController**

povezan je rutom */login* . Metoda *loginForm* prosljeđuje objekt *ClientDto* view-u *client_login*. 
	   
    @RequestMapping(method = RequestMethod.POST)
    public String login(@ModelAttribute("clientInfo")ClientDto clientInfo, Model model,RedirectAttributes redirectAttributes) {

        if(service.isValid(clientInfo.getUsername(), clientInfo.getPassword())) {

            redirectAttributes.addFlashAttribute("clientInfo", clientInfo);
            
            return "redirect:/transactioncreate";
        }

        model.addAttribute("isWrongPassword", true);


        return loginForm(model);
    }


Formom se šalju podaci post metodom *login* , te korištenjem metode *isValid* servisa ClientService ispitujem: stvorene podatke klijenta, je li forma bila popunjena i postoji li u bazi metodom *findByUsername*. Ukoliko klijen već postoji, ulogiravam ga na način da njgove podatke šaljem objektom *redirectAttributes* na rutu */transactioncreate*. Inače popunjavam objekt *model* sa informacijom da je lozinka kriva, te tu informaciju ispisujem u viewu *client_create*.

**TransactionController**

povezan rutom */transactioncreate*. 
Podatci koji stižu objektom redirectAttributes na ovu rutu su podatci o logiranom klijentu (kao parametri metode *Form* , objekt *clientInfo*). Pomoću imena klijenta pretražujem bazu podataka da bi dobila odgovarajući iban računa. To je moguće jer je klijentovo ime jedinstveno na nivou sustava. Spremam iban računa za prikaz u viewu.
Kreiram novi objekt *TransactionDto* koji će služiti u prijenosu podataka iz forme transakcijskog naloga do kreiranja njegovog objekta čije informacije mogu pospremiti u bazu. Te informacije su *properties* objekta *Transaction*: id, sourceAccount, destinationIban, status (zadan,odbije,izvrsen), balance, time, verified.
*sourceAccount* tipa Account je svojstvo po kojem prepoznajemo vlasnika klijenta i veza sa tablicom Account.Ta veza je veza tipa: mnogo transakcija sa jednim računom korisnika (account id-jem).



