package com.driver;

import java.util.PriorityQueue;

public class CurrentAccount extends BankAccount{
    public String getTradeLicenseId() {
        return tradeLicenseId;
    }

    String tradeLicenseId; //consists of Uppercase English characters only

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        super(name,balance,5000);
        if(balance<5000) throw new Exception("Insufficient Balance");
        this.tradeLicenseId = tradeLicenseId;

    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception
        boolean isValid = true;
        char[] licId = this.tradeLicenseId.toCharArray();
        for(int i=0; i<licId.length-1 && isValid; i++){
            if(licId[i]==licId[i+1]) isValid=false;
        }
        if(isValid) return;
        int n = licId.length;
        int[] count = new int[256];
        for(char x : licId) {
            count[x]++;
            if(count[x]>(n/2+n%2)) throw new Exception("Valid License can not be generated");
        }
        PriorityQueue<int[]> pq =new PriorityQueue<int[]>((a,b)-> b[1]-a[1]);
        char[] newLicId = new char[n];
        for(int i=0; i<256; i++){
            if(count[i]>0) pq.add(new int[]{i,count[i]});
        }
        int i=0;
        int[] prev = null;
        while(pq.size()>0){
            int[] curr = pq.poll();
            newLicId[i++] = (char) curr[0];
            curr[1]--;
            if(prev!=null && prev[1]!=0)
                pq.add(prev);
            prev = curr;
        }
        this.tradeLicenseId = new String(newLicId);
    }
}
