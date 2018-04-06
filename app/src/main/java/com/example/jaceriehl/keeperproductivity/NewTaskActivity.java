package com.example.jaceriehl.keeperproductivity;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.w3c.dom.Text;

public class NewTaskActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        final Button checkBox = findViewById(R.id.newTaskCheck);
        final EditText enterTaskName = findViewById(R.id.enterTaskName);
        final Button cancelBox = findViewById(R.id.closeNewTask);
        final Intent returnIntent = new Intent();
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
