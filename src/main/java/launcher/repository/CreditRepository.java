package launcher.repository;

import launcher.models.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditRepository extends JpaRepository<CreditCard,Integer> {


    @Query(value = "INSERT INTO card values(?1,?2,?3,?4,?5,?6,?7)", nativeQuery = true)
    void addCard(Integer id, boolean active, boolean card_number, CreditCard.Card_type cardType, Integer ccv, boolean enabled,Integer funds);

    /*
    @Query(value = "SET card (enabled) values(?)", nativeQuery = true)
    void enableCard(boolean set);

    @Query(value = "SET card (active) values(?)", nativeQuery = true)
    void activateCard(boolean set);

     */
}
