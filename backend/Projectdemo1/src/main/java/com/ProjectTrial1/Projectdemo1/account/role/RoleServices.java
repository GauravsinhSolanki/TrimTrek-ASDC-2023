package com.ProjectTrial1.Projectdemo1.account.role;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class RoleServices {

    private static final Logger LOG = LoggerFactory.getLogger(RoleServices.class);

    @Autowired
    RoleRepository roleRepo;

    public Role createRole(RoleDto roleDto) {
        LOG.debug("createRole, roleDto: " + roleDto);
        Role role = convertRoleDtoToEntity(roleDto);
        Role dbRole = roleRepo.save(role);
        LOG.debug("createRole, dbRole: " + dbRole);
        return dbRole;
    }

    private Role convertRoleDtoToEntity(RoleDto roleDto) {
        LOG.debug("convertRoleDtoToEntity, roleDto: " + roleDto);
        Role role = new Role();
        role.setRoleId(roleDto.getRoleId());
        role.setRoleName(roleDto.getRoleName());
        role.setRoleDescription(roleDto.getRoleDescription());
        role.setCreatedOn(LocalDateTime.now());
        LOG.debug("convertRoleDtoToEntity, role: " + role);
        return role;
    }

    public List<RoleDto> getAllRoles() {
        LOG.debug("getAllRoles");
        List<RoleDto> dtos = new ArrayList<>();
        List<Role> roles = roleRepo.findAll();
        for (Role role : roles) {
            RoleDto dto = convertRoleEntityToDto(role);
            dtos.add(dto);
        }
        LOG.debug("getAllRoles, dtos: " + dtos);
        return dtos;
    }

    private RoleDto convertRoleEntityToDto(Role role) {
        LOG.debug("convertRoleEntityToDto, role: " + role);
        RoleDto roleDto = new RoleDto();
        roleDto.setRoleId(role.getRoleId());
        roleDto.setRoleName(role.getRoleName());
        roleDto.setRoleDescription(role.getRoleDescription());
        LOG.debug("convertRoleEntityToDto, roleDto: " + roleDto);
        return roleDto;
    }
 

}
