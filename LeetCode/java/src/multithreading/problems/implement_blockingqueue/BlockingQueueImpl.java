package multithreading.problems.implement_blockingqueue;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

/**
 * Created by Xiaotian on 4/24/18.
 */
// BlockingQueueImpl via synchronized, wait(), notifyAll()
class BlockingQueueImpl {
    // credit: https://dzone.com/articles/java-concurrency-blocking-queu

    private List queue = new LinkedList();
    private int  limit = 10;

    public BlockingQueueImpl(int limit){
        this.limit = limit;
    }


    public synchronized void enqueue(Object item)
            throws InterruptedException  {
        while(this.queue.size() == this.limit) {
            wait();
        }
        if(this.queue.size() == 0) {
            notifyAll();
        }
        this.queue.add(item);
    }


    public synchronized Object dequeue()
            throws InterruptedException{
        while(this.queue.size() == 0){
            wait();
        }
        if(this.queue.size() == this.limit){
            notifyAll();
        }

        return this.queue.remove(0);
    }

}

// BlockingQueueImpl via lock.lock()/unlock() and lock.condition.await()/signal()
class BoundedBuffer {
    // credit: https://docs.oracle.com/javase/6/docs/api/java/util/concurrent/locks/Condition.html
    final Lock lock = new ReentrantLock();
    final Condition notFull  = lock.newCondition();
    final Condition notEmpty = lock.newCondition();

    final Object[] items = new Object[100];
    int putptr, takeptr, count;

    public void put(Object x) throws InterruptedException {
        lock.lock();
        try {
            while (count == items.length)
                notFull.await();
            items[putptr] = x;
            if (++putptr == items.length) putptr = 0;
            ++count;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public Object take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0)
                notEmpty.await();
            Object x = items[takeptr];
            if (++takeptr == items.length) takeptr = 0;
            --count;
            notFull.signal();
            return x;
        } finally {
            lock.unlock();
        }
    }
}
