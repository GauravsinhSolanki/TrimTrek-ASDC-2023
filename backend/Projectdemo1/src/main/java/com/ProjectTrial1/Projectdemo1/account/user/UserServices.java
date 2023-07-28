package com.ProjectTrial1.Projectdemo1.account.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.RandomStringUtils;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class UserServices {

    private static final Logger LOG = LoggerFactory.getLogger(UserServices.class);

    @Autowired
    UserRepository userRepo;

    @Autowired
    private HttpSession httpSession;


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
        String generatedString = generateUniqueUserId();
        user.setUserId(generatedString);
        user.setUserName(userDto.getUserName());
        user.setUserPassWord(userDto.getUserPassWord());
        user.setGender(userDto.getGender());
        user.setDob(userDto.getDob());
        user.setPhoneNo(userDto.getPhoneNo());
        user.setAltPhoneNo(userDto.getAltPhoneNo());
        user.setEmailId(userDto.getEmailId());
        user.setCreatedOn(LocalDateTime.now());
        LOG.debug("convertUserDtotoEntity, user: " + user);
        return user;
    }
    private String generateUniqueUserId() {
        String generatedUserId = RandomStringUtils.random(11, true, true);
        return generatedUserId;
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
        userDto.setUserPassWord(user.getUserPassWord());
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



//	public User blockUser(String userId) {
//		LOG.debug("blockUser, userId: " + userId);
//		User user = userRepo.findByEmailId(userId);
//
//		user.setActive(false);
//		userRepo.save(user);
//
////		 List<UserRole> userRoles = userRoleRepo.findByUserId(user.getId()); TODO
////		 for(UserRole userRole : userRoles ) {
////			 userRole.setActive(false);
////			 userRoleRepo.save(userRole);
////		 }
//		gardenServices.inActivategardensByUserId(userId);
//		addressServices.inActivateAddressByUserId(userId);
//		LOG.debug("blockUser, user: " + user);
//		return user;
//	}

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
        httpSession.setAttribute("user", user);
        return ResponseEntity.ok("Sign-in successful!");
    }


    public ResponseEntity<String> signOut() {

        if (httpSession.getAttribute("user") != null) {

            httpSession.removeAttribute("user");
            httpSession.invalidate();
        }

        return ResponseEntity.ok("Sign-out successful!");
    }




    public User createBarberProfile(BarberDto barberDto) {
        LOG.debug("barberProfile, barberDto: " + barberDto);

        User user =  userRepo.findByEmailId(barberDto.getEmailId());
        System.out.println(user);
        user.setSpeciality(barberDto.getSpeciality());
        System.out.println(user);
        User dbuser = userRepo.update(user);
        LOG.debug("createUser, dbuser: " + dbuser);
        return dbuser;
    }



    public BarberProfileDto getBarberProfileByEmailId(String emailId) {
        LOG.debug("getBarberProfileByEmailId, emailId: " + emailId);
        User user = userRepo.findByEmailId(emailId);
        BarberProfileDto dto = convertUserEntitytoBarberProfileDto(user);
        LOG.debug("getBarberProfileByEmailId, dto: " + dto);
        return dto;
    }

    public BarberProfileDto convertUserEntitytoBarberProfileDto(User user) {
        LOG.debug("convertUserEntitytoBarberProfileDto, user: " + user);
        BarberProfileDto barberProfileDto = new BarberProfileDto();

        barberProfileDto.setUserName(user.getUserName());
        barberProfileDto.setGender(user.getGender());
        barberProfileDto.setDob(user.getDob());
        barberProfileDto.setPhoneNo(user.getPhoneNo());
        barberProfileDto.setAltPhoneNo(user.getAltPhoneNo());
        barberProfileDto.setEmailId(user.getEmailId());
        barberProfileDto.setSpeciality(user.getSpeciality());
        LOG.debug("convertUserEntitytoBarberProfileDto, barberProfileDto: " + barberProfileDto);
        return barberProfileDto;
    }
}
