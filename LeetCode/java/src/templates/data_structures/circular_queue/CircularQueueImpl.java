package templates.data_structures.circular_queue;

import java.util.*;

/**
 * Created by Xiaotian on 4/26/18.
 */
class CircularQueueImpl {
    // credit: https://www.geeksforgeeks.org/circular-queue-set-1-introduction-array-implementation/

    int capacity;
    int front;
    int rear;
    int[] q;

    CircularQueueImpl(int capacity) {
        this.capacity = capacity;
        front = rear = -1;
        q = new int[capacity];
    }

    void enq(int val) {
        if (front == (rear + 1) % capacity) {
            System.out.println("Queue is Full. Abort enq operation.");
            return;
        }
        else if (front == -1) { // enq first item
            front = rear = 0;
        }
        else {
            rear = (rear != capacity - 1 ? rear + 1 : 0);
        }
        q[rear] = val;
    }

    int deq() {
        if (front == -1) {
            System.out.println("Queue is Empty. Abort deq operation.");
            return -1;
        }

        int val = q[front];
        q[front] = -1;

        if (front == rear) { // deq last item
            front = rear = -1;
        }
        else {
            front = (front != capacity - 1 ? front + 1 : 0);
        }
        return val;
    }

    public static void main(String[] args) {
        CircularQueueImpl q = new CircularQueueImpl(5);
        System.out.println(Arrays.toString(q.q));
        for (int x = 1; x <= 6; x++) {
            q.enq(x);
        }
        System.out.println(Arrays.toString(q.q));
        for (int x = 1; x <= 3; x++) {
            q.deq();
        }
        System.out.println(Arrays.toString(q.q));
        for (int x = 1; x <= 3; x++) {
            q.enq(x);
        }
        System.out.println(Arrays.toString(q.q));
        for (int x = 1; x <= 3; x++) {
            q.deq();
        }
        System.out.println(Arrays.toString(q.q));
        for (int x = 1; x <= 5; x++) {
            q.deq();
        }
        System.out.println(Arrays.toString(q.q));
    }
}
