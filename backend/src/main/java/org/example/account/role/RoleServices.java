package org.example.account.role;

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
        Role role = convertRoleDtotoEntity(roleDto);
        Role dbRole = roleRepo.save(role);
        LOG.debug("createRole, dbRole: " + dbRole);
        return dbRole;
    }

    private Role convertRoleDtotoEntity(RoleDto roleDto) {
        LOG.debug("convertRoleDtotoEntity, roleDto: " + roleDto);
        Role role = new Role();

        role.setRoleId(roleDto.getRoleId());
        role.setRoleName(roleDto.getRoleName());
        role.setRoleDescription(roleDto.getRoleDescription());
        role.setCreatedOn(LocalDateTime.now());
        LOG.debug("convertRoleDtotoEntity, role: " + role);
        return role;
    }

    public List<RoleDto> getAllRoles() {
        LOG.debug("getAllRoles,  ");
        List<RoleDto> dtos = new ArrayList<>();

        List<Role> roles = roleRepo.findAll();

        for (Role role : roles) {
            RoleDto dto = convertRoleEntitytoDto(role);
            dtos.add(dto);
        }
        LOG.debug("getAllRoles, dtos: " + dtos);
        return dtos;
    }

    private RoleDto convertRoleEntitytoDto(Role role) {
        LOG.debug("convertRoleEntitytoDto,  role: " + role);
        RoleDto roleDto = new RoleDto();

        roleDto.setRoleId(role.getRoleId());
        roleDto.setRoleName(role.getRoleName());
        roleDto.setRoleDescription(role.getRoleDescription());
        LOG.debug("convertRoleEntitytoDto, roleDto: " + roleDto);
        return roleDto;
    }

 

}
