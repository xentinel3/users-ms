package mx.com.think.usersms.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserResponseModel {
   private String firstName;
   private String lastName;
   private String userId;
   private String email;
}
