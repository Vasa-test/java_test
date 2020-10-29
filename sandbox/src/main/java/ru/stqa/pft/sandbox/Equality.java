package ru.stqa.pft.sandbox;

public class Equality {

  public static void main(String[] args){
    String s1 = "firefox";
    String s2 = new String(s1);

    System.out.println(s1 == s2); // сравниваются ссылки на объект
    System.out.println(s1.equals(s2)); // сравниваются содержимое объектов
  }
}
