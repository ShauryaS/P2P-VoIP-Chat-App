package shauryas.p2p_voip;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity{

    private int connectionid;
    private int setterid;

    public TextView tvuid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Params.firstStartUp==true) {
            startService(new Intent(getApplicationContext(), ServerStart.class));
            Params.firstStartUp = false;
        }
        setContentView(R.layout.activity_main);
        connectionid = R.id.button2;
        setterid = R.id.button3;
        tvuid = (TextView) findViewById(R.id.textView7);
        tvuid.setText(Params.userid);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void changeScreen(View view){

        if(view.getId()==connectionid){
            startActivity(new Intent(getApplicationContext(), clientscreen.class));
        }
        if(view.getId()==setterid){
            startActivity(new Intent(getApplicationContext(), setup.class));
        }

    }

}
