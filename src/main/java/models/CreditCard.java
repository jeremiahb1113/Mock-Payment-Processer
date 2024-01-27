package models;


import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class CreditCard {
    public enum Card_type {
        VISA, AMEX, MASTER_CARD
    }

    /*card number should be encrypted in database
    card number should have XXXX-XXXX-XXXX-XXXX-XXXX format
    card must be active and enabled in order to be used
     */
    @Id
    Long card_number;
    Integer ccv;
    boolean active;
    boolean enabled;
    double funds = 0.00;
    Card_type card_type;

    public CreditCard(Long card_number, Integer ccv, boolean active, boolean enabled, double funds, Card_type card_type){
        this.card_number = card_number;
        this.ccv = ccv;
        this.active = active;
        this.enabled = enabled;
        this.funds = funds;
        this.card_type = card_type;
    }
    public CreditCard(){}

    public Long getCard_number() {
        return card_number;
    }

    public void setCard_number(Long card_number) {
        this.card_number = card_number;
    }

    public Integer getCcv() {
        return ccv;
    }

    public void setCcv(Integer ccv) {
        this.ccv = ccv;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Double getFunds() {
        return funds;
    }

    public void setFunds(Double funds) {
        this.funds = funds;
    }

    public Card_type getCard_type() {
        return card_type;
    }

    public void setCard_type(Card_type card_type) {
        this.card_type = card_type;
    }
}
