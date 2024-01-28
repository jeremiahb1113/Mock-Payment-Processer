package launcher.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long user_id;
    String firstname;
    String lastname;
    //marked as Transient because Spring cannot determine the type
    @Transient
    CreditCard card;

    public UserDetails(){}

    public UserDetails(Long user_id, String firstname, String lastname
    , CreditCard card){
        this.user_id = user_id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.card = card;

    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public CreditCard getCard() {
        return card;
    }

    public void setCard(CreditCard card) {
        this.card = card;
    }
}
