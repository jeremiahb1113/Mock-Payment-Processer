package launcher.controllers;


import launcher.models.CreditCard;
import launcher.models.UserDetails;
import launcher.repository.UserRepository;
import launcher.services.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {

    @Autowired
    private UserService userService;


   //this method is intended to return all users
   @GetMapping("/all")
   public List<UserDetails> getAllUsers(){
       return userService.getAll();
   }

   //this method is intended to create a user with no creditcard
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDetails createUser(@RequestBody UserDetails userDetails){
        return userService.createUser(userDetails);
    }

    @GetMapping("/{user_id}")
    public UserDetails singleUser(@PathVariable("user_id") Long id){

       return userService.getUser(id);
    }
    //delete user
    @DeleteMapping("/{user_id}")
    public String deleteUser(@PathVariable("user_id") Long id){
       userService.deleteUser(id);
       return "User has been deleted successfully";
    }
    //update user details
    @PutMapping(value = "/{user_id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateUser(@PathVariable("user_id") Long id, @RequestBody UserDetails userDetails){
       userService.ModifyUser(userDetails.getFirstname(), userDetails.getLastname(),id);


    }
    //

}
