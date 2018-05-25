package multithreading.problems.h2o;

import java.util.*;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.locks.*;

/**
 * Created by Xiaotian on 4/25/18.
 */
public class Solution {
}

class H2O {
    int hCnt = 0;
    int oCnt = 0;
    Lock lock = new ReentrantLock();
    Condition cvH = lock.newCondition();
    Condition cvO = lock.newCondition();

    public void addH() throws InterruptedException {
        System.out.println("adding H");
        try {
            lock.lock();
            hCnt++;
            if (hCnt >= 2 && oCnt >= 1) {
                hCnt -= 2;
                oCnt -= 1;
                cvH.signal();
                cvO.signal();
                System.out.println("H2O");
            }
            else cvH.await();
        }
        finally {
            lock.unlock();
        }
    }

    public void addO() throws InterruptedException {
        System.out.println("adding O");
        try {
            lock.lock();
            oCnt++;
            if (hCnt >= 2 && oCnt >= 1) {
                hCnt -= 2;
                oCnt -= 1;
                cvH.signal();
                cvH.signal();
                System.out.println("H2O");
            }
            else cvO.await();
        }
        finally {
            lock.unlock();
        }
    }
}

class HProducer implements Runnable {
    private H2O h2o;

    public HProducer(H2O h2o) {
        this.h2o = h2o;
    }

    @Override
    public void run() {
        try {
            h2o.addH();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

class OProducer implements Runnable {
    private H2O h2o;

    public OProducer(H2O h2o) {
        this.h2o = h2o;
    }

    @Override
    public void run() {
        try {
            h2o.addO();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

class Demo {
    final private static H2O h2o = new H2O();

    public static void main(String[] args) throws InterruptedException {
        List<Thread> threadPool = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threadPool.add(new Thread(new HProducer(h2o)));
        }
        for (int i = 0; i < 5; i++) {
            threadPool.add(new Thread(new OProducer(h2o)));
        }
        Collections.shuffle(threadPool);

        for (Thread thread : threadPool) {
            thread.start();
        }
        for (Thread thread : threadPool) {
            thread.join();
        }
    }
}

