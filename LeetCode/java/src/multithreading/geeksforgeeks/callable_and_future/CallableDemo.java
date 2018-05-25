package multithreading.geeksforgeeks.callable_and_future;


// Java program to illustrate Callable
// to return a random number
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * Created by Xiaotian on 3/25/18.
 */
public class CallableDemo {
}

//class CallableExample implements Callable
//{
//
//    public Object call() throws Exception
//    {
//        // Create random number generator
//        Random generator = new Random();
//
//        Integer randomNumber = generator.nextInt(5);
//
//        // To simulate a heavy computation,
//        // we delay the thread for some random time
//        Thread.sleep(randomNumber * 1000);
//
//        return randomNumber;
//    }
//}
