package multithreading.problems.print_number;

/**
 * Created by Xiaotian on 3/25/18.
 */
public class PrintNumberDemo {
}

class Test {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter(50);
        CounterTask counterTask = new CounterTask(counter);

        Thread[] threads = new Thread[5];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(counterTask);
            threads[i].start();
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }
    }
}

class Counter {
    private int n;
    private int i;

    public Counter(int n) {
        this.n = n;
        i = 0;
    }

    public synchronized int getNext() {
        if (i >= n) return -1;

        i++;
        return i;
    }
}

class CounterTask implements Runnable {
    Counter counter;
    public CounterTask(Counter counter) {
        this.counter = counter;
    }
    @Override
    public void run() {
        while (true) {
            synchronized (counter) {
                int next = counter.getNext();
                if (next == -1) break;
                System.out.println(Thread.currentThread().getName() + ": " + next);
            }
        }
    }
}
