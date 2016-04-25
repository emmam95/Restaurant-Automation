package meghankh.loginwindow;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Meghan on 4/25/2016.
 */
public class TipCalculatorActivityTest extends ActivityInstrumentationTestCase2<TipCalculatorActivity> {

    TipCalculatorActivity activity;

    public TipCalculatorActivityTest()
    {
        super(TipCalculatorActivity.class);
    }

    protected void setUp() throws Exception {
        super.setUp();
        activity = getActivity();
    }

    public void testFields()
    {
        EditText totalWTax = (EditText) activity.findViewById(R.id.totalWTax);
        EditText tipAmount = (EditText) activity.findViewById(R.id.tipAmount);
        TextView finalAmount = (TextView) activity.findViewById(R.id.finalAmount);
        assertNotNull(totalWTax);
        assertNotNull(tipAmount);
        assertNotNull(finalAmount);
    }

    public void testPrefill()
    {
        EditText totalWTax = (EditText) activity.findViewById(R.id.totalWTax);
        EditText tipAmount = (EditText) activity.findViewById(R.id.tipAmount);
        TextView finalAmount = (TextView) activity.findViewById(R.id.finalAmount);
        assertEquals("", tipAmount.getText().toString());
    }
}
