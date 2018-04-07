package com.example.jaceriehl.keeperproductivity;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

public class NewTaskActivity extends AppCompatActivity {

    protected int htIndex;
    protected int repeatIndex;
    protected boolean customSelected = false;
    protected int customPosition = 4;
    protected TextView repeatTextView;
    protected TextView daysTextView;
    protected TextView timeTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        final Button checkBox = findViewById(R.id.newTaskCheck);
        final EditText enterTaskName = findViewById(R.id.enterTaskName);
        final Button cancelBox = findViewById(R.id.closeNewTask);
        repeatTextView = (TextView)findViewById(R.id.repeatTV);
        daysTextView = (TextView)findViewById(R.id.numDaysTV);
        timeTextView = (TextView)findViewById(R.id.timeTV);
        final Intent returnIntent = new Intent();
        String[] htSpin = new String[]{
                "Task","Habit"
        };
        String[] repeatSpin = new String[]{
                "Daily", "Weekly","Monthly", "Yearly", "Custom"
        };

        final Spinner habitTaskSpinner = (Spinner)findViewById(R.id.htSpinner);
        final Spinner repeatSpinner = (Spinner)findViewById(R.id.repeatSpinner);
        ArrayAdapter<String> htAdapter = new ArrayAdapter<String>(this,R.layout.spinner_layout,
                htSpin);
        htAdapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        habitTaskSpinner.setAdapter(htAdapter);

        ArrayAdapter<String> repeatAdapter = new ArrayAdapter<String>(this,R.layout.spinner_layout,
                repeatSpin);
        repeatAdapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        repeatSpinner.setAdapter(repeatAdapter);



        habitTaskSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                htIndex = adapterView.getSelectedItemPosition();
                if(htIndex == 1){
                    repeatTextView.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.lessSubtleText));
                    if(repeatIndex == customPosition) {
                        daysTextView.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.lessSubtleText));
                        timeTextView.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.lessSubtleText));
                    }
                    customSelected = true;
                }
                else{
                    repeatTextView.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.ghostText));
                    daysTextView.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.ghostText));
                    timeTextView.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.ghostText));
                    customSelected = false;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        repeatSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                repeatIndex = adapterView.getSelectedItemPosition();

                if(repeatIndex == customPosition){
                    daysTextView.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.lessSubtleText));
                    timeTextView.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.lessSubtleText));
                    customSelected = true;
                }
                else{
                    daysTextView.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.ghostText));
                    timeTextView.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.ghostText));
                    customSelected = false;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        enterTaskName.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(enterTaskName.getText().toString().trim().length() != 0)
                    checkBox.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.checkBoxes));
                else
                    checkBox.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.ghostText));
            }
        });

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(enterTaskName.getText().toString().trim().length() != 0)
                {
                    returnIntent.putExtra("taskName", enterTaskName.getText().toString().trim());
                    setResult(Activity.RESULT_OK,returnIntent);
                    finish();
                }
            }
        });

        cancelBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(Activity.RESULT_CANCELED, returnIntent);
                finish();
            }
        });
    }
}
