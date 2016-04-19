package meghankh.restaurantautomation;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class RestaurantActivity extends ActionBarActivity {
    private EditText orderAmount;
    private TextView textWithTip;
    private Button calculateButton;
    private TextView totalWithoutTip;
    private double tip;
    private double total;
    public void tipCalculator(View view)
    {
        orderAmount = (EditText)findViewById(R.id.orderAmount);
        totalWithoutTip = (TextView)findViewById(R.id.totalWithoutTip);
        calculateButton = (Button)findViewById(R.id.calculateButton);
        //calculate total with tip
        total = Double.parseDouble(orderAmount.getText().toString()) + 10;
        //set totalWithTip label
        textWithTip.setText(String.valueOf(total));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_restaurant, menu);
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
