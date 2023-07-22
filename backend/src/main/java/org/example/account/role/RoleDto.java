package org.example.account.role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {
	
    public String roleId;
    public String roleName;
    public String roleDescription;
    
}
