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

    //Check how many funds a user has
    //dummy account hardcoded
    @GetMapping("/check")
    public ArrayList<User> checkFunds(){
        ArrayList<User> response = new ArrayList<>();
        User user = new User();

        user.setFirstname("Dante");
        user.setLastname("King");
        user.setUser_id(0L);
        CreditCard card = new CreditCard();
        card.setFunds(7.00);
        //user.setCard(card);
        response.add(user);
        /*
        *Credit card is null at first but when the data
        * is retrieved from the database it sets the results
        * into the credit card ready for the user to view the info
        * it uses the parameters such as firstname, lastname and id
        * to request for the information*/
        return response;
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
    @PostMapping(value = "{user_id}/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String addCardToUser(@PathVariable("user_id") Long id,@RequestBody CreditCard card){


         creditService.addCard(id,card);
         return "card appended to user: " + id;

    }

}
