package mx.com.think.usersms.shared;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserDto implements Serializable {
   private static final long serialVersionUID = -6478009604421719862L;
   private String firstName;
   private String lastName;
   private String password;
   private String email;
   private String userId;
   private String encryptedPassword;
}
