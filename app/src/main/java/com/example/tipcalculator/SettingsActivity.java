package com.example.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SettingsActivity extends AppCompatActivity {

    EditText editTip;
    EditText editPeople;
    RadioGroup radioGroup;
    RadioButton radioButtonYes;
    RadioButton radioButtonNo;
    Button buttonReturn;

    private boolean default_decision;
    private int default_percentage;
    private int default_people;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        editTip = findViewById(R.id.editTip);
        editPeople = findViewById(R.id.editPeople);
        radioGroup = findViewById(R.id.radioGroup);
        radioButtonYes = findViewById(R.id.radioButtonYes);
        radioButtonNo = findViewById(R.id.radioButtonNo);



        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if( i == R.id.radioButtonYes){

                    default_decision = true;

                }

                else{

                    default_decision = false;

                }

            }
        });

    }

    @Override
    public void onPause(){

        super.onPause();
        updateSharedPreferences();

    }

    private void updateSharedPreferences() {

        default_percentage = Integer.parseInt(editTip.getText().toString());
        default_people = Integer.parseInt(editPeople.getText().toString());

        SharedPreferences sp = getSharedPreferences("shared", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("default decision", default_decision);
        editor.putInt("default percentage", default_percentage);
        editor.putInt("default people", default_people);
        editor.commit();

    }

    private void updateValues() {

        SharedPreferences sp = getSharedPreferences("shared", MODE_PRIVATE);
        default_percentage = sp.getInt("default percentage", 0);
        default_people = sp.getInt("default people", 0);
        default_decision = sp.getBoolean("default decision", false);

        editTip.setText(default_percentage + "");
        editPeople.setText(default_people + "");

        if(default_decision == true) {

            radioButtonYes.setChecked(true);

        }else {
            radioButtonNo.setChecked(true);
        }



    }

    public void onResume(){

        super.onResume();
        updateValues();


    }
}