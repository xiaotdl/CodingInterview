package multithreading.tutorialpoints.readwritelock_interface;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Xiaotian on 3/24/18.
 */
public class ReadWriteLockDemo {
}

class Test {
    private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);
    private static String message = "a";

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new WriterA());
        t1.setName("Writer A");

        Thread t2 = new Thread(new WriterB());
        t2.setName("Writer B");

        int n = 5;
        Thread[] readers = new Thread[n];
        for (int i = 0; i < n; i++) {
            readers[i] = new Thread(new Reader());
            readers[i].setName("Reader" + i);
        }

        t1.start();
        for (int i = 0; i < n; i++) {
            readers[i].start();
            if (i == 3) {
                t2.start();
            }
        }


        t1.join();
        t2.join();
        for (int i = 0; i < n; i++) {
            readers[i].join();
        }
    }

    static class Reader implements Runnable {

        public void run() {

            if(lock.isWriteLocked()) {
                System.out.println(Thread.currentThread().getName() + ": " + "Write Lock Present.");
            }

            lock.readLock().lock();

            try {
                Long duration = (long) (Math.random() * 10000);
                System.out.println(Thread.currentThread().getName()
                        + "  Time Taken " + (duration / 1000) + " seconds.");
                Thread.sleep(duration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() +": "+ message );
                lock.readLock().unlock();
            }
        }
    }

    static class WriterA implements Runnable {

        public void run() {
            lock.writeLock().lock();

            try {
                Long duration = (long) (Math.random() * 10000);
                System.out.println(Thread.currentThread().getName()
                        + "  Time Taken " + (duration / 1000) + " seconds.");
                Thread.sleep(duration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                message = message.concat("a");
                lock.writeLock().unlock();
            }
        }
    }

    static class WriterB implements Runnable {

        public void run() {
            lock.writeLock().lock();

            try {
                Long duration = (long) (Math.random() * 10000);
                System.out.println(Thread.currentThread().getName()
                        + "  Time Taken " + (duration / 1000) + " seconds.");
                Thread.sleep(duration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                message = message.concat("b");
                lock.writeLock().unlock();
            }
        }
    }
}

