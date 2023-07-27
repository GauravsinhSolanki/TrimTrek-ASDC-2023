package com.ProjectTrial1.Projectdemo1.account.userrole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileDto {

    public String userId;
    public String userName;
    public List<String> roleId;
}
