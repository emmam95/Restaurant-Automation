package meghankh.loginwindow;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.CheckBox;

import Model.Employee;
import Model.FetchData;
import Model.WriteData;


public class Register extends ActionBarActivity {
    int employeeID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button createAccountButton = (Button) findViewById(R.id.create_Account_But);
        final Context context = this;
        Log.d("TEST", "PLEASE DISPLAY LOG CAT");
        //fetch data in order to figure out the next available employee id number
        FetchData fetch = new FetchData();
        fetch.parseData(context);
        employeeID = fetch.getNumberOfEmployee() + 1;
        Log.d("TEST", "TESTING employee" + employeeID);
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView ssn = (TextView) findViewById(R.id.ssn);
                TextView phone = (TextView) findViewById(R.id.phoneNumber);
                TextView address = (TextView) findViewById(R.id.address);
                TextView firstName = (TextView) findViewById(R.id.firstName);
                TextView lastName = (TextView) findViewById(R.id.lastName);
                TextView password = (TextView) findViewById(R.id.password);
                CheckBox checkManager = (CheckBox) findViewById(R.id.checkManager);
                int isManager = 0;
                if (checkManager.isChecked())
                {
                    isManager = 1;
                }
                Log.d("TEST", "Create " + firstName.toString() + " " + lastName.toString() + " " + password.toString());
                //create a new employee object with the data set in the fields
                Employee employee = new Employee(employeeID, isManager, 0, ssn.getText().toString(), phone.getText().toString(), address.getText().toString(), firstName.getText().toString(), lastName.getText().toString(), 7.25, 40, password.getText().toString());
                Log.d("TEST", "Enter Data.");
                WriteData writeData = new WriteData();
                writeData.addEmployee(context, employee);
                Intent intent = new Intent(context, EditInfo.class);
                Bundle extras = new Bundle();
                extras.putString("employeeID", Integer.toString(employeeID));
                extras.putString("userID", Integer.toString(employeeID));
                extras.putString("userIsManager", Integer.toString(isManager));
                intent.putExtras(extras);
                Log.d("TEST", "Next Activity" + employeeID);
                startActivity(intent);
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
