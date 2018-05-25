package multithreading.tutorialpoints.threadlocal_class;

/**
 * Created by Xiaotian on 3/24/18.
 */
public class ThreadLocalDemo {
}

class RunnableDemo implements Runnable {
    int counter = 0;
    ThreadLocal<Integer> threadLocalCounter = new ThreadLocal<Integer>();

    Object lock = new Object();

    public void run() {
        synchronized (lock) {
            counter++;
            System.out.println("Counter: " + counter);
        }

        if(threadLocalCounter.get() != null) {
            threadLocalCounter.set(threadLocalCounter.get().intValue() + 1);
        } else {
            threadLocalCounter.set(0);
        }
        threadLocalCounter.set(threadLocalCounter.get().intValue() + 1);
        System.out.println("threadLocalCounter: " + threadLocalCounter.get());
    }
}

class Test {

    public static void main(String args[]) {
        RunnableDemo commonInstance = new RunnableDemo();

        Thread t1 = new Thread(commonInstance);
        Thread t2 = new Thread(commonInstance);
        Thread t3 = new Thread(commonInstance);
        Thread t4 = new Thread(commonInstance);

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        // wait for threads to end
        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (Exception e) {
            System.out.println("Interrupted");
        }
    }
}
