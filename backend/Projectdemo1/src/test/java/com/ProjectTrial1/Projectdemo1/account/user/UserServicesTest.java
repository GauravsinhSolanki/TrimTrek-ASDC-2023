package com.ProjectTrial1.Projectdemo1.account.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

public class UserServicesTest {

    @Mock
    private UserRepository userRepoMock;

    @InjectMocks
    private UserServices userServices;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testCreateUser() {
        UserDto userDto = new UserDto("testName", "testPassword", "testGender", LocalDate.now(), "testPhone", "testAltPhone", "testEmail");
        User expectedUser = new User(0, "testUserId", "testName", "testPassword", "testGender", LocalDate.now(), "testPhone", "testAltPhone", "testEmail", "testSpeciality", true, null, LocalDateTime.now(), null, LocalDateTime.now());

        when(userRepoMock.save(any(User.class))).thenReturn(expectedUser);

        User result = userServices.createUser(userDto);

        assertEquals(expectedUser, result);
        verify(userRepoMock).save(any(User.class));
    }

    @Test
    public void testGetAllUsers() {
        List<User> userList = new ArrayList<>();
        User user1 = new User(0, "testUserId1", "testName1", "testPassword1", "testGender1", LocalDate.now(), "testPhone1", "testAltPhone1", "testEmail1", "testSpeciality1", true, null, LocalDateTime.now(), null, LocalDateTime.now());
        User user2 = new User(1, "testUserId2", "testName2", "testPassword2", "testGender2", LocalDate.now(), "testPhone2", "testAltPhone2", "testEmail2", "testSpeciality2", true, null, LocalDateTime.now(), null, LocalDateTime.now());
        userList.add(user1);
        userList.add(user2);

        when(userRepoMock.findAll()).thenReturn(userList);

        List<UserDto> result = userServices.getAllUsers();

        assertEquals(userList.size(), result.size());
        verify(userRepoMock).findAll();
    }

    @Test
    public void testGetUserByUserId() {
        User expectedUser = new User(0, "testUserId", "testName", "testPassword", "testGender", LocalDate.now(), "testPhone", "testAltPhone", "testEmail", "testSpeciality", true, null, LocalDateTime.now(), null, LocalDateTime.now());

        when(userRepoMock.findByUserId(any(String.class))).thenReturn(expectedUser);

        UserDto result = userServices.getUserByUserId("testUserId");

        assertEquals(expectedUser.getUserName(), result.getUserName());
        verify(userRepoMock).findByUserId(any(String.class));
    }

    @Test
    public void testGetUserByEmailId() {
        User expectedUser = new User(0, "testUserId", "testName", "testPassword", "testGender", LocalDate.now(), "testPhone", "testAltPhone", "testEmail", "testSpeciality", true, null, LocalDateTime.now(), null, LocalDateTime.now());

        when(userRepoMock.findByEmailId(any(String.class))).thenReturn(expectedUser);

        UserDto result = userServices.getUserByEmailId("testEmail");

        assertEquals(expectedUser.getUserName(), result.getUserName());
        verify(userRepoMock).findByEmailId(any(String.class));
    }

    @Test
    public void testGetUserIdByEmailId() {
        User expectedUser = new User(0, "testUserId", "testName", "testPassword", "testGender", LocalDate.now(), "testPhone", "testAltPhone", "testEmail", "testSpeciality", true, null, LocalDateTime.now(), null, LocalDateTime.now());

        when(userRepoMock.findByEmailId(any(String.class))).thenReturn(expectedUser);

        String result = userServices.getUserIdByEmailId("testEmail");

        assertEquals(expectedUser.getEmailId(), result);
        verify(userRepoMock).findByEmailId(any(String.class));
    }

    @Test
    public void testGetEmailIdByUserId() {
        User expectedUser = new User(0, "testUserId", "testName", "testPassword", "testGender", LocalDate.now(), "testPhone", "testAltPhone", "testEmail", "testSpeciality", true, null, LocalDateTime.now(), null, LocalDateTime.now());

        when(userRepoMock.findByUserId(any(String.class))).thenReturn(expectedUser);

        String result = userServices.getEmailIdByUserId("testUserId");

        assertEquals(expectedUser.getEmailId(), result);
        verify(userRepoMock).findByUserId(any(String.class));
    }


    @Test
    public void testCreateBarberProfile() {
        BarberDto barberDto = new BarberDto("testEmail", "testSpeciality");
        User expectedUser = new User(0, "testUserId", "testName", "testPassword", "testGender", LocalDate.now(), "testPhone", "testAltPhone", "testEmail", "testSpeciality", true, null, LocalDateTime.now(), null, LocalDateTime.now());

        when(userRepoMock.findByEmailId(any(String.class))).thenReturn(expectedUser);
        when(userRepoMock.update(any(User.class))).thenReturn(expectedUser);

        User result = userServices.createBarberProfile(barberDto);

        assertEquals(expectedUser, result);
        verify(userRepoMock).findByEmailId(any(String.class));
        verify(userRepoMock).update(any(User.class));
    }



    @Test
    public void testGetPasswordByEmailId() {
        User expectedUser = new User(0, "testUserId", "testName", "testPassword", "testGender", LocalDate.now(), "testPhone", "testAltPhone", "testEmail", "testSpeciality", true, null, LocalDateTime.now(), null, LocalDateTime.now());

        when(userRepoMock.findByEmailId(any(String.class))).thenReturn(expectedUser);

        String result = userServices.getPasswordByEmailId("testEmail");

        assertEquals(expectedUser.getUserPassWord(), result);
        verify(userRepoMock).findByEmailId(any(String.class));
    }

    @Test
    public void testCreateUserWithBarberProfile() {
        BarberDto barberDto = new BarberDto("testEmail", "testSpeciality");
        User expectedUser = new User(0, "testUserId", "testName", "testPassword", "testGender", LocalDate.now(), "testPhone", "testAltPhone", "testEmail", "testSpeciality", true, null, LocalDateTime.now(), null, LocalDateTime.now());

        when(userRepoMock.findByEmailId(any(String.class))).thenReturn(expectedUser);
        when(userRepoMock.update(any(User.class))).thenReturn(expectedUser);

        User result = userServices.createBarberProfile(barberDto);

        assertEquals(expectedUser, result);
        verify(userRepoMock).findByEmailId(any(String.class));
        verify(userRepoMock).update(any(User.class));
    }

    @Test
    public void testGetBarberProfileByEmailId() {
        User expectedUser = new User(0, "testUserId", "testName", "testPassword", "testGender", LocalDate.now(), "testPhone", "testAltPhone", "testEmail", "testSpeciality", true, null, LocalDateTime.now(), null, LocalDateTime.now());

        when(userRepoMock.findByEmailId(any(String.class))).thenReturn(expectedUser);

        BarberProfileDto result = userServices.getBarberProfileByEmailId("testEmail");

        assertEquals(expectedUser.getUserName(), result.getUserName());
        verify(userRepoMock).findByEmailId(any(String.class));
    }
}