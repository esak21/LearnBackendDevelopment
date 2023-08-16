package com.botIntegration.demo.controller;

import com.botIntegration.demo.services.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AppController {

    @Autowired
    private AppService appService;

    @PostMapping("/v1/{message}")
    public ResponseEntity<String> sendSimpleMessageToSlack (
            @PathVariable(name = "message") String message) {
        appService.sendMessageToSlack(message);
        return ResponseEntity.ok(message);
    }

}