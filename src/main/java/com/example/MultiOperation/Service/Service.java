package com.example.MultiOperation.Service;

import com.example.MultiOperation.Configration.Configration;
import com.example.MultiOperation.Entity.UserRole;
import com.example.MultiOperation.Entity.Users;
import com.example.MultiOperation.Repository.UserRepository;
import com.example.MultiOperation.Repository.UserRoleRepository;
import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import com.twilio.rest.api.v2010.account.Message;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class Service {
    @Autowired
    private UserRepository repo;

    @Autowired
    private UserRoleRepository userRoleRepo;
    @Autowired
    private Configration twilloConfigration;

    public List<Users> userDetails()
    {

        List<Users> userDetails=null;
        userDetails=repo.findAll();
        return userDetails ;
    }



    public Optional<Users> searchUser(Long userId)
    {

        Optional<Users> userDetails=null;
        userDetails=repo.findById(userId);

        return userDetails ;
    }


    public List<Users> readDataFromExcel(MultipartFile file) {


        List<Users> dataList = new ArrayList<>();
        List<UserRole> dataRole = new ArrayList<>();


        try (InputStream inputStream = file.getInputStream()) {
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next(); // Skip header row

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Users data = new Users();
                UserRole roleData=new UserRole();


                data.setName(row.getCell(0).toString());
                data.setEmail(row.getCell(1).toString());
                data.setMobileNo(row.getCell(2).toString());
                roleData.setUserRoles(row.getCell(3).toString());
                roleData.setDesignation(row.getCell(4).toString());
                roleData.setUsers(data);
                dataRole.add(roleData);
                dataList.add(data);

            }
            userRoleRepo.saveAll(dataRole);
            repo.saveAll(dataList);


        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
        }

        return dataList;
    }

    public String saveAllData(List<Users> userData) {

        repo.saveAll(userData);
        return "Saved";
    }

    public int otpGenerate(String mobileNo) {
        int otpValue = (int) Math.floor(Math.random() * 900000) + 100000;
        return otpValue;
    }

    public void otpSendSms(String mobileNo,int otp)
    {

        Twilio.init(twilloConfigration.getAccountSid(), twilloConfigration.getAuthToken());
        Message message= Message.creator(new PhoneNumber("+91"+mobileNo),
                 new PhoneNumber(twilloConfigration.getPhoneNumber()),"This otp is used for authentication, generated otp is "+otp+" valid for 10 mins.").create();
        System.out.println("SMS sent with SID: " + message.getSid());
    }
}
