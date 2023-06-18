package org.example;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class UserServicesTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServices userServices;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllUsers() {
        // Arrange
        User user1 = new User();
        user1.setId(1L);
        user1.setUsername("jay");
        user1.setPassword("jay@dal");

        User user2 = new User();
        user2.setId(2L);
        user2.setUsername("rakshit");
        user2.setPassword("rakshit@dal");

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);

        when(userRepository.findAll()).thenReturn(users);

        // Act
        List<User> result = userServices.getAllUsers();

        // Assert
        assertEquals(users.size(), result.size());
        assertEquals(users.get(0).getUsername(), result.get(0).getUsername());
        assertEquals(users.get(1).getUsername(), result.get(1).getUsername());


    }

    @Test
    public void testCreateUser() {
        // Arrange
        User user = new User();
        user.setId(1L);
        user.setUsername("jay");
        user.setPassword("jay@dal");

        when(userRepository.save(user)).thenReturn(user);

        // Act
        User result = userServices.createUser(user);

        // Assert
        assertEquals(user.getId(), result.getId());
        assertEquals(user.getUsername(), result.getUsername());
        assertEquals(user.getPassword(), result.getPassword());


    }


}
