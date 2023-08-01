package com.ProjectTrial1.Projectdemo1.UserAuthentication;

import com.ProjectTrial1.Projectdemo1.photogallery.PhotoGalleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserAuthenticationService {
    @Autowired
    UserAuthenticationRepository userAuthenticationRepository;
    public Boolean setUserToken(String userEmail) {
        Boolean result = userAuthenticationRepository.setUserTokenDB(userEmail);
        return result;
    }
    public boolean checkUserToken(String userEmail) {
        return userAuthenticationRepository.getUserTokenDB(userEmail);
    }

    public boolean deleteUserToken(String userEmail) {
        return userAuthenticationRepository.deleteUserTokenDB(userEmail);
    }
}
