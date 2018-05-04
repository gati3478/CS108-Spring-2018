package seminar13.threads;

public class RunnableExample implements Runnable {

    @Override
    public void run() {
        System.out.println("არის, არსებობს!");
    }

    public static void main(String[] args) {
        Thread a = new Thread(new RunnableExample());
        a.start();
    }

}
