package launcher.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    Long user_id;
    String firstname;
    String lastname;
     //Long card_ID;
    //creditcard is another class mapped as a One to One
    //relationship in SQL we need it to be labeled
    //otherwise it will not be able to determine the type
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "card_ID", referencedColumnName = "id")
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
