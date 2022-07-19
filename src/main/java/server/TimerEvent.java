package server;

import net.minecraft.server.*;
import net.minecraftforge.server.ServerLifecycleHooks;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

public class TimerEvent {
    private final Timer timer;
    private boolean graceTime;

    public TimerEvent() {
        timer = new Timer("CheckForNoPlayers");
        graceTime = true;
        // Delay of 10 minutes before server starts checking
        timer.scheduleAtFixedRate(new OnTimerEvent(), 300000, 30000);
    }

    private class OnTimerEvent extends TimerTask {
        private MinecraftServer server;

        public void run() {
            if (server == null) {
                server = ServerLifecycleHooks.getCurrentServer();
            }

            var playerCount = server.getPlayerCount();
            System.out.printf("There are %d players online%n", playerCount);

            if (playerCount == 0 && graceTime) {
                graceTime = false;
            } else if (playerCount == 0) {
                // Shutdown the server
                System.out.println("No players online, shutting down server.");
                try {
                    new File("/home/ec2-user/.stopserver").createNewFile();
                    timer.cancel();
                    server.stopServer();
                    Thread.sleep(10000);
                    System.exit(0);
                } catch (Exception e) { }
            } else {
                graceTime = true;
            }
        }
    }
}
