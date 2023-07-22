package org.example.account.userrole;

import org.example.account.role.RoleRepository;
import org.example.account.user.User;
import org.example.account.user.UserDto;
import org.example.account.user.UserRepository;
import org.example.account.user.UserServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class UserRoleServices {

    private static final Logger LOG = LoggerFactory.getLogger(UserRoleServices.class);

    @Autowired
    UserRepository userRepo;

    @Autowired
    RoleRepository roleRepo;

    @Autowired
    UserRoleRepository userRoleRepo;


    @Autowired
    UserServices userServices;

//    @Autowired
//    AddressServices addressServices;
//
//    @Autowired
//    GardeningTaskServices gardeningTaskServices;


    public List<UserRole> createUserRole(UserRoleDto userRoleDto) {
        LOG.debug("createUserRole, userRoleDto: " + userRoleDto);
        List<UserRole> userRoles = convertUserRoleDtotoEntity(userRoleDto);

        List<UserRole> dbUserRoles = userRoleRepo.saveAll(userRoles);
        LOG.debug("createUserRole, dbUserRoles: " + dbUserRoles);
        return dbUserRoles;
    }

    private List<UserRole> convertUserRoleDtotoEntity(UserRoleDto userRoleDto) {
        LOG.debug("convertUserRoleDtotoEntity, userRoleDto: " + userRoleDto);
        List<UserRole> userRoles = new ArrayList<>();


        for (String roleId : userRoleDto.getRoleId()) {

            UserRole userRole = new UserRole();
            userRole.setUser(userRepo.findByEmailId(userRoleDto.getUserId()));//TODO
            userRole.setRole(roleRepo.findByRoleId(roleId));
            userRole.setCreatedOn(LocalDateTime.now());

            userRoles.add(userRole);
        }

        LOG.debug("convertUserRoleDtotoEntity, userRoles: " + userRoles);
        return userRoles;
    }

    public List<UserRoleDto> getAllUserRoles() {
        LOG.debug("getAllUserRoles,  ");
        List<UserRole> userRoles = userRoleRepo.findAll();
        List<UserRoleDto> dtos = new ArrayList<>();

        for (UserRole userRole : userRoles) {
            UserRoleDto dto = new UserRoleDto();

            List<String> roles = new ArrayList<>();

            roles.add(userRole.getRole().getRoleId());

            dto.setUserId(userRole.getUser().getUserId());
            dto.setRoleId(roles);

            if (dtos.size() == 0)
                dtos.add(dto);
            int count = 0;

            for (UserRoleDto dto1 : dtos) {
                if (dto1.getUserId().equals(dto.getUserId())) {
                    List<String> a = dto1.getRoleId();
                    if (!a.contains(userRole.getRole().getRoleId()))
                        a.add(userRole.getRole().getRoleId());
                    dto1.setRoleId(a);
                    count = 1;
                }
            }
            if (count == 0)


                dtos.add(dto);


        }
        LOG.debug("getAllUserRoles, dtos: " + dtos);


        return dtos;
    }


    public UserRoleDto getUserRoleByUserId(String userId) {
        LOG.debug("getUserRoleByUserId, userId: " + userId);
        List<String> roleIds = new ArrayList<>();

        User User = userRepo.findByEmailId(userId);//TODO
        List<UserRole> userRoles = userRoleRepo.findByUserId(User.getId());
        LOG.debug("getUserRoleByUserId, userRoles: " + userRoles);

        for (UserRole userRole : userRoles) {
            String roleId = userRole.getRole().getRoleId();
            roleIds.add(roleId);
        }

        UserRoleDto dto = new UserRoleDto();
        dto.setUserId(userId);
        dto.setRoleId(roleIds);
//        dto.setCreatedBy(roleIds);

        LOG.debug("getUserRoleByUserId, dto: " + dto);
        return dto;
    }

    public List<UserProfileDto> getAllBarberUserRole() {
        LOG.debug("getAllGardnerUserRole: " );
        List<UserProfileDto> dtos = new ArrayList<>();

        List<UserRole> userRoles = userRoleRepo.findAll();
        LOG.debug("getUserRoleByUserId, userRoles: " + userRoles);

        for (UserRole userRole : userRoles) {
            UserProfileDto dto = new UserProfileDto();

            List<String> roles = new ArrayList<>();

            roles.add(userRole.getRole().getRoleId());
            dto.setUserId(userRole.getUser().getUserId());
            dto.setRoleId(roles);
            dto.setUserName(userRole.getUser().getUserName());
            for (String newfinalDto : dto.getRoleId()) {

                if (newfinalDto == RoleType.BARBER.name()) {

                    dtos.add(dto);
                }
            }

        }

        LOG.debug("getAllGardnerUserRole, dto: " + dtos);
        return dtos;
    }
    public List<UserProfileDto> getAllAdminRole() {
        LOG.debug("getAllAdminRole: " );
        List<UserProfileDto> dtos = new ArrayList<>();

        List<UserRole> userRoles = userRoleRepo.findAll();
        LOG.debug("getAllAdminRole, userRoles: " + userRoles);

        for (UserRole userRole : userRoles) {
            UserProfileDto dto = new UserProfileDto();

            List<String> roles = new ArrayList<>();

            roles.add(userRole.getRole().getRoleId());
            dto.setUserId(userRole.getUser().getUserId());
            dto.setRoleId(roles);
            dto.setUserName(userRole.getUser().getUserName());
            for (String newfinalDto : dto.getRoleId()) {

                if (newfinalDto == RoleType.ADMIN.name()) {

                    dtos.add(dto);
                }
            }

        }

        LOG.debug("getAllAdminRole, dto: " + dtos);
        return dtos;
    }

	public List<UserDto> getCustomerAndBarber() {
		List<UserRole> userRoles = userRoleRepo.findAll();
		List<UserDto> dtos = new ArrayList<>();
		for (UserRole userRole : userRoles) {
			User user = userRole.getUser();
			String roleId = userRole.getRole().getRoleId();
			if(roleId.equals(RoleType.BARBER.toString())) {
				UserDto userDto = userServices.convertUserEntitytoDto(user);
				dtos.add(userDto);
			}
		}

		return dtos;
	}


    public List<UserProfileDto> getAllCustomerRole() {
        LOG.debug("getAllCustomerRole: " );
        List<UserProfileDto> dtos = new ArrayList<>();

        List<UserRole> userRoles = userRoleRepo.findAll();
        LOG.debug("getAllCustomerRole, userRoles: " + userRoles);

        for (UserRole userRole : userRoles) {
            UserProfileDto dto = new UserProfileDto();

            List<String> roles = new ArrayList<>();

            roles.add(userRole.getRole().getRoleId());
            dto.setUserId(userRole.getUser().getUserId());
            dto.setRoleId(roles);
            dto.setUserName(userRole.getUser().getUserName());
            for (String newfinalDto : dto.getRoleId()) {

                if (newfinalDto == RoleType.CUSTOMER.name()) {

                    dtos.add(dto);
                }
            }

        }

        LOG.debug("getAllCustomerRole, dto: " + dtos);
        return dtos;
    }


	public UserDto getUserByEmailId(String emailId) {
		LOG.debug("getUserByEmailId, emailId: " + emailId);
		User user = userRepo.findByEmailId(emailId);
		UserDto dto = userServices.convertUserEntitytoDto(user);
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



}
