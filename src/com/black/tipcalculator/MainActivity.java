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
	public double lastCalculated;
	public double totalTip;
	public double totalAmount;
	public double totalPerPerson;
	
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
    	lastCalculated = fifteenPercent;
    	calculateTip(fifteenPercent);
    }
    
    public void calculateEighteenPercent(View view) {
    	lastCalculated = eighteenPercent;
    	calculateTip(eighteenPercent);
    }
    
    public void calculateTwentyPercent(View view) {
    	lastCalculated = twentyPercent;
    	calculateTip(twentyPercent);
    }
    
    public int getPeopleInParty() {
    	int number;
        TextView people = (TextView)findViewById(R.id.personsPerParty);

        String tmpNum = people.getText().toString();
        number = Integer.parseInt(tmpNum);
        
        if(number < 1) {
        	number = 1;
        }
        
        return number;
    }
    
    public void decrementPeopleInParty(View view) {
    	int personsToDisplay;
    	TextView people = (TextView)findViewById(R.id.personsPerParty);
       	
    	try {
       		personsToDisplay = getPeopleInParty() - 1;
       		if (personsToDisplay < 1) {
       			personsToDisplay = 1;
       		}
        	people.setText(Integer.toString(personsToDisplay));
        	
        	if (lastCalculated > 0) {
        		calculateTip(lastCalculated);
        	}
       	} catch (Exception e) {
       		System.out.println(e.toString());
       	}
    }
    
    public void addPeopleInParty(View view) {
    	int personsToDisplay;
       	TextView people = (TextView)findViewById(R.id.personsPerParty);
       	
       	try {
       		personsToDisplay = getPeopleInParty() + 1;
        	people.setText(Integer.toString(personsToDisplay));
        	
        	if (lastCalculated > 0) {
        		calculateTip(lastCalculated);
        	}
       	} catch (Exception e) {
       		System.out.println(e.toString());
       	}
    }
    
    public void calculateTip(double percentage) {
        EditText editAmount = (EditText)findViewById(R.id.billAmountText);
        TextView tipText = (TextView)findViewById(R.id.tipTotalView);
        TextView totalText = (TextView)findViewById(R.id.totalView);
        TextView perPerson = (TextView)findViewById(R.id.totalPerPersonView);
        
        float number;
        
        if (!editTextIsEmpty(editAmount)) {
        	number = Float.valueOf(editAmount.getText().toString());
        } else {
        	number = 0;
        }
        
    	totalTip = number * percentage;
    	totalAmount = totalTip + number;
    	totalPerPerson = totalAmount / getPeopleInParty();
    	
    	String niceTotalTip = (String.format(getString(R.string.amount_format), totalTip));
    	String niceTotal    = (String.format(getString(R.string.amount_format), totalAmount));
    	String niceTotalPerPerson = (String.format(getString(R.string.amount_format), totalPerPerson));

    	
    	tipText.setText(getString(R.string.tip_label) + niceTotalTip);
    	totalText.setText(getString(R.string.total_label) + niceTotal);
    	perPerson.setText(getString(R.string.per_person_total) + niceTotalPerPerson);
    }
    
    private boolean editTextIsEmpty(EditText edit) {
    	return edit.getText().toString().trim().length() == 0;
    }
}
