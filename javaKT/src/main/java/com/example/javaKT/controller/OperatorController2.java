package com.example.javaKT.controller;

import com.example.javaKT.models.Input;
import com.example.javaKT.models.Output;
import com.example.javaKT.models.Student;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;

@RestController
public class OperatorController2 {

  public static Function<String, BiFunction<Integer, Integer, ResponseEntity>> operationFn = opr_type -> (arg1, arg2) -> {
    switch (opr_type) {
      case "sum":
        return ResponseEntity.ok().body(arg1 + arg2);
      case "subtract":
        return ResponseEntity.ok().body(arg1 - arg2);
      case "multiply":
        return ResponseEntity.ok().body(arg1 * arg2);
      case "divide":
        return ResponseEntity.ok().body(arg1 / arg2);
      default:
        return ResponseEntity.badRequest().body("Invalid Request");
    }
  };

  //http://localhost:8080/operator/sum?num1=10&num2=20

  @GetMapping("/operator/{operator_type}")
  public ResponseEntity operations(@PathVariable("operator_type") String operation_type, @RequestParam int num1, @RequestParam int num2) {
    return operationFn.apply(operation_type).apply(num1, num2);
  }

  //http://localhost:8080/operator2/sum?firstNumber=10&secondNumber=20
  //http://localhost:8080/operator2/subtract?firstNumber=10&secondNumber=20
  //http://localhost:8080/operator2/multiply?firstNumber=10&secondNumber=20
  //http://localhost:8080/operator2/divide?firstNumber=10&secondNumber=20
  @GetMapping("/operator2/{operator_type}")
  public int operations2(@PathVariable("operator_type") String operation_type, @RequestParam int firstNumber, @RequestParam int secondNumber) {
    Function<String, BinaryOperator<Integer>> operation = operaor_type -> (arg1, arg2) -> {
      switch (operaor_type) {
        case "sum":
          return arg1 + arg2;
        case "subtract":
          return arg1 - arg2;
        case "multiply":
          return arg1 * arg2;
        case "divide":
          return arg1 / arg2;
        default:
          return 0;
      }
    };
    return operation.apply(operation_type).apply(firstNumber, secondNumber);
  }

  // http://localhost:8080/operatorPost/sum?num1=10&num2=20
  @PostMapping("/operatorPost/{operator_type}")
  public ResponseEntity operations1(@PathVariable("operator_type") String operation_type, @RequestParam int num1, @RequestParam int num2) {
    return operationFn.apply(operation_type).apply(num1, num2);
  }

  @PostMapping("/postBody")
  public ResponseEntity postBody(@RequestBody Input reqBody) {
    String operation = reqBody.getType();
    System.out.println("Sysout" + reqBody.getNumbers().stream().count());

    switch (operation) {
      case "count":
        return ResponseEntity.ok().body(reqBody.getNumbers().stream().count());
      case "sum":
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(reqBody.getNumbers().stream().reduce((a, b) -> a + b));
      default:
        return ResponseEntity.badRequest().body("Invalid");
    }

  }

  @RequestMapping(value = "/json", method = RequestMethod.POST, produces = "text/html")
  public ResponseEntity<Output> postSum(@RequestBody Input reqBody) {
    /*final HttpHeaders httpHeaders= new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);*/
    String operation = reqBody.getType();
    switch (operation) {
      case "count":
        return ResponseEntity.ok().body(new Output((int) reqBody.getNumbers().stream().count()));
      //case "sum" : return  ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(reqBody.getNumbers().stream().reduce((a,b) -> a+b));
      default:
        return ResponseEntity.badRequest().body(new Output(0));
    }
  }

  @PostMapping("/student")
  public ResponseEntity<Student> studentCategoryByAge(@RequestBody Student student) {
    int age = student.getAge();
    Student out = student;
    if (age <= 19)
      out.setType("Teenager");
    else
      out.setType("Adult");

    return ResponseEntity.ok().body(student);
  }



/*  @PostMapping("/student2")
  public ServerResponse studentCategoryByAge(ServerRequest request) throws ServletException, IOException {
    Student student = request.body(Student.class);
    int age = student.getAge();
    Student out = student;
    if (age <= 19)
      out.setType("Teenager");
    else
      out.setType("Adult");

    return ServerResponse.ok().body(student);
  }*/


}


