package com.driver;

public class Main {
    public static void main(String[] args) {
        try{
            CurrentAccount curr=new CurrentAccount("My_Name", 5002,"MYIDISTHIS");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}