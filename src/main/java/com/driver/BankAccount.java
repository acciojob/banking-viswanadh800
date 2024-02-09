package com.driver;

public class BankAccount {

    private String name;
    private double balance;
    private double minBalance;

    public double getBalance(){
        return balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getMinBalance() {
        return minBalance;
    }

    public void setMinBalance(double minBalance) {
        this.minBalance = minBalance;
    }

    public BankAccount(String name, double balance, double minBalance) {
        this.name=name;
        this.balance=balance;
        this.minBalance=minBalance;
    }

    private String generateAccountNumberHelper(int digits, int sum){
        if(digits==0 || 9*digits<sum)
            return null;
        if(digits==1)
            return sum+"";
        int number[]=new int[digits];
        sum-=1;
        for(int i=digits-1;i>=0;i--){
            if(sum>=9){
                number[i]=9;
                sum-=9;
            }
            else{
                number[i]=sum;
                sum=0;
            }
        }
        number[0]++;
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<digits;i++)
            sb.append(number[i]);
        return sb.toString();
    }
    public String generateAccountNumber(int digits, int sum) throws Exception{
        //Each digit of an account number can lie between 0 and 9 (both inclusive)
        //Generate account number having given number of 'digits' such that the sum of digits is equal to 'sum'
        //If it is not possible, throw "Account Number can not be generated" exception
        String number=generateAccountNumberHelper(digits,sum);
        if(number==null)
            throw new Exception("Account Number can not be generated");
        return number;
    }

    public void deposit(double amount) {
        //add amount to balance
        balance+=amount;
    }

    public void withdraw(double amount) throws Exception {
        // Remember to throw "Insufficient Balance" exception, if the remaining amount would be less than minimum balance
        if(balance-amount<minBalance)
            throw new Exception("Insufficient Balance");
        balance-=amount;
    }

}