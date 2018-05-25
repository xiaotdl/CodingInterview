package multithreading.tutorialpoints.condition_interface;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Xiaotian on 3/24/18.
 */
public class ConditionDemo {
}

class Test {

    public static void main(String[] args) throws InterruptedException {
        ItemQueue itemQueue = new ItemQueue(2);

        //Create a producer and a consumer.
        Thread producer = new Producer(itemQueue);
        producer.setName("producerThread");
        Thread consumer = new Consumer(itemQueue);
        consumer.setName("consumerThread");

        //Start both threads.
        producer.start();
        consumer.start();

        //Wait for both threads to terminate.
        producer.join();
        consumer.join();
    }

    // http://grepcode.com/file/repository.grepcode.com/java/root/jdk/openjdk/8u40-b25/java/util/concurrent/locks/Condition.java?av=f
    static class ItemQueue {
        private Object[] items = null;
        private int capacity;
        private int count = 0;
        private int placeIndex = 0;
        private int removeIndex = 0;

        private final ReentrantLock lock;
        private final Condition notEmpty;
        private final Condition notFull;

        public ItemQueue(int capacity) {
            this.items = new Object[capacity];
            this.capacity = capacity;
            lock = new ReentrantLock();
            notEmpty = lock.newCondition();
            notFull = lock.newCondition();
        }

        public void add(Object item) throws InterruptedException {
            lock.lock();

            // blocking
            while(count >= items.length) {
                notFull.await();
            }

            items[placeIndex] = item;
            placeIndex = (placeIndex + 1) % capacity;
//            putIndex = (i == capacity) ? 0 : i;
            ++count;

            //Notify the consumer that there is data available.
            notEmpty.signal();
            lock.unlock();
        }

        public Object remove() throws InterruptedException {
            Object item = null;

            lock.lock();

            // blocking
            while (count <= 0) {
                notEmpty.await();
            }

            item = items[removeIndex];
            removeIndex = (removeIndex + 1) % items.length;
            --count;

            //Notify the producer that there is space available.
            notFull.signal();
            lock.unlock();

            return item;
        }

        public boolean notEmpty() {
            return (items.length == 0);
        }
    }

    static class Producer extends Thread {
        private final ItemQueue queue;

        public Producer(ItemQueue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                for (int number = 1; number <= 5; number++) {
                    System.out.println("[Producer]: " + number);
                    queue.add(number);
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    static class Consumer extends Thread {
        private final ItemQueue queue;

        public Consumer(ItemQueue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {

            try {

                do {
                    Object number = queue.remove();
                    System.out.println("[Consumer]: " + number);

                    Thread.sleep(1000);

                    if(number == null) {
                        return;
                    }
                } while(!queue.notEmpty());
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
