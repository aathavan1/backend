package com.aathavan.backend.controller;


import com.aathavan.backend.logic.LoginLogic;
import com.aathavan.backend.model.ReturnStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginLogic loginLogic;

    @PostMapping("/userlogin")
    public ReturnStatus loginUser(@RequestBody Map<String, Object> map) {
        return loginLogic.loginUser(map);
    }

    @GetMapping("/check")
    public ReturnStatus getAuthTokenDetail(@RequestParam String userid) {
        return loginLogic.getAuthTokenDetail(userid);
    }

    @PostMapping("/checklogin")
    public ReturnStatus checkLogin(@RequestBody Map<String, Object> map) {
        return loginLogic.checkLogin(map);
    }


}
