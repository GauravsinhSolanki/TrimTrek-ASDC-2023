package com.ProjectTrial1.Projectdemo1.UserAuthentication;

public interface UserAuthenticationRepository {
    boolean setUserTokenDB(String userEmail);

    boolean getUserTokenDB(String userEmail);

    boolean deleteUserTokenDB(String userEmail);
}
