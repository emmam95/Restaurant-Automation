package meghankh.loginwindow;

import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import Model.FetchData;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends Activity implements LoaderCallbacks<Cursor> {
    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    FetchData fetch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Bundles are used to transfer data between activities
        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
            //Displays error message that was set in a previous activity
            String errorMessageText = extras.getString("Error");
            TextView errorMessage = (TextView) findViewById(R.id.errorMessage);
            errorMessage.setText(errorMessageText);
        }

        Button registerButton = (Button) findViewById(R.id.register_but);
        Button email_sign_in_button = (Button) findViewById(R.id.email_sign_in_button);
        final Context context = this;
        //parse the text file containing employee information
        fetch = new FetchData();
        fetch.parseData(context);
        Log.d("TEST", "Parsed Data");

        //when register button is clicked change the activity to register activity
        registerButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent = new Intent(context, Register.class);
            startActivity(intent);
            }
        });
        //when sign in button is clicked
        email_sign_in_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
            Log.d("TEST", "Clicked Sign In");
            //get info off textviews
            EditText firstName = (EditText) findViewById(R.id.firstName);
            EditText lastName = (EditText) findViewById(R.id.lastName);
            EditText password = (EditText) findViewById(R.id.password);
            Log.d("TEST", "Lookup " + firstName.getText().toString() + " " + lastName.getText().toString());
            //grab the emloyee ID based on first and lastname
            int employeeID = fetch.lookUpEmployeeID(firstName.getText().toString(), lastName.getText().toString());
            Log.d("TEST", "Found ID " + employeeID);
            //if this employee exists
            if (employeeID > 0) {
                //if password is correct and the employee has not been terminated. getInactive() is a method that returns a status of termination
                if (fetch.getEmployee(employeeID).getEmployeeInfo().getPassword().equals(password.getText().toString())
                    && fetch.getEmployee(employeeID).getEmployeeInfo().getInactive() == 0) {
                    //go to edit profile
                    Intent intent = new Intent(context, EditInfo.class);
                    //bundle some info to go to new activity
                    Bundle extras = new Bundle();
                    extras.putString("employeeID", Integer.toString(employeeID));
                    extras.putString("userID", Integer.toString(employeeID));
                    extras.putString("userIsManager", Integer.toString(fetch.getEmployee(employeeID).getEmployeeInfo().getManager()));
                    intent.putExtras(extras);
                    Log.d("TEST", "Person is a manager: " + fetch.getEmployee(employeeID).getEmployeeInfo().getManager());
                    startActivity(intent);
                }
                else
                {
                    Intent intent = new Intent(context, LoginActivity.class);
                    intent.putExtra("Error", "Invalid Login.");
                    startActivity(intent);
                }
            }
            else
            {
                Intent intent = new Intent(context, LoginActivity.class);
                intent.putExtra("Error", "Invalid Login.");
                startActivity(intent);
            }
            }
        });

        Button tipButton = (Button) findViewById(R.id.tipButton);
        //go to tip activity
        tipButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, TipCalculatorActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<String>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }

        addEmailsToAutoComplete(emails);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {
        // Required but no implemented.
    }

    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }

    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(LoginActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        mEmailView.setAdapter(adapter);
    }

}



