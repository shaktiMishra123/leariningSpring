package com.example.javaKT.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {

  @GetMapping("/")
  public String helloWorld(){
    return "Hello World";
  }

  //http://localhost:8080/sum?num1=10&num2=20
  @GetMapping("/sum")
  public int sum(@RequestParam int num1,@RequestParam int num2){
    return num1 + num2;
  }
}
