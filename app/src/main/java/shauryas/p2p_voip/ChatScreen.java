package shauryas.p2p_voip;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class ChatScreen extends ActionBarActivity {

    private EditText msgToServerGetter;
    private ArrayAdapter<String> adapter;
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatscreen);

        msgToServerGetter = (EditText) findViewById(R.id.editText5);

        adapter = new ArrayAdapter<String>(this, R.layout.list_msg, Params.msgs);
        list = (ListView) findViewById(R.id.messages);
        list.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_chat_screen, menu);
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

    public void sendPressed(View view){

        Params.msgs.add(Params.userid + ": " + msgToServerGetter.getText().toString());
        clientscreen.handleMessages();

    }

    public void refresh(){

        Log.e("check", "working");
        //setContentView(R.layout.activity_chatscreen);

    }

}
