package launcher.services;

import launcher.dto.UserDTO;
import launcher.models.UserDetails;
import launcher.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService  {

    @Autowired
    UserRepository userRepository;


    public UserDetails createUser(UserDetails userDetails) {
        return userRepository.save(userDetails);
    }

    public UserDetails getUser(Long id) {

       return userRepository.selectUser(id);
    }


    public List<UserDetails> getAll() {
         List<UserDetails> users = userRepository.findAll();
         return users;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public void ModifyUser(String first, String last,Long id) {
         userRepository.modify(first, last,id);
    }
}
