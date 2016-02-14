package shauryas.p2p_voip;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Timer;

public class ConnectionClient implements Runnable{

    private String serverIpAddress;
    private int serverPortNumber;

    private OutputStream os;
    private PrintWriter pw;

    public ConnectionClient(String _serverIpAddress, int _serverPortNumber){

        serverIpAddress = _serverIpAddress;
        serverPortNumber = _serverPortNumber;

    }

    public void connectAndFormStreams() throws Exception{

        Socket s = new Socket(serverIpAddress, serverPortNumber);

        os = s.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os);
        pw = new PrintWriter(osw);

    }


    public void run() {

        //send
        pw.println(Params.msgs.get(Params.msgs.size()-1));
        pw.flush();

    }
}
