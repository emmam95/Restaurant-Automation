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
import android.widget.EditText;

import java.io.File;

import Model.Employee;
import Model.EmployeeInfo;
import Model.FetchData;
import Model.Utils;
import Model.WriteData;

public class ViewEmployeeInfo extends ActionBarActivity {

    int employeeID;
    Employee employee;
    FetchData fetch;
    int userID;
    int userIsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_employee_info);

        Log.d("TEST", "View Info Activity");
        final Context context = this;
        Bundle extras = getIntent().getExtras();
        employeeID = 0;
        userIsManager = 0;
        if (extras != null)
        {
            employeeID = new Integer(extras.getString("employeeID")).intValue();
            Log.d("TEST", "employeeID" + employeeID);
            userID = new Integer(extras.getString("userID")).intValue();
            userIsManager = new Integer(extras.getString("userIsManager")).intValue();
            Log.d("TEST", extras.getString("userIsManager"));
        }
        File path = context.getFilesDir();
        File file = new File(path, "employee.txt");
        fetch = new FetchData();
        fetch.parseData(context);
        Log.d("TEST", "employeeID" + employeeID);
        employee = fetch.getEmployee(employeeID);
        EmployeeInfo info = employee.getEmployeeInfo();
        Log.d("TEST", "Employee: " + employeeID + " is " + info.getFirstName());
        Utils utils = new Utils();

        TextView firstName = (TextView) findViewById(R.id.firstName);
        int byteLength = info.getFirstName().length();
        byte[] bytes = info.getFirstName().getBytes();
        char[] chars = utils.converToCharArray(bytes, byteLength);
        firstName.setText(chars, 0, byteLength);

        TextView ssn = (TextView) findViewById(R.id.ssn);
        byteLength = info.getSSN().toString().length();
        bytes = info.getSSN().toString().getBytes();
        chars = utils.converToCharArray(bytes, byteLength);
        ssn.setText(chars, 0, byteLength);

        TextView phone = (TextView) findViewById(R.id.phoneNumber);
        byteLength = info.getPhone().length();
        bytes = info.getPhone().getBytes();
        chars = utils.converToCharArray(bytes, byteLength);
        phone.setText(chars, 0, byteLength);

        TextView address = (TextView) findViewById(R.id.address);
        byteLength = info.getAddress().length();
        bytes = info.getAddress().getBytes();
        chars = utils.converToCharArray(bytes, byteLength);
        address.setText(chars, 0, byteLength);

        TextView lastName = (TextView) findViewById(R.id.lastName);
        byteLength = info.getLastName().length();
        bytes = info.getLastName().getBytes();
        chars = utils.converToCharArray(bytes, byteLength);
        lastName.setText(chars, 0, byteLength);

        TextView password = (TextView) findViewById(R.id.password);
        byteLength = info.getPassword().length();
        bytes = info.getPassword().getBytes();
        chars = utils.converToCharArray(bytes, byteLength);
        password.setText(chars, 0, byteLength);

        Button logoutButton = (Button) findViewById(R.id.logoutButton);

        Button fireButton = (Button) findViewById(R.id.fireButton);

        EditText lookupFirstName = (EditText) findViewById(R.id.lookupFirstName);
        EditText lookupLastName = (EditText) findViewById(R.id.lookupLastName);
        Button lookupButton = (Button) findViewById(R.id.lookupButton);

        TextView terminatedMessage = (TextView) findViewById(R.id.terminatedMessage);
        terminatedMessage.setVisibility(View.INVISIBLE);

        if (userIsManager == 0)
        {
            fireButton.setVisibility(View.INVISIBLE);
            lookupFirstName.setVisibility(View.INVISIBLE);
            lookupLastName.setVisibility(View.INVISIBLE);
            lookupButton.setVisibility(View.INVISIBLE);
        }

        if (employee.getEmployeeInfo().getInactive() == 1)
        {
            fireButton.setVisibility(View.INVISIBLE);
            terminatedMessage.setVisibility(View.VISIBLE);
        }

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, LoginActivity.class);
                Log.d("TEST", "Logout Activity" + employeeID);
                startActivity(intent);
            }
        });

        fireButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ViewEmployeeInfo.class);
                employee.getEmployeeInfo().setInactive(1);
                fetch.updateEmployee(employee);
                WriteData writer = new WriteData();
                writer.updateDatabase(context, fetch.getEmployeeArray(), fetch.getNumberOfEmployee());
                Bundle extras = new Bundle();
                extras.putString("employeeID", Integer.toString(employeeID));
                extras.putString("userID", Integer.toString(userID));
                extras.putString("userIsManager", Integer.toString(userIsManager));
                intent.putExtras(extras);
                Log.d("TEST", "Terminated Activity" + employeeID);
                startActivity(intent);
            }
        });

        lookupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText lookupFirstName = (EditText) findViewById(R.id.lookupFirstName);
                EditText lookupLastName = (EditText) findViewById(R.id.lookupLastName);
                int lookupID = fetch.lookUpEmployeeID(lookupFirstName.getText().toString(), lookupLastName.getText().toString());
                if (lookupID > 0) {
                    Intent intent;
                    if (userID == lookupID) {
                        intent = new Intent(context, EditInfo.class);
                    }
                    else {
                        intent = new Intent(context, ViewEmployeeInfo.class);
                    }
                    Bundle extras = new Bundle();
                    extras.putString("employeeID", Integer.toString(lookupID));
                    extras.putString("userID", Integer.toString(userID));
                    extras.putString("userIsManager", Integer.toString(userIsManager));
                    intent.putExtras(extras);
                    Log.d("TEST", "Lookup Activity" + employeeID);
                    startActivity(intent);
                }
                else
                {
                    Intent intent = new Intent(context, ViewEmployeeInfo.class);
                    Bundle extras = new Bundle();
                    extras.putString("employeeID", Integer.toString(employeeID));
                    extras.putString("userID", Integer.toString(userID));
                    extras.putString("userIsManager", Integer.toString(userIsManager));
                    intent.putExtras(extras);
                    Log.d("TEST", "Lookup Activity Failed" + employeeID);
                    startActivity(intent);
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_employee_info, menu);
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
