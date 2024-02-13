package launcher.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long user_id;
    private String firstname;
    private String lastname;



    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    List<CreditCard> cards = new ArrayList<>();

    public User(){}

    public User(Long user_id, String firstname, String lastname
    , List<CreditCard> cards){
        this.user_id = user_id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.cards = cards;


    }

    public List<CreditCard> getCards() {
        return cards;
    }

    public void setCards(List<CreditCard> cards) {
        this.cards = cards;
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

}
