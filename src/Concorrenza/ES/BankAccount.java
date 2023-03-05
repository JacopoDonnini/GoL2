package Concorrenza.ES;

public class BankAccount {
    private int balance;
    //syncrhronized fa una lock/unlock sulla classe padre della funzione specificata
    public synchronized void withdraw(int amount){
        balance -= amount;
    }

    public synchronized void deposit(int amount){
        balance += amount;
    }

    /*esempio di deadlock*/
    public synchronized void transfer(int amount, BankAccount destination){
        balance -= amount;
        synchronized (destination){
            destination.balance += amount;
        }
        //codice sopra equiv a:
        //balance -= amount;
        //destination.deposit(amount);
    }

    public void transfersenzaDeadlock(int amount, BankAccount dest){
        synchronized (this){
            balance -= amount;
        }
        synchronized (dest){
            dest.balance += amount;
        }
        //codice sopra equiv a:
        //this.withdraw(amount)
        //destination.deposit(amount);
    }
    public synchronized int getBalance() {
        return balance;
    }
}
