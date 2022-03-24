package com.mindhub.homebanking.config;



import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toList;
import com.mindhub.homebanking.dtos.ClientDTO;
import com.mindhub.homebanking.dtos.TransactionDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class InitController {


    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping({"/index.html", "/"})
    public String index(Model model){
        return "index.html";
    }
/*
    @GetMapping({  "/account/{id}"})//"/account", "http://localhost:8080/account.html?id=", "account.html?id", "/account.html?id=", "/account.html?id=",
    public String account(Account account,Model model)  {//@PathVariable Long id,
        Client client = clientRepository.findById(account.getId()).get();
        model.addAttribute("account", account);
        System.out.println("id: "+account.getId());

        return "account";
    }
*/
    @GetMapping( "/account/{id}")
    public String account( Model model, @PathVariable("id") Long id){

        model.addAttribute("account", accountRepository.getById(id));
        model.addAttribute("transactions", transactionRepository.findAllByAccount(accountRepository.getById(id)));

        return "account";
    }
    @GetMapping({"/survey/{id}"})
    public String survey(Model model, @PathVariable("id") Long id){
        model.addAttribute("client", clientRepository.getById(id));
        return "survey";
    }
/*
    //funciona pero no recibe el mensaje
    @GetMapping({ "/account.html","/account" })
    public String account( Model model){

        //model.addAttribute("account", accountRepository.findById(id));
        System.out.println("cuenta");
        return "account";
    }
*/
    @GetMapping({"/accounts.html"})
    public String accounts(Model model){
    return "accounts";
}
    @GetMapping({"/login.html"})
    public String login(Model model){
        return "login.html";
    }



    @GetMapping({"/products.html"})
    public String products(Model model){
        return "products.html";
    }

    @GetMapping({"/create-invest.html"})
    public String createInvest(Model model){
        return "create-invest.html";
    }


    @GetMapping({"/cards.html"})
    public String cards(Model model){
        return "cards";
    }

    @GetMapping({"/view-invests.html"})
    public String viewInvests(Model model){
        return "view-invests";
    }


    @GetMapping({"/transfers.html"})
    public String transfer(Model model){
        return "transfers";
    }


    @GetMapping({"/create-cards.html"})
    public String createCards(Model model){
        return "create-cards";
    }

    @GetMapping({"/loan.html"})
    public String loan(Model model){
        return "loan";
    }


    @GetMapping({"/loan-application.html"})
    public String loanApplication(Model model){
        return "loan-application";
    }

    @GetMapping({"/invest-application.html"})
    public String investApplication(Model model){
        return "invest-application";
    }

    /*
    @GetMapping({"/error"})
    public String error(Model model){
        System.out.println("no existe la ruta");
        return "index";
    }
*/
}


