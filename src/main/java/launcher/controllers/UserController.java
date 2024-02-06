package launcher.controllers;


import launcher.models.User;
import launcher.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    public User singleUser(@PathVariable("user_id") Long id){

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
    public void updateUser(@PathVariable("user_id") Long id, @RequestBody User userDetails){
       userService.ModifyUser(userDetails.getFirstname(), userDetails.getLastname(),id);


    }
    //

}
