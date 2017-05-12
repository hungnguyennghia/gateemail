package vc.util;


import java.util.*;
//import sms.common.Logger;

public class Queue {
    //private static Logger logger = new Logger("Queue");

    protected LinkedList queue = null;
    protected Object monitor   = null;

    /** Name of the queue; also name of the folder stores pdu(s) of this queue. */
    private static int count = 0;
    private String name = "queue_" + ++count;
    public String getName() {
        return name;
    }
    public void setName(String value) {
        this.name = value;
    }



    private static Vector queueList = new Vector();
    public static String showQueuesSize() {
        String response = "Size of Queues:\n";
        Queue q = null;
        for (int i=0; i < queueList.size(); i++) {
            q = (Queue) queueList.get(i);
            response += q.getName() + ": " + q.size() + "\n";
        }
        return response;
    }


    /** Stores data of all queues in this application. */
    public static int storeAllQueueData() {
        System.out.println("Storing all queue(s)... ");
        Queue q = null;
        int counter = 0;
        for (int i=0; i < queueList.size(); i++) {
            q = (Queue) queueList.get(i);
            if (!q.isEmpty()) {
                q.storeData();
                counter++;
            }
        }
        return counter;
    }

    public Queue() {
        this.monitor = this;
        this.queue   = new LinkedList();

        queueList.add(this);
    }

    public Queue(String name) {
        this.monitor = this;
        this.queue   = new LinkedList();
        this.name = name;

        queueList.add(this);
    }


    /**
     * This method is used by a consummer. If you attempt to remove
     * an object from an queue is empty queue, you will be blocked
     * (suspended) until an object becomes available to remove. A
     * blocked thread will thus wake up.
     * @return the first object (the one is removed).
     */
    public Object dequeue() {
        synchronized (monitor) {
            while (queue.isEmpty()) { //Threads are blocked
                try { //if the queue is empty.
                    monitor.wait(); //wait until other thread call notify().
                } catch (InterruptedException ex) {}
            }
            Object item = queue.removeFirst();
            return item;
        }
    }

    public void enqueue(Object item) {
        synchronized (monitor) {
            queue.addLast(item);
            monitor.notifyAll();
        }
    }

    public int size() {
        synchronized (monitor) {
            return queue.size();
        }
    }

    public boolean isEmpty() {
        synchronized (monitor) {
            return queue.isEmpty();
        }
    }

    protected Collection dequeueAll() {
        List list = null;
        synchronized (monitor) {
            list = Arrays.asList(queue.toArray());
            queue.clear();
        }
        return list;
    }

    protected void enqueueAll(Collection c) {
        synchronized (monitor) {
            queue.addAll(c);
            monitor.notifyAll();
        }
    }

    //The app. calls this method before exited.
    public int storeData() {return 0; }
}
