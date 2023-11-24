package com.example.MultiOperation.Controller;

import com.example.MultiOperation.Entity.UserRole;
import com.example.MultiOperation.Entity.Users;
import com.example.MultiOperation.Model.CommonResponse;
import com.example.MultiOperation.Service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
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
    public List<CommonResponse> searchUser(@RequestBody Users userRequest) {

        Optional<Users> userDetail = userService.searchUser(userRequest.getId());
       // List<UserRole> roles = null;
        CommonResponse commonResponse=new CommonResponse();


        if (userDetail.isEmpty()) {

            commonResponse.setUsers(userDetail);
            commonResponse.setMessage("User not found");
            commonResponse.setCode("1111");
        }
        else
        {
            commonResponse.setUsers(userDetail);
            commonResponse.setMessage("Sucess");
            commonResponse.setCode("0000");
        }
        return Collections.singletonList(commonResponse);
    }


    @PostMapping("/upload")
    public ResponseEntity<Users> handleFileUpload(@RequestParam("file") MultipartFile file) {
        List<Users> dataList = userService.readDataFromExcel(file);
        return
    }

    @PostMapping("/saveData")
    public  String saveData(@RequestBody List<Users> userData)
    {

        userService.saveAllData(userData);
        return "Saved";
    }


}
