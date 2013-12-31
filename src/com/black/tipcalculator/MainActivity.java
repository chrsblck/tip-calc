package com.black.tipcalculator;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	public static final double fifteenPercent = .15;
	public static final double eighteenPercent = .18;
	public static final double twentyPercent = .2;
	public double totalTip;
	public double totalAmount;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); 
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void calculateFifteenPercent(View view) {
    	this.caclulateTip(fifteenPercent);
    }
    
    public void calculateEighteenPercent(View view) {
    	this.caclulateTip(eighteenPercent);
    }
    
    public void calculateTwentyPercent(View view) {
    	this.caclulateTip(twentyPercent);
    }
    
    public void caclulateTip(double percentage) {
        EditText editAmount = (EditText)findViewById(R.id.billAmountText);
        TextView tipText = (TextView)findViewById(R.id.tipTotalView);
        TextView totalText = (TextView)findViewById(R.id.totalView);
        float number;
        
        if (!editTextIsEmpty(editAmount)) {
        	number = Float.valueOf(editAmount.getText().toString());
        } else {
        	number = 0;
        }
        
    	totalTip = number * percentage;
    	totalAmount = totalTip + number;
    	
    	String niceTotalTip = (String.format(getString(R.string.amount_format), totalTip));
    	String niceTotal    = (String.format(getString(R.string.amount_format), totalAmount));

    	tipText.setText(getString(R.string.tip_label) + niceTotalTip);
    	totalText.setText(getString(R.string.total_label) + niceTotal);
    }
    
    private boolean editTextIsEmpty(EditText edit) {
    	return edit.getText().toString().trim().length() == 0;
    }
}
