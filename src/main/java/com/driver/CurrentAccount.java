package com.driver;

import java.util.HashMap;
import java.util.PriorityQueue;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only

    public String getTradeLicenseId() {
        return tradeLicenseId;
    }

    public void setTradeLicenseId(String tradeLicenseId) {
        this.tradeLicenseId = tradeLicenseId;
    }

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        super(name,balance,5000);
        if(balance<5000)
            throw new Exception("Insufficient Balance");
        this.tradeLicenseId=tradeLicenseId;
        validateLicenseId();
    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception
        boolean isvalid = true;
        //checking whether given id is valid or not
        for (int i = 1; i < tradeLicenseId.length(); i++)
            if (tradeLicenseId.charAt(i) == tradeLicenseId.charAt(i - 1)) {
                isvalid = false;
                break;
            }
        if(isvalid) //do nothing if given id is valid
            return;
        //if given id is not valid then modifying id
        StringBuilder id=new StringBuilder();
        if (!isvalid) {
            PriorityQueue<Pair> pq = new PriorityQueue<>();
            HashMap<Character, Integer> map = new HashMap<>();
            for (int i = 0; i < tradeLicenseId.length(); i++) {
                map.put(tradeLicenseId.charAt(i), map.getOrDefault(tradeLicenseId.charAt(i), 0) + 1);
            }
            for (char key : map.keySet())
                pq.add(new Pair(key, map.get(key)));
//            StringBuilder id = new StringBuilder();
            while (pq.size() != 0) {
                Pair p = pq.poll();
                id.append(p.c);
                p.freq--;
                if (p.freq != 0)
                    pq.add(p);
            }
        }
        isvalid=true;
        //checking whether modified id is valid or not
        for (int i = 1; i < tradeLicenseId.length(); i++)
            if (tradeLicenseId.charAt(i) == tradeLicenseId.charAt(i - 1)) {
                isvalid = false;
                break;
            }
        if(isvalid){    //change id to modified if modified id is valid
            tradeLicenseId= id.toString();
            return;
        }
        else throw new Exception("Valid License can not be generated");
    }
}

class Pair implements Comparable<Pair>{
    char c;
    int freq;
    Pair(char c,int freq){
        this.c=c;
        this.freq=freq;
    }
    @Override
    public int compareTo(Pair b){
        return b.freq-this.freq;
    }
}
