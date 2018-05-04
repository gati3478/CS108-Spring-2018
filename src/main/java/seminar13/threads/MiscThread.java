package seminar13.threads;

// Demonstrates Thread.currentThread(), and the difference
// between run() and start().
@SuppressWarnings("WeakerAccess")
class MiscThread {
    // Prints the name of the thread calling this method.
    public static void whoAmI() {
        Thread runningMe = Thread.currentThread();
        System.out.println("whoAmI thread: " + runningMe.getName());
    }

    // Simple worker thread subclass -- run() sleeps
    // and then call whoAmI().
    public static class MiscWorker extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            MiscThread.whoAmI();
        }
    }

    public static void main(String[] args) {
        MiscThread.whoAmI();
        Thread worker = new MiscWorker();
        worker.run();   // Note: never call run() like this!
        worker.start(); // Correct way to start a thread.
        System.out.println("სულ ესაა");
    }
    /*
     Output:
	 whoAmI thread:main
	 whoAmI thread:main
	 სულ ესაა
	 whoAmI thread:Thread-0
	 */
}
