package Concorrenza.ES;

public class Main {
    public static void main(String[] args) throws Exception{
        /*
        BankAccount account = new BankAccount();
        Thread t1 = new Thread(() -> {
            for(int i=0;i<1000;i++){
                account.deposit(1);         //non cambia riferimento ad account, solo lo stato
            }
        });
        Thread t2 = new Thread(() -> {
            for(int i=0;i<1000;i++){
                account.withdraw(1);         //non cambia riferimento ad account, solo lo stato
            }
        });
        t1.start();
        t2.start();
        t1.join();      //da eccezione ma non a runtime
        t2.join();      //idem
        System.out.println(account.getBalance());
        //il risultato non e' 0! piu thread accedono contemporaneamente a balance


         */
        /////////////////////////////////////////////////////////////////////////

        /*
        MyThread1 tr1 = new MyThread1(5);
        for(int i=0; i<1000;i++){
            Thread t = new MyThread1(i);
            t.start();
        }


        for(int i=0; i<1000;i++){
            Runnable r = new MyRunnable(i);
            Thread t = new Thread(r);
            t.start();
        }
        tr1.start();
        System.out.println("CIAO");

        for(int i=0; i<1000;i++){
            final int num=i;
            new Thread(()-> System.out.println(num));
        }

         */
    }
}

class MyThread1 extends Thread{
    private final int num;

    public MyThread1(int num) {
        this.num = num;
    }

    @Override
    public void run(){
        System.out.println(num);
    }
}

class MyRunnable implements Runnable{       //interfaccia runnable con 1 metodo
    private final int num;

    public MyRunnable(int num) {
        this.num = num;
    }

    @Override
    public void run(){
        System.out.println(num);
    }
}