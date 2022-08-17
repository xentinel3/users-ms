package mx.com.think.usersms.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name="users", schema = "public")
public class UserEntity implements Serializable {
   private static final long serialVersionUID = -9138424249763040513L;

   @Id
   @GeneratedValue
   private Long id;
   @Column(nullable = false, length = 50)
   private String firstName;
   @Column(nullable = false, length = 50)
   private String lastName;
   @Column(nullable = false, length = 120, unique = true)
   private String email;
   @Column(nullable = false, unique = true)
   private String userId;
   @Column(nullable = false, unique = true)
   private String encryptedPassword;
}
