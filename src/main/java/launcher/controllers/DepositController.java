package launcher.controllers;

import launcher.models.CreditCard;
import launcher.models.User;
import launcher.services.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/funds")
public class DepositController {

    @Autowired
    private CreditService creditService;

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("payment/{id}")
    public void paymentTransaction(@PathVariable("id") Long id, @RequestBody CreditCard creditCard){
        creditService.pay(id, creditCard.getFunds());
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("deposit/{id}")
    public void depositTransaction(@PathVariable("id") Long id, @RequestBody CreditCard creditCard){
        creditService.deposit(id, creditCard.getFunds());
    }


    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/activate/{id}")
    public void activeCard(@PathVariable("id") Long id){
        creditService.activeCard(id);

    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/deactivate/{id}")
    public void deActiveCard(@PathVariable("id") Long id){
        creditService.deactiveCard(id);

    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/enable/{id}")
    public void enableCard(@PathVariable("id") Long id){
        creditService.enableCard(id);

    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/disable/{id}")
    public void DisableCard(@PathVariable("id") Long id){
        creditService.disableCard(id);

    }



    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Optional<CreditCard> getFunds(@PathVariable("id") Long id){

       return creditService.creditRepository.findById(id);
    }


    //add body in json for credit card
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public String createCard( @RequestBody CreditCard creditCard){
        creditService.makeCard(creditCard);
        return "card added";
    }



    //go into user as pathvariable and then add card into database
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "{user_id}/append", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String addCardToUser(@PathVariable("user_id") Long id,@RequestBody CreditCard card){


         creditService.addCard(id,card);
         return "card appended to user: " + id;

    }


    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "{user_id}/delete/card")
    public void deleteCard(@PathVariable("user_id") Long id){
        creditService.deleteCard(id);
    }

}
