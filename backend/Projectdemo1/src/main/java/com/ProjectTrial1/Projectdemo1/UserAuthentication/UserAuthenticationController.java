package com.ProjectTrial1.Projectdemo1.UserAuthentication;

import com.ProjectTrial1.Projectdemo1.photogallery.PhotoGallery;
import com.ProjectTrial1.Projectdemo1.photogallery.PhotoGalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/user-authentication")
public class UserAuthenticationController {
    @Autowired
    UserAuthenticationService userAuthenticationService;

    @PostMapping("/setToken/")
    public String setAuthenticationToken(@RequestBody Map<String,String> request) {
        //NEED  TO ADD
//        System.out.println(request.get("userEmail"));
//        return request.get("userEmail");
        String user_email = request.get("userEmail");
        Boolean isAuthenticated = userAuthenticationService.setUserToken(user_email);
        if (isAuthenticated) {
            return "true";
        } else {
            return "false";
        }
    }

    @PostMapping("/checkToken/")
    public String checkAuthenticationToken(@RequestBody Map<String,String> request) {
        //NEED  TO ADD
        String user_email = request.get("userEmail");
        System.out.println(user_email);
        Boolean isAuthenticated = userAuthenticationService.checkUserToken(user_email);
        if (isAuthenticated) {
            return "true";
        } else {
            return "false";
        }
//        return "true";
    }


        // ... Other code ...

        @PostMapping("/deleteToken")
        public String deleteAuthenticationToken(@RequestBody Map<String,String> request) {
            String user_email = request.get("userEmail");
            boolean isDeleted = userAuthenticationService.deleteUserToken(user_email);
            if (isDeleted) {
                return "true";
            } else {
                return "false";
            }
        }




}
