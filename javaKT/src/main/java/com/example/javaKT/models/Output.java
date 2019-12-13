package com.example.javaKT.models;

public class Output {

  public Integer getSum() {
    return Sum;
  }

  public void setSum(Integer sum) {
    Sum = sum;
  }

  public Output(Integer sum) {
    Sum = sum;
  }

  Integer Sum;

  @Override
  public String toString() {
    return "{" +
        "Sum=" + Sum +
        '}';
  }
}
