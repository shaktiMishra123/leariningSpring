package com.example.javaKT.models;

import java.util.List;

public class Input {
  String type;
  List<Integer> numbers;

  public void setType(String type) {
    this.type = type;
  }

  public void setNumbers(List<Integer> numbers) {
    this.numbers = numbers;
  }

  public String getType() {
    return type;
  }

  public List<Integer> getNumbers() {
    return numbers;
  }

  public Input(String type, List<Integer> numbers) {
    this.type = type;
    this.numbers = numbers;
  }
}
