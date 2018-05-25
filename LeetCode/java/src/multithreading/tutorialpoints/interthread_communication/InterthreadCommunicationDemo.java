package multithreading.tutorialpoints.interthread_communication;

/**
 * Created by Xiaotian on 3/23/18.
 */
public class InterthreadCommunicationDemo {
}
//  public void wait()
//  Causes the current thread to wait until another thread invokes the notify().

//  public void notify()
//  Wakes up a single thread that is waiting on this object's monitor.

//  public void notifyAll()
//  Wakes up all the threads that called wait() on the same object.

class Chat {
    boolean isQuestionTurn = true;
    boolean isAnswerTurn = false;

    public synchronized void question(String msg) {
        if (!isQuestionTurn) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(msg);
        isQuestionTurn = false;
        isAnswerTurn = true;
        notify();
    }

    public synchronized void answer(String msg) {
        if (!isAnswerTurn) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(msg);
        isQuestionTurn = true;
        isAnswerTurn = false;
        notify();
    }
}

class T1 implements Runnable {
    Chat m;
    String[] s1 = { "Hi", "How are you ?", "I am also doing fine!" };

    public T1(Chat m1) {
        this.m = m1;
        new Thread(this, "question").start();
    }

    public void run() {
        for (int i = 0; i < s1.length; i++) {
            m.question(s1[i]);
        }
    }
}

class T2 implements Runnable {
    Chat m;
    String[] s2 = { "Hi", "I am good, what about you?", "Great!" };

    public T2(Chat m2) {
        this.m = m2;
        new Thread(this, "answer").start();
    }

    public void run() {
        for (int i = 0; i < s2.length; i++) {
            m.answer(s2[i]);
        }
    }
}
class Test {
    public static void main(String[] args) {
        Chat m = new Chat();
        new T1(m);
        new T2(m);
    }
}
