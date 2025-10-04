package com.aathavan.backend.controller;


import com.aathavan.backend.BackendApplication;
import com.aathavan.backend.logic.LoginLogic;
import com.aathavan.backend.logic.TempLogic;
import com.aathavan.backend.model.ReturnStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginLogic loginLogic;

    @PostMapping("/checklogin")
    public ReturnStatus validateUserData(@RequestBody Map<String, Object> map) {
        return loginLogic.validateUserData(map);
    }

    @GetMapping("/check")
    public ReturnStatus getAuthTokenDetail(@RequestParam String userid) {
        return loginLogic.getAuthTokenDetail(userid);
    }


}
