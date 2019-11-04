package com.example.test;

public class MyClass {

    public static int titleToNumber(String s) {
        char[] list=s.toCharArray();
        int sum=0;
        for (int i = 0; i < list.length; i++) {
            int val=list[i]-64;
            sum+=val*Math.pow(26,list.length-1-i);
        }
        return sum;
    }

    public static int titleToNumber2(String s) {
        char[] list=s.toCharArray();
        int sum=0;
        for (int i = 0; i < list.length; i++) {
            sum=sum*26+(list[i]-64);
        }
        return sum;
    }

    public static int getValue(int n){
        int sum=0;
        while (n>0){
            sum+=Math.pow(n%10,2);
            n/=10;
        }
        return sum;
    }

    public static void main(String[] args){
        int result=getValue(19);
        System.out.println(result);
    }
}
