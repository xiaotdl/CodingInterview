package multithreading.problems.multi_producer_multi_consumer_using_blockingqueue;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Xiaotian on 3/25/18.
 */
public class ProducerConsumerDemo {
}

class Test {

    public static void main(String[] args) {
        ArrayBlockingQueue<Integer> buffer = new ArrayBlockingQueue<>(2);

        Producer p1 = new Producer(buffer, "Producer 1");
        Producer p2 = new Producer(buffer, "Producer 2");
        Producer p3 = new Producer(buffer, "Producer 3");
        Producer p4 = new Producer(buffer, "Producer 4");
        Producer p5 = new Producer(buffer, "Producer 5");

        Consumer c1 = new Consumer(buffer, "Consumer 1");
        Consumer c2 = new Consumer(buffer, "Consumer 2");
        Consumer c3 = new Consumer(buffer, "Consumer 3");
        Consumer c4 = new Consumer(buffer, "Consumer 4");
        Consumer c5 = new Consumer(buffer, "Consumer 5");

        p1.start();
        p2.start();
        p3.start();
        p4.start();
        p5.start();

        c1.start();
        c2.start();
        c3.start();
        c4.start();
        c5.start();
    }
}

class Producer extends Thread {

    private BlockingQueue<Integer> buffer;
    private Random random;

    public Producer(BlockingQueue<Integer> buffer, String threadName) {
        this.buffer = buffer;
        setName(threadName);
        random = new Random();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int data = random.nextInt(100);
            try {
                //Inserts the specified element into this queue
                //waits if necessary for space to become available.
                buffer.put(data);
                System.out.println(this.getName() + " put: " + data);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer extends Thread {

    private BlockingQueue<Integer> buffer;

    public Consumer(BlockingQueue<Integer> buffer, String threadName) {
        this.buffer = buffer;
        setName(threadName);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                //take() Retrieves and removes the head of this queue
                //waits if necessary until an element becomes available.
                int data = buffer.take();
                System.out.println(this.getName() + " take: " + data);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
