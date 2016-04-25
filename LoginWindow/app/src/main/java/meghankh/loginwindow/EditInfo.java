package meghankh.loginwindow;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import Model.Employee;
import Model.EmployeeInfo;
import Model.FetchData;
import Model.WriteData;
import Model.Utils;


public class EditInfo extends ActionBarActivity {

    int employeeID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info);
        Button changeAccountButton = (Button) findViewById(R.id.change_Account_But);
        Log.d("TEST", "Edit Info Activity");
        final Context context = this;
        Bundle extras = getIntent().getExtras();
        employeeID = 0;
        if (extras != null)
        {
            employeeID = new Integer(extras.getString("employeeID")).intValue();
            Log.d("TEST", "employeeID" + employeeID);
        }
        File path = context.getFilesDir();
        File file = new File(path, "employee.txt");
        FetchData fetch = new FetchData();
        fetch.parseData(context);
        Log.d("TEST", "employeeID" + employeeID);
        Employee employee = fetch.getEmployee(employeeID);
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

        changeAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView ssn = (TextView) findViewById(R.id.ssn);
                TextView phone = (TextView) findViewById(R.id.phoneNumber);
                TextView address = (TextView) findViewById(R.id.address);
                TextView firstName = (TextView) findViewById(R.id.firstName);
                TextView lastName = (TextView) findViewById(R.id.lastName);
                TextView password = (TextView) findViewById(R.id.password);
                // Employee employee = new Employee(employeeID, 0, ssn.toString(), phone.toString(), address.toString(), firstName.toString(), lastName.toString(), 7.25, 40, password.toString());
                WriteData writeData = new WriteData();
                // writeData.addEmployee(context, employee);
                Intent intent = new Intent(context, EditInfo.class);
                intent.putExtra("employeeID", Integer.toString(employeeID));
                Log.d("TEST", "Next Activity" + employeeID);
                startActivity(intent);
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
