package launcher.repository;

import launcher.models.CreditCard;
import launcher.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;

import javax.transaction.Transactional;

@Repository
public interface CreditRepository extends JpaRepository<CreditCard,String> {


    //add card to User
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO cards (id,card_number,ccv,active,enabled,funds,card_type,user_id) VALUES (?1,?2,?3,?4,?5,?6,?7,?8)", nativeQuery = true)
    void appendCard(String id,Long card_number,Integer ccv, boolean active,
                    boolean enabled, Double funds, String card_type, Long user_id);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO users (card_number,ccv,active,enabled,funds,card_type,user_id) VALUES (?1,?2,?3,?4,?5,?6,?7)", nativeQuery = true)
    void appendUser(Long card_number,Integer ccv, boolean active,
                    boolean enabled, double funds, String card_type, Long user_id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE ONLY cards SET active = true WHERE cards.id = ?",nativeQuery = true)
    void activateCard(String id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE ONLY cards SET active = false WHERE cards.id = ?",nativeQuery = true)
    void deactivateCard(String id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE cards SET enabled = true WHERE cards.id = ?",nativeQuery = true)
    void enableCard(String id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE cards SET enabled = false WHERE cards.id = ?", nativeQuery = true)
    void disableCard(String id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE cards SET funds = (funds + ?1) FROM users WHERE cards.id = ?2", nativeQuery = true)
    void add(Double funds,String id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE cards SET funds = (funds - ?1) FROM users WHERE cards.id = ?2",nativeQuery = true,countQuery = "1")
    void subtract(Double funds,String id);

    @Query(value = "SELECT active FROM cards WHERE id = ?",nativeQuery = true)
    boolean checkActive(String id);

    @Query(value = "SELECT enabled FROM cards WHERE id = ?",nativeQuery = true)
    boolean checkEnabled(String id);

    @Query(value = "SELECT funds FROM cards WHERE id = ?", nativeQuery = true)
    double funds(String id);

    @Query(value = "SELECT card_type FROM cards WHERE id = ?",nativeQuery = true)
    String checkIsCredit(String id);
}
