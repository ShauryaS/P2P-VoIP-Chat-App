package shauryas.p2p_voip;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class setup extends ActionBarActivity {

    private EditText getUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
        getUser = (EditText) findViewById(R.id.editText);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_setup, menu);
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

    public void save(View view) throws FileNotFoundException {

        Params.userid = getUser.getText().toString();
        //write();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));

    }

    public void write() throws FileNotFoundException {

        FileOutputStream fos = new FileOutputStream("Info.txt");
        OutputStreamWriter osw = new OutputStreamWriter(fos);
        PrintWriter pw = new PrintWriter(osw);

        pw.println(Params.userid);
        pw.flush();
        pw.close();

    }

}
