package mx.com.think.usersms.service;


import mx.com.think.usersms.shared.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsersService extends UserDetailsService {

   UserDto createUser(UserDto userDetails);
   UserDto getUserDetailsByEmail(String email);
}
