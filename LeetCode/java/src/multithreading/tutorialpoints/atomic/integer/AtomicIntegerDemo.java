package multithreading.tutorialpoints.atomic.integer;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Xiaotian on 3/24/18.
 */
public class AtomicIntegerDemo {
}

// Thread Unsafe
class Test1 {

    static class Counter {
        private int c = 0;

        public void increment() {
            c++;
        }

        public int value() {
            return c;
        }
    }

    public static void main(final String[] arguments) throws InterruptedException {
        final Counter counter = new Counter();

        //1000 threads
        for(int i = 0; i < 1000; i++) {

            new Thread(new Runnable() {
                public void run() {
                    counter.increment();
                }
            }).start();
        }
        Thread.sleep(5000);
        System.out.println("Final number (should be 1000): " + counter.value());
    }
}


// Thread Safe
class Test2 {

    static class Counter {
        private AtomicInteger c = new AtomicInteger(0);

        public void increment() {
            c.getAndIncrement();
        }

        public int value() {
            return c.get();
        }
    }

    public static void main(final String[] arguments) throws InterruptedException {
        final Counter counter = new Counter();

        //1000 threads
        for(int i = 0; i < 1000 ; i++) {

            new Thread(new Runnable() {
                public void run() {
                    counter.increment();
                }
            }).start();
        }
        Thread.sleep(6000);
        System.out.println("Final number (should be 1000): " + counter.value());
    }
}

