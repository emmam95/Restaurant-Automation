package meghankh.loginwindow;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.util.Log;

import java.io.File;

import Model.Employee;
import Model.EmployeeInfo;
import Model.FetchData;
import Model.WriteData;
import Model.Utils;


public class EditInfo extends ActionBarActivity {

    int employeeID;
    Employee employee;
    FetchData fetch;
    int userIsManager;
    int userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info);

        Log.d("TEST", "Edit Info Activity");
        final Context context = this;
        Bundle extras = getIntent().getExtras();
        employeeID = 0;
        userIsManager = 0;
        if (extras != null)
        {
            employeeID = new Integer(extras.getString("employeeID")).intValue();
            Log.d("TEST", "employeeID" + employeeID);
            userIsManager = new Integer(extras.getString("userIsManager")).intValue();
            userID = new Integer(extras.getString("userID")).intValue();
            Log.d("TEST", extras.getString("userIsManager"));
        }
        File path = context.getFilesDir();
        File file = new File(path, "employee.txt");
        //fetch the data from the textfile
        fetch = new FetchData();
        fetch.parseData(context);
        Log.d("TEST", "employeeID" + employeeID);
        employee = fetch.getEmployee(employeeID);
        EmployeeInfo info = employee.getEmployeeInfo();
        Log.d("TEST", "Employee: " + employeeID + " is " + info.getFirstName());
        Utils utils = new Utils();
        //Below is just setting the text inside the textviews to what their current values are
        EditText firstName = (EditText) findViewById(R.id.firstName);
        int byteLength = info.getFirstName().length();
        byte[] bytes = info.getFirstName().getBytes();
        char[] chars = utils.converToCharArray(bytes, byteLength);
        firstName.setText(chars, 0, byteLength);

        EditText ssn = (EditText) findViewById(R.id.ssn);
        byteLength = info.getSSN().toString().length();
        bytes = info.getSSN().toString().getBytes();
        chars = utils.converToCharArray(bytes, byteLength);
        ssn.setText(chars, 0, byteLength);

        EditText phone = (EditText) findViewById(R.id.phoneNumber);
        byteLength = info.getPhone().length();
        bytes = info.getPhone().getBytes();
        chars = utils.converToCharArray(bytes, byteLength);
        phone.setText(chars, 0, byteLength);

        EditText address = (EditText) findViewById(R.id.address);
        byteLength = info.getAddress().length();
        bytes = info.getAddress().getBytes();
        chars = utils.converToCharArray(bytes, byteLength);
        address.setText(chars, 0, byteLength);

        EditText lastName = (EditText) findViewById(R.id.lastName);
        byteLength = info.getLastName().length();
        bytes = info.getLastName().getBytes();
        chars = utils.converToCharArray(bytes, byteLength);
        lastName.setText(chars, 0, byteLength);

        EditText password = (EditText) findViewById(R.id.password);
        byteLength = info.getPassword().length();
        bytes = info.getPassword().getBytes();
        chars = utils.converToCharArray(bytes, byteLength);
        password.setText(chars, 0, byteLength);

        Button logoutButton = (Button) findViewById(R.id.logoutButton);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, LoginActivity.class);
                Log.d("TEST", "Logout Activity" + employeeID);
                startActivity(intent);
            }
        });

        Button updateButton = (Button) findViewById(R.id.updateButton);

        //updating objects and database with new info
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Employee currentEmployee = fetch.getEmployee(employeeID);
                EditText firstName = (EditText) findViewById(R.id.firstName);
                currentEmployee.getEmployeeInfo().setFirstName(firstName.getText().toString());
                EditText lastName = (EditText) findViewById(R.id.lastName);
                currentEmployee.getEmployeeInfo().setLastName(lastName.getText().toString());
                EditText ssn = (EditText) findViewById(R.id.ssn);
                currentEmployee.getEmployeeInfo().setSSN(ssn.getText().toString());
                EditText phone = (EditText) findViewById(R.id.phoneNumber);
                currentEmployee.getEmployeeInfo().setPhone(phone.getText().toString());
                EditText address = (EditText) findViewById(R.id.address);
                currentEmployee.getEmployeeInfo().setAddress(address.getText().toString());
                EditText password = (EditText) findViewById(R.id.password);
                currentEmployee.getEmployeeInfo().setPassword(password.getText().toString());
                fetch.updateEmployee(currentEmployee);
                WriteData writer = new WriteData();
                writer.updateDatabase(context, fetch.getEmployeeArray(), fetch.getNumberOfEmployee());
                Intent intent = new Intent(context, EditInfo.class);
                Bundle extras = new Bundle();
                extras.putString("employeeID", Integer.toString(employeeID));
                extras.putString("userID", Integer.toString(userID));
                extras.putString("userIsManager", Integer.toString(userIsManager));
                intent.putExtras(extras);
                Log.d("TEST", "update Activity" + employeeID);
                startActivity(intent);
            }
        });

        EditText lookupFirstName = (EditText) findViewById(R.id.lookupFirstName);
        EditText lookupLastName = (EditText) findViewById(R.id.lookupLastName);
        Button lookupButton = (Button) findViewById(R.id.lookupButton);
        //if you arent a manager you cant look up employees
        if (userIsManager == 0)
        {
            lookupFirstName.setVisibility(View.INVISIBLE);
            lookupLastName.setVisibility(View.INVISIBLE);
            lookupButton.setVisibility(View.INVISIBLE);
        }

        lookupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText lookupFirstName = (EditText) findViewById(R.id.lookupFirstName);
                EditText lookupLastName = (EditText) findViewById(R.id.lookupLastName);
                int lookupID = fetch.lookUpEmployeeID(lookupFirstName.getText().toString(), lookupLastName.getText().toString());
                //if employee exists
                if (lookupID > 0) {
                    Intent intent;
                    //if looking up yourself, make it edittable
                    if (userID == lookupID) {
                        intent = new Intent(context, EditInfo.class);
                    }
                    else { //else make it view only
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
                    Intent intent = new Intent(context, EditInfo.class);
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
        getMenuInflater().inflate(R.menu.menu_edit_info, menu);
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
