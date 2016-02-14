package shauryas.p2p_voip;

import java.io.*;
import java.net.*;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;

public class ConnectionServer implements Runnable{

    public static int listeningPortNumber = 6748;

    private InputStream is;
    private BufferedReader br;

    public ServerSocket serverSocket;
    public Socket s;

    private ChatScreen chatScreen = new ChatScreen();;

    public void connectAndFormStreams() throws Exception{

        serverSocket = new ServerSocket();
        serverSocket.setReuseAddress(true);
        serverSocket.bind(new InetSocketAddress(listeningPortNumber));

        while(!Thread.interrupted()){
            
            Log.i("Connect","Before accept()");

            s = serverSocket.accept();

            Log.i("Connect", "after accept");

            is = s.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            br = new BufferedReader(isr);

            Thread serverThread = new Thread(this);
            serverThread.start();

        }

        s.close();
        serverSocket.close();

    }

    public void run() {

        //read
        while(true){

            try {
                Params.msgs.add(br.readLine());
                chatScreen.refresh();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

}
