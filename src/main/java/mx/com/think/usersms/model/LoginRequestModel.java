package mx.com.think.usersms.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestModel {
   private String email;
   private String password;
}
