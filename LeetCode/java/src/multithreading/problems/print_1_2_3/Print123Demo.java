package multithreading.problems.print_1_2_3;


import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Xiaotian on 3/25/18.
 */
public class Print123Demo {
}

class Test {

    public static void main(String[] args) {
        Printer p = new Printer();

        Task[] tasks = new Task[3];
        for (int i = 0; i < tasks.length; i++) {
            tasks[i] = new Task(p, i + 1);
        }

        int MAX_THREAD_POOL_NUM = 3;
        ExecutorService pool = Executors.newFixedThreadPool(MAX_THREAD_POOL_NUM);
        for (Task task : tasks) {
            pool.execute(task);
        }
        pool.shutdown();
    }
}

class Task implements Runnable {
    Printer printer;
    int number;

    Task(Printer printer, int n) {
        this.printer = printer;
        this.number = n;
    }

    @Override
    public void run() {
        while (true) {
            printer.printNumber(number);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

}

class Printer{
    public volatile int turn = 1;
    public final ReentrantLock lock = new ReentrantLock();
    public final Condition isMyTurn = lock.newCondition();

    public void printNumber(int number) {
        lock.lock();

        try {
            while (turn != number)
                isMyTurn.await();

            System.out.println(Thread.currentThread().getName() + ": " + number);

            turn = number + 1;
            isMyTurn.signalAll();

            if (turn > 3) {
                turn = 1;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
