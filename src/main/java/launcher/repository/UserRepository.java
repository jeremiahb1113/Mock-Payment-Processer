package launcher.repository;

import launcher.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query(value = "SELECT * FROM users WHERE user_id = ?", nativeQuery = true)
    User selectUser(Long id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE users SET firstname = ?1, lastname = ?2 WHERE user_id = ?3",nativeQuery = true)
    void modify(String first, String last, Long id);



}
