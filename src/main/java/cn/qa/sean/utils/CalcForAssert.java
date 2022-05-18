package cn.qa.sean.utils;

public class CalcForAssert {
    public static String getPercent(String value) {
      return   Integer.toString((((Integer.parseInt(value))/100)*10));
    }

    public static void main(String[] args) {
        System.out.println(getPercent("4345333"));
    }

}
