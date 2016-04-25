package meghankh.loginwindow;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TipCalculatorActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_calculator);

        final Context context = this;

        EditText totalWTax = (EditText) findViewById(R.id.totalWTax);

        totalWTax.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                EditText totalWTax = (EditText) findViewById(R.id.totalWTax);
                EditText tipAmount = (EditText) findViewById(R.id.tipAmount);
                String totalBeforeString = totalWTax.getText().toString();
                if (!totalBeforeString.equals("")) {
                    double totalBefore = new Double(totalWTax.getText().toString()).doubleValue();
                    if (totalBefore > 0.0) {
                        double tip = 0.15 * totalBefore;
                        tipAmount.setText(new Double(tip).toString());
                    }
                }
                else {
                    tipAmount.setText("0.00");
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                // Do nothing.
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                // Do nothing.
            }
        });

        Button calculateTipButton = (Button) findViewById(R.id.calculateTipButton);

        calculateTipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText totalWTax = (EditText) findViewById(R.id.totalWTax);
                EditText tipAmount = (EditText) findViewById(R.id.tipAmount);
                TextView finalAmount = (TextView) findViewById(R.id.finalAmount);
                double totalBefore = new Double(totalWTax.getText().toString()).doubleValue();
                double tip = new Double(tipAmount.getText().toString()).doubleValue();
                double total = totalBefore + tip;
                finalAmount.setText(new Double(total).toString());
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tip_calculator, menu);
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
