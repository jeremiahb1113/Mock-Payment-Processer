package launcher.controllers;


import launcher.models.User;
import launcher.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {

    @Autowired
    private UserService userService;

   //this method is intended to return all users
   @GetMapping("/all")
   public List<User> getAllUsers(){
       return userService.getAll();
   }

   //this method is intended to create a user with no creditcard
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public User createUser(@RequestBody User userDetails){

       return userService.createUser(userDetails);

    }



    @GetMapping("/{user_id}")
    public ResponseEntity<User> singleUser(@PathVariable("user_id") Long id){
       ResponseEntity<User> user = new ResponseEntity<User>(userService.getUser(id),HttpStatus.OK);
       if(user.getBody() == null) {
           return new ResponseEntity<User>(userService.getUser(id),HttpStatus.NOT_FOUND);
       }
       return user;
    }

    @DeleteMapping("/{user_id}")
    public ResponseEntity<String> deleteUser(@PathVariable("user_id") Long id){



       if(userService.doesUserExist(id)){
           userService.deleteAllCards(id);
           userService.deleteUser(id);
           return new ResponseEntity<String>("User with id: " + id + " has been successfully removed",HttpStatus.OK);
       }
       else if(!userService.doesUserExist(id)){
           return new ResponseEntity<String>("User does not exist",HttpStatus.NOT_FOUND);
       }

       return new ResponseEntity<String>("An error occurred",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping(value = "/{user_id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateUser(@PathVariable("user_id") Long id, @RequestBody User userDetails){

            if(userService.doesUserExist(id)){
               userService.ModifyUser(userDetails.getFirstname(), userDetails.getLastname(),id);
               return new ResponseEntity<String>("User has successfully been modified",HttpStatus.OK);
                }
        else if (!userService.doesUserExist(id)){
           //User does not exist, return a 404
                return new ResponseEntity<String>("User does not exist",HttpStatus.NOT_FOUND);
        }
        else return new ResponseEntity<String>("An error occurred, please try again",HttpStatus.INTERNAL_SERVER_ERROR);
        }


}
