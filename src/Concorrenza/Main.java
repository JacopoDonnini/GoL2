package Concorrenza;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//PROGRAMMAZIONE CONCORRENTE
//programmazione dove piu processi avvengono contemporaneamente (multitasking)
//scheduler decide a chi allocare risorse e per quanto tempo
//fisicamente il parallelismo avviene su processori separati (se disponibili)
public class Main {
    public static void main(String[] args) {

        Thread t = new MyThread1(12);
        t.start();
        for (int i = 0; i < 1000; i++) {
            new MyThread1(i).start();
        }
        System.out.println("Ciao");

        for (int i = 0; i < 1000; i++) {
            MyRunnable1 r = new MyRunnable1(i);
            Thread k = new Thread(r);
            k.start();
        }
        System.out.println("Ciao ancora");

        for(int i=0;i<1000;i++){
            //new Thread(()-> System.out.println(i)); //PROBLEMA i e' condivisa e non e' final!!!
            final int res = i;
            new Thread(() -> System.out.println(res));
        }

        //ExecutorService execService = Executors.newSingleThreadExecutor();
        ExecutorService execServiceMulti = Executors.newCachedThreadPool();
        execServiceMulti.submit(() -> System.out.println("Ciao1"));
        execServiceMulti.submit(() -> System.out.println("Ciao2"));
        execServiceMulti.shutdown();
    }
}
class MyThread1 extends Thread{     //sintassi thread
    private final int num;

    public MyThread1(int num){      //costruttore thread (passare param)
        this.num=num;
    }
    @Override
    public void run(){              //funz eseguita con t.start;
        System.out.println(num);
    }
}

class MyRunnable1 implements Runnable{  //sintassi processo (interfaccia funz solo run)
    private final int num;

    public MyRunnable1(int num){        //costruttore thread (passare param)
        this.num=num;
    }
    @Override
    public void run(){                  //funz eseguita con t.start;
        System.out.println(num);
    }
}