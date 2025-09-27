package com.aathavan.backend.controller;


import com.aathavan.backend.logic.TempLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Temp {
    @Autowired
    private TempLogic tempLogic;

    @GetMapping("/get")
    private Object getUser() {
        try {
            return tempLogic.httpHit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/get/{city}")
    private Object getUserBu(@PathVariable String city) {
        try {
            return tempLogic.httpHit(city);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
