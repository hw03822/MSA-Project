package com.example.second_service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("/second-service")
@Slf4j
public class SecondServiceController {
    private static final Logger logger = Logger.getLogger(SecondServiceController.class.getName());

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to the Second service";
    }

    @GetMapping("/message")
    public String message(@RequestHeader("second-request") String header) {
        logger.info(header);
        return "Hello world in Second Service";
    }

    @GetMapping("/check")
    public String check() {
        return "Hi, there. This is a message from Second Service.";
    }
}
