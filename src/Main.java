import java.util.Timer;
import java.util.TimerTask;

public class Main {

    public static void main(String[] args) {

        Timer timer = new Timer();
        TimerTask checkForUpdates = new TimerTask() {
            @Override
            public void run() {
                try {
                    CommHandler.checkForUpdates();
                } catch (Exception e) {}
            }
        };

        timer.schedule(checkForUpdates, 0, 1000);

        System.out.println(">> Running");

    }
}
