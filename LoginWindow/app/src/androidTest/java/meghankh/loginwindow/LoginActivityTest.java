package meghankh.loginwindow;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Meghan on 4/25/2016.
 */
public class LoginActivityTest extends ActivityInstrumentationTestCase2<LoginActivity> {

    LoginActivity activity;

    public LoginActivityTest()
    {
        super(LoginActivity.class);
    }

    protected void setUp() throws Exception {
        super.setUp();
        activity = getActivity();
    }

    public void testFields() {
        EditText firstName = (EditText) activity.findViewById(R.id.firstName);
        EditText lastName = (EditText) activity.findViewById(R.id.lastName);
        EditText password = (EditText) activity.findViewById(R.id.password);
        Button registerButton = (Button) activity.findViewById(R.id.register_but);
        Button email_sign_in_button = (Button) activity.findViewById(R.id.email_sign_in_button);
        Button tipButton = (Button) activity.findViewById(R.id.tipButton);
        TextView errorMessage = (TextView) activity.findViewById(R.id.errorMessage);
        assertNotNull(lastName);
        assertNotNull(password);
        assertNotNull(registerButton);
        assertNotNull(email_sign_in_button);
        assertNotNull(tipButton);
        assertNotNull(firstName);
        assertNotNull(errorMessage);
    }

    public void testBadLogin()
    {
        EditText firstName = (EditText) activity.findViewById(R.id.firstName);
        EditText lastName = (EditText) activity.findViewById(R.id.lastName);
        EditText password = (EditText) activity.findViewById(R.id.password);
        Button email_sign_in_button = (Button) activity.findViewById(R.id.email_sign_in_button);
        TextView errorMessage = (TextView) activity.findViewById(R.id.errorMessage);
        assertEquals("", errorMessage.getText().toString());
        assertNotNull(email_sign_in_button);
    }
}
