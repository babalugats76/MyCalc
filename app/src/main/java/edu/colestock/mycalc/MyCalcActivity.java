package edu.colestock.mycalc;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MyCalcActivity extends Activity {

    // Class "fields"
    private boolean startNumber = true;  // true: number (or decimal) is valid input
    private boolean acceptDecimal = true; // true: decimal is valid input; false: it is not
    private String currentTotal = ""; // empty string: operator has not yet been pressed

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_calc);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my_calc, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onValueButtonClick(View view) {
        // Cast view argument to a Button; that way we know what fired the event
        // Capture the button's text in a variable, i.e., btnText
        Button btn = (Button) view;
        String btnText = (String) btn.getText();

        // Use view to locate the calculator's display
        TextView calcResult = (TextView) findViewById(R.id.calcResult);

        // If decimal button pushed, return if not accepting decimal as input
        // Otherwise, set acceptDecimal flag to reflect that, after this, no other decimal input will be accepted
        // i.e., Every operand can have, at most, one decimal
        if(btnText.equals(".")) {
            if(!acceptDecimal) {
                return;
            }
            acceptDecimal = false;
        }

        // Store operand (current series of user inputs) in the calculator's display
        // If currently waiting for the first number, e.g., after an operation (+,-, etc.) startNumber will be true
        if(startNumber) {
            calcResult.setText(btnText); // Set value of display pressed button's label
            startNumber = false; // Set startNumber false, i.e., we are now accepting operations!
        } else {  // Otherwise, append to calculator's display
            calcResult.setText((String) calcResult.getText() + (String) btn.getText());
        }
    }

}
