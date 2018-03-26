package com.example.jaceriehl.keeperproductivity;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

public class NewListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_list);

        final Button checkBox = findViewById(R.id.newListCheck);
        final EditText enterBoardName = findViewById(R.id.enterBoardName);
        enterBoardName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!enterBoardName.getText().equals(""))
                    checkBox.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.checkBoxes));
                else
                    checkBox.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.ghostText));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


}
