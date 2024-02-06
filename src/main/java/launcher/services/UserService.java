package launcher.services;

import launcher.models.User;
import launcher.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService  {

    @Autowired
    UserRepository userRepository;


    public User createUser(User userDetails) {
        return userRepository.save(userDetails);
    }


    public User getUser(Long id) {

       return userRepository.selectUser(id);
    }


    public List<User> getAll() {
         List<User> users = userRepository.findAll();
         return users;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public void ModifyUser(String first, String last,Long id) {
         userRepository.modify(first, last,id);
    }
}
