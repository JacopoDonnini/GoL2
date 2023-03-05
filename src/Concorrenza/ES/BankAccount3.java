package Concorrenza.ES;

import java.util.concurrent.atomic.AtomicInteger;

public class BankAccount3 {
    private AtomicInteger balance;

    public BankAccount3(){
        balance = new AtomicInteger();
    }
    public void withdraw(int amount){
        balance.addAndGet(-amount);
    }
    public void deposit(int amount){
        balance.addAndGet(amount);
    }

    public int getBalance() {
        return balance.get();
    }
}
