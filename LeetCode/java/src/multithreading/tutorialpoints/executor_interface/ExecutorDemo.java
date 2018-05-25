package multithreading.tutorialpoints.executor_interface;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Xiaotian on 3/24/18.
 */
public class ExecutorDemo {
}

class Test {

    public static void main(final String[] arguments) throws InterruptedException {
        Executor executor = Executors.newCachedThreadPool();
        executor.execute(new Task());
        ThreadPoolExecutor pool = (ThreadPoolExecutor)executor;
        pool.shutdown();
    }

    static class Task implements Runnable {

        public void run() {

            try {
                Long duration = (long) (Math.random() * 5 + 1);
                System.out.println("Running a " + duration + " seconds task!");
                TimeUnit.SECONDS.sleep(duration);
                System.out.println("Task Completed");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
