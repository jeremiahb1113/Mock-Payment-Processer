package launcher.controllers;

import launcher.models.CreditCard;
import launcher.models.User;
import launcher.services.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> paymentTransaction(@PathVariable("id") String id, @RequestBody CreditCard creditCard){
        ResponseEntity<String> responseEntity;

        if(creditService.checkFunds(id) < creditCard.getFunds() && !creditService.isCredit(id)){
            return responseEntity = new ResponseEntity<>("Insufficient funds", HttpStatus.FORBIDDEN);
        }

        if(creditService.isActive(id) && creditService.isEnabled(id) ){
            creditService.pay(id, creditCard.getFunds());
           return responseEntity = new ResponseEntity<String>("Transaction Successful",HttpStatus.OK);
        }

        if(!creditService.isActive(id) && !creditService.isEnabled(id)){
            return responseEntity = new ResponseEntity<>("Card is currently disabled and not activated",HttpStatus.FORBIDDEN);
        }

        if(creditService.isActive(id) && !creditService.isEnabled(id)){
            return responseEntity = new ResponseEntity<>("Card is currently disabled",HttpStatus.FORBIDDEN);
        }

        if(!creditService.isActive(id) && creditService.isEnabled(id)){
            return responseEntity = new ResponseEntity<>("Card is currently not activated",HttpStatus.FORBIDDEN);
        }

        return responseEntity = new ResponseEntity<String>("Transaction Failed",HttpStatus.INTERNAL_SERVER_ERROR);
    }



    @ResponseStatus(HttpStatus.OK)
    @PutMapping("deposit/{id}")
    public ResponseEntity<String> depositTransaction(@PathVariable("id") String id, @RequestBody CreditCard creditCard){

        if(creditService.isActive(id) && creditService.isEnabled(id) && creditService.checkCardExist(id)){
            creditService.deposit(id, creditCard.getFunds());
            return new ResponseEntity<String>("Deposit Successful",HttpStatus.OK);
        }
        else if(!creditService.checkCardExist(id)) return new ResponseEntity<String>("User or Card does not exist",HttpStatus.NOT_FOUND);
        else if(!creditService.isActive(id)) return new ResponseEntity<String>("Credit card is not active",HttpStatus.FORBIDDEN);
        else if (!creditService.isEnabled(id)) return new ResponseEntity<String>("Credit card is not enabled",HttpStatus.FORBIDDEN);
        else return new ResponseEntity<String>("An error occurred, please try again",HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/activate/{id}")
    public void activeCard(@PathVariable("id") String id){
        creditService.activeCard(id);

    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/deactivate/{id}")
    public void deActiveCard(@PathVariable("id") String id){
        creditService.deactiveCard(id);

    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/enable/{id}")
    public void enableCard(@PathVariable("id") String id){
        creditService.enableCard(id);

    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/disable/{id}")
    public void DisableCard(@PathVariable("id") String id){
        creditService.disableCard(id);

    }



    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Optional<CreditCard> getFunds(@PathVariable("id") String id){

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
    public void deleteCard(@PathVariable("user_id") String id){
        creditService.deleteCard(id);
    }

}
