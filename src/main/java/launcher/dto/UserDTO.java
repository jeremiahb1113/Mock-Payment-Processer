package launcher.dto;

import launcher.models.CreditCard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    Long id;
    String firstname;
    String lastname;
    CreditCard card;
}
