package seminar14.semaphore;

@SuppressWarnings("WeakerAccess")
public class TesterTask implements Runnable {
    private SharedData sharedData;

    public TesterTask(SharedData sharedData) {
        this.sharedData = sharedData;
    }

    @Override
    public void run() {
        // Just invoke method guarded with semaphore
        sharedData.getValue();
    }

}
