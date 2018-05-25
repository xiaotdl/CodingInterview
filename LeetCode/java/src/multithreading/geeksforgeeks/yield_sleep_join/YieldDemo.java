package multithreading.geeksforgeeks.yield_sleep_join;
// Java program to illustrate yield() method
// in Java
import java.lang.*;

/**
 * Created by Xiaotian on 3/25/18.
 */
public class YieldDemo {
}

// MyThread extending Thread
class MyThread extends Thread
{
    public void run()
    {
        for (int i=0; i<5 ; i++)
            System.out.println(Thread.currentThread().getName()
                    + " in control");
    }
}

// Driver Class
class yieldDemo
{
    public static void main(String[]args)
    {
        MyThread t = new MyThread();
        t.setName("my-thread");
        t.start();

        for (int i=0; i<5; i++)
        {
            // Control passes to child thread
            Thread.yield();

            // After execution of child Thread
            // main thread takes over
            System.out.println(Thread.currentThread().getName()
                    + " in control");
        }
    }
}

