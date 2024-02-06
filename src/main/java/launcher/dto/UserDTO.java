package launcher.dto;

import launcher.models.User;
import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
   private User userDetails;
}
