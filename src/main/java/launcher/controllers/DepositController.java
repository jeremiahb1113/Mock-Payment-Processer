package launcher.controllers;

import launcher.models.CreditCard;
import launcher.models.UserDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@RestController
public class DepositController {


    //Check how many funds a user has
    //dummy account hardcoded
    @GetMapping("/funds")
    public ArrayList<UserDetails> checkFunds(){
        ArrayList<UserDetails> response = new ArrayList<>();
        UserDetails user = new UserDetails();

        user.setFirstname("Dante");
        user.setLastname("King");
        user.setUser_id(0L);
        CreditCard card = new CreditCard();
        card.setFunds(7.00);
        user.setCard(card);
        response.add(user);
        /*
        *Credit card is null at first but when the data
        * is retrieved from the database it sets the results
        * into the credit card ready for the user to view the info
        * it uses the parameters such as firstname, lastname and id
        * to request for the information*/
        return response;
    }

    @GetMapping("funds/{id}/{firstname}/{lastname}")
    public ResponseEntity<UserDetails> getFunds(@PathVariable("id") Long id,
                                                @PathVariable("firstname") String firstname,
                                                @PathVariable("lastname") String lastname){

        UserDetails user = new UserDetails();
        user.setUser_id(id);
        user.setFirstname(firstname);
        user.setLastname(lastname);

        HttpHeaders headers = new HttpHeaders();

        ResponseEntity<UserDetails> entity = new ResponseEntity<>(user,headers, HttpStatus.CREATED);
        return entity;

    }

    @GetMapping("/hello")
    public String helloWorld(){
        return "Ello Worlda";
    }

}
