package Concorrenza.ES;

public class BankAccount2 {
    private int balance;

    //assumo tetto minimo 1000 altrimenti non posso prelevare
    public synchronized void withdraw(int amount) throws InterruptedException{
        //while(true){
        //    if(amount < balance){
        //    balance -= amount;
        //    break;}
        //} METODO SBAGLIATO! non e' synchronized

        //while(true){
        //    synchronized (this){
        //        if(amount < balance){
        //            balance -= amount;
        //            break;}
        //    }
        //} CORRETTO MA CPU CONTROLLA CONDIZIONE A OGNI CICLO -> SPRECO ENERGIA

        while(amount > balance) {   //lock libero finche condizione verificata
            this.wait();            //wait causa rilascio lock
        }                           //nel ciclo while perche se si sblocca e cond non verificata
        balance -= amount;
        this.notifyAll();           //risveglia tutti i thread in che aspettano unlock
    }

    //assumo che amount sia < 1000 altrimenti non posso depositare
    public synchronized void deposit(int amount) throws InterruptedException{
        while(balance+amount>1000){
            this.wait();
        }
        balance += amount;
        this.notifyAll();           //sblocca tutti i thread in wait
    }

    public int getBalance() {
        return balance;
    }
}
