package mx.com.think.usersms.service;

import mx.com.think.usersms.data.UserEntity;
import mx.com.think.usersms.data.UsersRepository;
import mx.com.think.usersms.shared.UserDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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

      UserDto createdUser = modelMapper.map(userEntity, UserDto.class);
      return createdUser;
   }
}
