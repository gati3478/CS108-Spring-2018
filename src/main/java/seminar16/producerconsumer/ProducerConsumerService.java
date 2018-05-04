package seminar16.producerconsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@SuppressWarnings("WeakerAccess")
public class ProducerConsumerService {

    public static class Producer implements Runnable {

        private BlockingQueue<Message> queue;

        public Producer(BlockingQueue<Message> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            // Produce messages
            for (int i = 0; i < 100; ++i) {
                Message msg = new Message("" + i);
                try {
                    Thread.sleep(i);
                    queue.put(msg);
                    System.out.println("Produced " + msg.getMessage());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // Adding exit message
            Message msg = new Message("Exit");
            try {
                queue.put(msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static class Consumer implements Runnable {

        private BlockingQueue<Message> queue;

        public Consumer(BlockingQueue<Message> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                Message msg;
                // Consuming messages until exit message is received
                while (!(msg = queue.take()).getMessage().equals("Exit")) {
                    Thread.sleep(100);
                    System.out.println("Consumed " + msg.getMessage());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class Message {
        private String message;

        public Message(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

    }

    public static void main(String[] args) {
        // Creating BlockingQueue of size 10
        BlockingQueue<Message> queue = new ArrayBlockingQueue<>(10);
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);

        // Starting producer to produce messages in queue
        new Thread(producer).start();
        // Starting consumer to consume messages from queue
        new Thread(consumer).start();
        System.out.println("Producer and Consumer has been started");
        
        // Note: Try implementing the given problem using semaphores
        // Note: Try implementing the given problem without using semaphores or other synchronization mechanisms
    }
}
