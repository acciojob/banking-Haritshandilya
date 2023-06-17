package com.driver;

public class SavingsAccount extends BankAccount{
    public double getRate() {
        return rate;
    }

    public double getMaxWithdrawalLimit() {
        return maxWithdrawalLimit;
    }

    double rate;
    double maxWithdrawalLimit;

    public SavingsAccount(String name, double balance, double maxWithdrawalLimit, double rate) {
        // minimum balance is 0 by default
        super(name,balance,0);
        this.maxWithdrawalLimit = maxWithdrawalLimit;
        this.rate = rate;
    }
    public void withdraw(double amount) throws Exception {
        // Might throw the following errors:
        // 1. "Maximum Withdraw Limit Exceed" : If the amount exceeds maximum withdrawal limit
        // 2. "Insufficient Balance" : If the amount exceeds balance
        if(amount>this.maxWithdrawalLimit) throw new Exception("Maximum Withdraw Limit Exceed");
        double remainingAmount = this.getBalance()-amount;
        if(remainingAmount<this.getMinBalance()) throw new Exception("Insufficient Balance");
        this.setBalance(remainingAmount);
    }

    public double getSimpleInterest(int years){
        // Return the final amount considering that bank gives simple interest on current amount
        return this.getBalance() * ( 1 + this.rate * years / 100);
    }

    public double getCompoundInterest(int times, int years){
        // Return the final amount considering that bank gives compound interest on current amount given times per year
        return this.getBalance()*Math.pow((1+this.rate/100),years*times)-this.getBalance();
    }

}
