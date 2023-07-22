package org.example.account.user;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class UserServices {

    private static final Logger LOG = LoggerFactory.getLogger(UserServices.class);

    @Autowired
    UserRepository userRepo;


    public User createUser(UserDto userDto) {
        LOG.debug("createUser, userDto: " + userDto);
        User user = convertUserDtotoEntity(userDto);
        User dbuser = userRepo.save(user);
        LOG.debug("createUser, dbuser: " + dbuser);
        return dbuser;
    }

    private User convertUserDtotoEntity(UserDto userDto) {
        LOG.debug("convertUserDtotoEntity, userDto: " + userDto);
        User user = new User();
        String generatedString = RandomStringUtils.random(11, true, true);
        user.setUserId(generatedString);
        user.setUserName(userDto.getUserName());
        user.setUserPassWord(user.getUserPassWord());
        user.setGender(userDto.getGender());
        user.setDob(userDto.getDob());
        user.setPhoneNo(userDto.getPhoneNo());
        user.setAltPhoneNo(userDto.getAltPhoneNo());
        user.setEmailId(userDto.getEmailId());
        user.setCreatedOn(LocalDateTime.now());
        LOG.debug("convertUserDtotoEntity, user: " + user);
        return user;
    }


    public List<UserDto> getAllUsers() {
        LOG.debug("getAllUsers,  ");
        List<UserDto> dtos = new ArrayList<>();

        List<User> users = userRepo.findAll();

        for (User user : users) {
            UserDto dto = convertUserEntitytoDto(user);
            dtos.add(dto);
        }
        LOG.debug("getAllUsers, dtos: " + dtos);
        return dtos;
    }

    public UserDto convertUserEntitytoDto(User user) {
        LOG.debug("convertUserEntitytoDto, user: " + user);
        UserDto userDto = new UserDto();

        userDto.setUserName(user.getUserName());
        userDto.setPassWord(user.getUserPassWord());
        userDto.setGender(user.getGender());
        userDto.setDob(user.getDob());
        userDto.setPhoneNo(user.getPhoneNo());
        userDto.setAltPhoneNo(user.getAltPhoneNo());
        userDto.setEmailId(user.getEmailId());
        LOG.debug("convertUserEntitytoDto, userDto: " + userDto);
        return userDto;
    }



    public UserDto getUserByUserId(String userId) {
        LOG.debug("getUserByUserId, userId: " + userId);
        User User = userRepo.findByUserId(userId);

        UserDto dto = convertUserEntitytoDto(User);
        LOG.debug("getUserByUserId, dto: " + dto);
        return dto;
    }


	public UserDto getUserByEmailId(String emailId) {
		LOG.debug("getUserByEmailId, emailId: " + emailId);
		User user = userRepo.findByEmailId(emailId);
		UserDto dto = convertUserEntitytoDto(user);
		LOG.debug("getUserByEmailId, dto: " + dto);
		return dto;
	}

	public String getUserIdByEmailId(String emailId) {
		LOG.debug("getUserIdByEmailId, emailId: " + emailId);
		User user = userRepo.findByEmailId(emailId);
		String userId = user.getEmailId();
		LOG.debug("getUserIdByEmailId, userId: " + userId);
		return userId;
	}

	public String getEmailIdByUserId(String userId) {
		LOG.debug("getEmailIdByUserId, userId: " + userId);
		User user = userRepo.findByUserId(userId);
		String emailId = user.getEmailId();
		LOG.debug("getEmailIdByUserId, emailId: " + emailId);
		return emailId;
	}

    public String getPasswordByEmailId(String emailId){
        LOG.debug("getPasswordByEmailId, emailId: " + emailId);
        User user = userRepo.findByEmailId(emailId);
        String passWord = user.getUserPassWord();
        LOG.debug("getPasswordByEmailId, passWord: " + passWord);
        return passWord;
    }

    public ResponseEntity<String> signIn(String emailId, String userPassWord) {
        LOG.debug("signIn, emailId: " + emailId);
        LOG.debug("signIn, userPassWord: " + userPassWord);
       User user = userRepo.findByEmailId(emailId);

        if (user == null) {
            return ResponseEntity.badRequest().body("Invalid username.");
        }

       String passWord = getPasswordByEmailId(emailId);

        if (!(passWord.equals(userPassWord) )){
            return ResponseEntity.badRequest().body("Invalid password.");
        }

        return ResponseEntity.ok("Sign-in successful!");
    }



    public User createBarberProfile(BarberDto barberDto) {
        LOG.debug("barberProfile, barberDto: " + barberDto);

        User user =  userRepo.findByEmailId(barberDto.getEmailId());
        user.setSpeciality(barberDto.getSpeciality());
        User dbuser = userRepo.save(user);
        LOG.debug("createUser, dbuser: " + dbuser);
        return dbuser;
    }



}
