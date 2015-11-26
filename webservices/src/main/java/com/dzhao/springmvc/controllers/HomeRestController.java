package com.dzhao.springmvc.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dzhao on 2/11/2015.
 */
@RestController
public class HomeRestController {

    @RequestMapping(value="/rest/api/v1", method= RequestMethod.GET)
    public ResponseEntity<String> info(){
        return new ResponseEntity<String>("Home Controller.", HttpStatus.OK);
    }
}
