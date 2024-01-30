package launcher.repository;

import launcher.models.CreditCard;
import launcher.models.UserDetails;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<UserDetails,Long> {

    @Query(value = "SELECT * FROM users WHERE id = ?", nativeQuery = true)
    UserDetails selectUser(Long id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE users SET firstname = ?1, lastname = ?2 WHERE id = ?3",nativeQuery = true)
    void modify(String first, String last, Long id);

}
