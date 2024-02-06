package launcher.repository;

import launcher.models.CreditCard;
import launcher.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CreditRepository extends JpaRepository<CreditCard,Long> {

/*
    @Query(value = "INSERT INTO card VALUES active = ?1, card_number = ?2, card_type = ?3, ccv = ?4, enabled = ?5 WHERE id = ?6", nativeQuery = true)
    void addCard( boolean active, Long card_number, CreditCard.Card_type cardType, Integer ccv, boolean enabled, Long id);
*/



    //@Query(value = "INSERT INTO card (active, card_number, card_type, ccv, enabled) VALUES(?1,?2,?3,?4,?5)", nativeQuery = true)
    //void addCard( boolean active, Long card_number, String cardType, Integer ccv, boolean enabled, Long id);

    /*
    @Query(value = "SET card (enabled) values(?)", nativeQuery = true)
    void enableCard(boolean set);

    @Query(value = "SET card (active) values(?)", nativeQuery = true)
    void activateCard(boolean set);

     */

    //add card to User
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO cards (id,card_number,ccv,active,enabled,funds,card_type,user_id) VALUES (?1,?2,?3,?4,?5,?6,?7,?8)", nativeQuery = true)
    void appendCard(Long id,Long card_number,Integer ccv, boolean active,
                    boolean enabled, double funds, String card_type, Long user_id);
}
