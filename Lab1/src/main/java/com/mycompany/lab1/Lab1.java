package com.mycompany.lab1;

/**
 *
 * @author Sandu
 */
public class Lab1 {
    public static int sumDigit(int a){
        int sum = 0;
       while(a!=0) {
           sum = sum + a%10;
           a = a / 10;
       }
       return sum;
    }
    void compulsory() {  
        System.out.println("Hello World!");
        String[] languages = {"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};
        int n = (int)(Math.random()*1_000_000);
        n = (n*3 + Integer.parseInt("10101",2) + Integer.parseInt("FF",16))*6;
        int sum = sumDigit(n);
        while(sum>=10) {
            sum = sumDigit((sum));
        }
        System.out.println("Willy-nilly, this semester I will learn " + languages[sum]);
       }
    public static void main(String[] args) {
        Lab1 lab1 = new Lab1();
        lab1.compulsory();
    }
}
