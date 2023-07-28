package com.ProjectTrial1.Projectdemo1.account.userrole;

import com.ProjectTrial1.Projectdemo1.account.role.Role;
import com.ProjectTrial1.Projectdemo1.account.role.RoleRepository;
import com.ProjectTrial1.Projectdemo1.account.user.User;
import com.ProjectTrial1.Projectdemo1.account.user.UserDto;
import com.ProjectTrial1.Projectdemo1.account.user.UserRepository;
import com.ProjectTrial1.Projectdemo1.account.user.UserServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserRoleServicesTest {

    @InjectMocks
    UserRoleServices userRoleServices;

    @Mock
    UserRepository userRepo;

    @Mock
    RoleRepository roleRepo;

    @Mock
    UserRoleRepository userRoleRepo;

    @Mock
    UserServices userServices;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }



    @Test
    public void testGetUserRoleByUserId() {
        User user = new User();
        user.setId(1);
        user.setEmailId("test@example.com");

        Role role = new Role();
        role.setId(1);
        role.setRoleId("role1");

        UserRole userRole = new UserRole();
        userRole.setUserId(1);
        userRole.setRoleId(1);

        when(userRepo.findByEmailId("test@example.com")).thenReturn(user);
        when(userRoleRepo.findByUserId(1)).thenReturn(Arrays.asList(userRole));
        when(roleRepo.findById(1)).thenReturn(role);

        UserRoleDto userRoleDto = userRoleServices.getUserRoleByUserId("test@example.com");

        assertEquals("test@example.com", userRoleDto.getUserId());
        assertEquals("role1", userRoleDto.getRoleId().get(0));
        verify(userRepo, times(1)).findByEmailId("test@example.com");
        verify(userRoleRepo, times(1)).findByUserId(1);
        verify(roleRepo, times(1)).findById(1);
    }

    @Test
    public void testCreateUserRole() {
        UserRoleDto userRoleDto = new UserRoleDto();
        userRoleDto.setUserId("test@example.com");
        userRoleDto.setRoleId(Arrays.asList("role1"));

        User user = new User();
        user.setId(1);
        user.setEmailId("test@example.com");

        Role role = new Role();
        role.setId(1);
        role.setRoleId("role1");

        UserRole userRole = new UserRole();
        userRole.setUserId(1);
        userRole.setRoleId(1);

        when(userRepo.findByEmailId("test@example.com")).thenReturn(user);
        when(roleRepo.findByRoleId("role1")).thenReturn(role);
        when(userRoleRepo.saveAll(anyList())).thenReturn(Arrays.asList(userRole));

        List<UserRole> userRoles = userRoleServices.createUserRole(userRoleDto);

        assertEquals(1, userRoles.size());
        assertEquals(1, userRoles.get(0).getUserId());
        assertEquals(1, userRoles.get(0).getRoleId());
        verify(userRepo, times(1)).findByEmailId("test@example.com");
        verify(roleRepo, times(1)).findByRoleId("role1");
        verify(userRoleRepo, times(1)).saveAll(anyList());
    }

    @Test
    public void testGetAllUserRoles() {
        User user = new User();
        user.setId(1);
        user.setEmailId("test@example.com");
        user.setUserId("test");

        Role role = new Role();
        role.setId(1);
        role.setRoleId("role1");

        UserRole userRole = new UserRole();
        userRole.setUserId(1);
        userRole.setRoleId(1);

        when(userRoleRepo.findAll()).thenReturn(Arrays.asList(userRole));
        when(userRepo.findById(1)).thenReturn(user);
        when(roleRepo.findById(1)).thenReturn(role);

        List<UserRoleDto> userRoleDtos = userRoleServices.getAllUserRoles();

        assertEquals(1, userRoleDtos.size());
        assertEquals("test", userRoleDtos.get(0).getUserId());
        assertEquals("role1", userRoleDtos.get(0).getRoleId().get(0));
    }

    @Test
    public void testGetUserByEmailId() {
        User user = new User();
        user.setEmailId("test@example.com");
        UserDto userDto = new UserDto();
        userDto.setEmailId("test@example.com");

        when(userRepo.findByEmailId("test@example.com")).thenReturn(user);
        when(userServices.convertUserEntitytoDto(user)).thenReturn(userDto);

        UserDto result = userRoleServices.getUserByEmailId("test@example.com");

        assertEquals("test@example.com", result.getEmailId());
    }

    @Test
    public void testGetUserIdByEmailId() {
        User user = new User();
        user.setEmailId("test@example.com");

        when(userRepo.findByEmailId("test@example.com")).thenReturn(user);

        String result = userRoleServices.getUserIdByEmailId("test@example.com");

        assertEquals("test@example.com", result);
    }

    @Test
    public void testGetEmailIdByUserId() {
        User user = new User();
        user.setUserId("test");
        user.setEmailId("test@example.com");

        when(userRepo.findByUserId("test")).thenReturn(user);

        String result = userRoleServices.getEmailIdByUserId("test");

        assertEquals("test@example.com", result);
    }

    @Test
    public void testGetAllBarberUserRole() {
        User user = new User();
        user.setId(1);
        user.setUserId("test");

        Role role = new Role();
        role.setId(1);
        role.setRoleId(RoleType.BARBER.name());

        UserRole userRole = new UserRole();
        userRole.setUserId(1);
        userRole.setRoleId(1);

        when(userRoleRepo.findAll()).thenReturn(Arrays.asList(userRole));
        when(userRepo.findById(1)).thenReturn(user);
        when(roleRepo.findById(1)).thenReturn(role);

        List<UserProfileDto> userProfileDtos = userRoleServices.getAllBarberUserRole();

        assertEquals(1, userProfileDtos.size());
        assertEquals("test", userProfileDtos.get(0).getUserId());
        assertEquals(RoleType.BARBER.name(), userProfileDtos.get(0).getRoleId().get(0));
    }

    @Test
    public void testGetAllAdminRole() {
        User user = new User();
        user.setId(1);
        user.setUserId("test");

        Role role = new Role();
        role.setId(1);
        role.setRoleId(RoleType.ADMIN.name());

        UserRole userRole = new UserRole();
        userRole.setUserId(1);
        userRole.setRoleId(1);

        when(userRoleRepo.findAll()).thenReturn(Arrays.asList(userRole));
        when(userRepo.findById(1)).thenReturn(user);
        when(roleRepo.findById(1)).thenReturn(role);

        List<UserProfileDto> userProfileDtos = userRoleServices.getAllAdminRole();

        assertEquals(1, userProfileDtos.size());
        assertEquals("test", userProfileDtos.get(0).getUserId());
        assertEquals(RoleType.ADMIN.name(), userProfileDtos.get(0).getRoleId().get(0));
    }

    @Test
    public void testGetCustomerAndBarber() {
        User user = new User();
        user.setId(1);
        user.setUserId("test");

        Role role = new Role();
        role.setId(1);
        role.setRoleId(RoleType.BARBER.name());

        UserRole userRole = new UserRole();
        userRole.setUserId(1);
        userRole.setRoleId(1);

        UserDto userDto = new UserDto();
        userDto.setEmailId("test@example.com");

        when(userRoleRepo.findAll()).thenReturn(Arrays.asList(userRole));
        when(userRepo.findById(1)).thenReturn(user);
        when(roleRepo.findById(1)).thenReturn(role);
        when(userServices.convertUserEntitytoDto(user)).thenReturn(userDto);

        List<UserDto> userDtos = userRoleServices.getCustomerAndBarber();

        assertEquals(1, userDtos.size());
        assertEquals("test@example.com", userDtos.get(0).getEmailId());
    }

    @Test
    public void testGetAllCustomerRole() {
        User user = new User();
        user.setId(1);
        user.setUserId("test");

        Role role = new Role();
        role.setId(1);
        role.setRoleId(RoleType.CUSTOMER.name());

        UserRole userRole = new UserRole();
        userRole.setUserId(1);
        userRole.setRoleId(1);

        when(userRoleRepo.findAll()).thenReturn(Arrays.asList(userRole));
        when(userRepo.findById(1)).thenReturn(user);
        when(roleRepo.findById(1)).thenReturn(role);

        List<UserProfileDto> userProfileDtos = userRoleServices.getAllCustomerRole();

        assertEquals(1, userProfileDtos.size());
        assertEquals("test", userProfileDtos.get(0).getUserId());
        assertEquals(RoleType.CUSTOMER.name(), userProfileDtos.get(0).getRoleId().get(0));
    }
}



