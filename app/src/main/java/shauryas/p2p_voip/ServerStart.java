package shauryas.p2p_voip;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class ServerStart extends Service implements Runnable {

    ConnectionServer cs = new ConnectionServer();

    public ServerStart() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void run() {

        try {
            cs.connectAndFormStreams();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Server Starting", Toast.LENGTH_SHORT).show();
        ConnectionServer cs = new ConnectionServer();
        Thread runServer = new Thread(this);
        runServer.start();
        return START_REDELIVER_INTENT;
    }

}


