package shauryas.p2p_voip;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import junit.framework.TestCase;

import org.apache.http.conn.util.InetAddressUtils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.List;


public class clientscreen extends ActionBarActivity implements Runnable{

    private static ConnectionClient cc;

    private EditText ipgetter;
    private EditText portgetter;
    private TextView uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientscreen);
        ipgetter = (EditText) findViewById(R.id.editText3);
        portgetter = (EditText) findViewById(R.id.editText4);
        uid = (TextView) findViewById(R.id.textView12);
        uid.setText(Params.userid.trim() + "-" + getLocalIpAddress());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_clientscreen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public String getLocalIpAddress() {
        try {
            String ipv4;
            List<NetworkInterface> nilist = Collections.list(NetworkInterface.getNetworkInterfaces());
            if(nilist.size() > 0){
                for (NetworkInterface ni: nilist){
                    List<InetAddress>  ialist = Collections.list(ni.getInetAddresses());
                    if(ialist.size()>0){
                        for (InetAddress address: ialist){
                            if (!address.isLoopbackAddress() && InetAddressUtils.isIPv4Address(ipv4 = address.getHostAddress())){
                                return ipv4;
                            }
                        }
                    }

                }
            }

        } catch (SocketException ex) {
            ex.printStackTrace();
        }
        return "";
    }

    public void setInfo(View view){

        String serverIPAddress = ipgetter.getText().toString();
        int serverPortNumber = Integer.parseInt(portgetter.getText().toString());
        String clientIPAddress = getLocalIpAddress().toString();

        cc = new ConnectionClient(serverIPAddress, serverPortNumber);

        Thread callRun = new Thread(this);
        callRun.start();

        Intent intent = new Intent(getApplicationContext(), ChatScreen.class);
        startActivity(intent);

    }

    public static void handleMessages(){

        Thread clientThread = new Thread(cc);
        clientThread.start();

    }

    public void run(){

        try {
            cc.connectAndFormStreams();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
