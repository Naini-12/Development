package com.example.MultiOperation.Controller;

import com.example.MultiOperation.Entity.UserRole;
import com.example.MultiOperation.Entity.Users;
import com.example.MultiOperation.Service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class Controller {

    @Autowired
    private Service userService;

    @GetMapping("/detail")
    public List<Users> userDetails() {

        List<Users> userDetail = userService.userDetails();
        return userDetail;
    }

    @PostMapping("/searchUser")
    public Optional<Users> searchUser(@RequestBody Users userRequest) {

        Optional<Users> userDetail = userService.searchUser(userRequest.getId());
        List<UserRole> roles = null;


        if (userDetail.get().getUserRoles().isEmpty()) {
            System.out.println("user role is not available");
            userDetail.get().setUserRoles(roles);
        } else {
            System.out.println("user role is available");
        }
        return userDetail;
    }


    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        List<Users> dataList = userService.readDataFromExcel(file);
        return ResponseEntity.ok("File uploaded successfully!");
    }

    @PostMapping("/saveData")
    public  String saveData(@RequestBody List<Users> userData)
    {

        userService.saveAllData(userData);
        return "Saved";
    }


}
