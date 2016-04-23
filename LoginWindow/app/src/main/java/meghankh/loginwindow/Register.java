package meghankh.loginwindow;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import Model.Employee;


public class Register extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button createAccountButton = (Button) findViewById(R.id.create_Account_But);
        final Context context = this;
        File path = context.getFilesDir();
        File file = new File(path, "text.txt");
        int length = (int) file.length();
        byte[] bytes = new byte[length];
        try {
            FileInputStream in = new FileInputStream(file);
            try{
                in.read(bytes);
            }
            catch (IOException e){
            }
            finally {
                in.close();
            }
        }
        catch (IOException e){
        }
        TextView firstName = (TextView) findViewById(R.id.firstName);
        char[] chars = new char[length];
        for (int i = 0; i < length; i++)
        {
            chars[i] = (char) bytes[i];
        }
        firstName.setText(chars, 0, length);
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView ssn = (TextView) findViewById(R.id.ssn);
                TextView phone = (TextView) findViewById(R.id.phoneNumber);
                TextView address = (TextView) findViewById(R.id.address);
                TextView firstName = (TextView) findViewById(R.id.firstName);
                TextView lastName = (TextView) findViewById(R.id.lastName);
                TextView password = (TextView) findViewById(R.id.password);
                Employee employee = new Employee(0, ssn.toString(), phone.toString(), address.toString(), firstName.toString(), lastName.toString(), 7.25, 40, password.toString());
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
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
}
