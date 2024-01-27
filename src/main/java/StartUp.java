
import models.CreditCard;
import models.UserDetails;

public class StartUp {
    public static void main(String[] args) {
        System.out.println("Starting");
        UserDetails user = new UserDetails();
        CreditCard card = new CreditCard();
        card.setCard_number(1111222233334444L);
        card.setCard_type(CreditCard.Card_type.VISA);
        card.setActive(true);
        card.setEnabled(true);
        card.setCcv(123);
        card.setFunds(100.00);

        user.setUser_id(1l);
        user.setFirstname("Alshon");
        user.setLastname("King");
        user.setCard(card);

        System.out.println("user detils: " + user.getCard().getFunds());
    }
}
