package mx.com.think.usersms.service;

import mx.com.think.usersms.data.UserEntity;
import mx.com.think.usersms.data.UsersRepository;
import mx.com.think.usersms.shared.UserDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class UsersServiceImpl implements UsersService {

   final
   BCryptPasswordEncoder bCryptPasswordEncoder;

   final UsersRepository usersRepository;


   public UsersServiceImpl(UsersRepository usersRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
      this.usersRepository = usersRepository;
      this.bCryptPasswordEncoder = bCryptPasswordEncoder;
   }

   @Override
   public UserDto createUser(UserDto userDetails) {
      ModelMapper modelMapper = new ModelMapper();
      modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

      userDetails.setUserId(UUID.randomUUID().toString());

      UserEntity userEntity = modelMapper.map(userDetails, UserEntity.class);
      userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));
      usersRepository.save(userEntity);

      return modelMapper.map(userEntity, UserDto.class);
   }

   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      UserEntity user = usersRepository.findByEmail(username);
      if (user == null) throw new UsernameNotFoundException(username);
      return new User(user.getEmail(), user.getEncryptedPassword(), true, true, true, true, new ArrayList<>());
   }

   @Override
   public UserDto getUserDetailsByEmail(String email) {
      UserEntity user = usersRepository.findByEmail(email);
      if (user == null) throw new UsernameNotFoundException(email);
      ModelMapper model = new ModelMapper();
      model.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
      return model.map(user, UserDto.class);
   }
}
