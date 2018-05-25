package multithreading.geeksforgeeks.semaphore_class;

/**
 * Created by Xiaotian on 3/26/18.
 */
public class SemaphoreDemo {
}

//http://tutorials.jenkov.com/java-concurrency/semaphores.html
//
//Counting Semaphore
//The Semaphore implementation in the previous section does not count the number of signals sent to it by take() method calls. We can change the Semaphore to do so. This is called a counting semaphore. Here is a simple implementation of a counting semaphore:
//
//public class CountingSemaphore {
//    private int signals = 0;
//
//    public synchronized void take() {
//        this.signals++;
//        this.notify();
//    }
//
//    public synchronized void release() throws InterruptedException{
//        while(this.signals == 0) wait();
//        this.signals--;
//    }
//
//}
//Bounded Semaphore
//The CoutingSemaphore has no upper bound on how many signals it can store. We can change the semaphore implementation to have an upper bound, like this:
//
//public class BoundedSemaphore {
//    private int signals = 0;
//    private int bound   = 0;
//
//    public BoundedSemaphore(int upperBound){
//        this.bound = upperBound;
//    }
//
//    public synchronized void take() throws InterruptedException{
//        while(this.signals == bound) wait();
//        this.signals++;
//        this.notify();
//    }
//
//    public synchronized void release() throws InterruptedException{
//        while(this.signals == 0) wait();
//        this.signals--;
//        this.notify();
//    }
//}

//Using Semaphores as Locks
//It is possible to use a bounded semaphore as a lock. To do so, set the upper bound to 1, and have the call to take() and release() guard the critical section. Here is an example:
//
//        BoundedSemaphore semaphore = new BoundedSemaphore(1);
//
//        ...
//
//        semaphore.take();
//
//        try{
//        //critical section
//        } finally {
//        semaphore.release();
//        }
