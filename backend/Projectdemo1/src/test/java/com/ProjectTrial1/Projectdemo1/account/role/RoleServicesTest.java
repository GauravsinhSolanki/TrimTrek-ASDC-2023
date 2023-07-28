package com.ProjectTrial1.Projectdemo1.account.role;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.List;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

public class RoleServicesTest {

    private RoleRepository roleRepoMock;
    private RoleServices roleServices;

    @BeforeEach
    public void setUp() {
        roleRepoMock = Mockito.mock(RoleRepository.class);
        roleServices = new RoleServices();
        ReflectionTestUtils.setField(roleServices, "roleRepo", roleRepoMock);
    }

    @Test
    public void testCreateRole() {
        RoleDto roleDto = new RoleDto();
        Role expectedRole = new Role(0, "roleId1", "roleName1", "roleDesc1", true, null, LocalDateTime.now(), null, null);

        when(roleRepoMock.save(any(Role.class))).thenReturn(expectedRole);

        Role actualRole = roleServices.createRole(roleDto);

        org.junit.jupiter.api.Assertions.assertEquals(expectedRole, actualRole);
        verify(roleRepoMock).save(any(Role.class));
    }

    @Test
    public void testGetAllRoles() {
        Role role1 = new Role(0, "roleId1", "roleName1", "roleDesc1", true, null, LocalDateTime.now(), null, null);
        Role role2 = new Role(1, "roleId2", "roleName2", "roleDesc2", true, null, LocalDateTime.now(), null, null);

        when(roleRepoMock.findAll()).thenReturn(Arrays.asList(role1, role2));

        List<RoleDto> roleDtoList = roleServices.getAllRoles();

        org.junit.jupiter.api.Assertions.assertEquals(2, roleDtoList.size());
        verify(roleRepoMock).findAll();
    }
}
