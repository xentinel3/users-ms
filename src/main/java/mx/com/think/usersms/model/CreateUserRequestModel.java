package mx.com.think.usersms.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
public class CreateUserRequestModel {

   @NotNull(message = "First name cannot be null")
   @Size(min=2, message = "First name must not be less than 2 characters")
   private String firstName;
   @NotNull(message = "Last name cannot be null")
   @Size(min=2, message = "Last name must not be less than 2 characters")
   private String lastName;
   @NotNull(message = "Password name cannot be null")
   @Size(min=8, max = 20, message = "Password must not be equal or greater than 8 characters and less than 20")
   private String password;
   @NotNull(message = "Email name cannot be null")
   @Email
   private String email;
}
