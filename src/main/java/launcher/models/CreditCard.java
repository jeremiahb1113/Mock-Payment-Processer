package launcher.models;




import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "cards")
public class CreditCard {
    @Id
    //@GeneratedValue(generator = "system-uuid")
    //@GenericGenerator(name = "system-uuid", strategy = "uuid")
    String id = UUID.randomUUID().toString();
    Long card_number;
    Integer ccv;
    boolean active;
    boolean enabled;
    double funds = 0.00;
    String card_type;
    Long user_identification;
    //@ManyToOne subject can only have 1 teacher, but teacher can have man subjects
    //this is subject, it can have only one User
    //@JoinColumn(name = "id",referencedColumnName = "user_id")

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user_owner;




    public CreditCard(Long user_identification,String id, Long card_number, Integer ccv, boolean active, boolean enabled, double funds, String card_type){
        this.id = id;
        this.card_number = card_number;
        this.ccv = ccv;
        this.active = active;
        this.enabled = enabled;
        this.funds = funds;
        this.card_type = card_type;
        this.user_identification = user_identification;

    }
    public CreditCard(){}

    public Long getUser_identification() {
        return user_identification;
    }

    public void setUser_identification(Long user_identification) {
        this.user_identification = user_identification;
    }

    public User getUser_owner() {
        return user_owner;
    }

    public void setUser_owner(User user_owner) {
        this.user_owner = user_owner;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFunds(double funds) {
        this.funds = funds;
    }

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

    public String getCard_type() {
        return card_type;
    }

    public void setCard_type(String card_type) {
        this.card_type = card_type;
    }
}
