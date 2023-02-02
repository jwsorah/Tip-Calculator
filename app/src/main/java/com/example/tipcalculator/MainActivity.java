package com.example.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText enterPrice;
    SeekBar tipBar;
    TextView seekBarLabel;
    RadioGroup radioGroup;
    RadioButton splitCostButton;
    RadioButton payEntireButton;
    EditText numPeople;
    TextView totalPrice;
    TextView totalTip;
    TextView totalSplit;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enterPrice = findViewById(R.id.enterPrice);
        tipBar = findViewById(R.id.tipBar);
        seekBarLabel = findViewById(R.id.seekBarLabel);
        radioGroup = findViewById(R.id.radioGroup);
        splitCostButton = findViewById(R.id.splitCostButton);
        payEntireButton = findViewById(R.id.payEntireButton);
        numPeople = findViewById(R.id.numPeople);
        totalPrice = findViewById(R.id.totalPrice);
        totalTip = findViewById(R.id.totalTip);
        totalSplit = findViewById(R.id.totalSplit);
        button = findViewById(R.id.button);


        tipBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                seekBarLabel.setText(i + "");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }




        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                double price = Double.parseDouble(enterPrice.getText().toString());
                double tip = Double.parseDouble(seekBarLabel.getText().toString());
                //double people = Double.parseDouble(numPeople.getText().toString());

                if(payEntireButton.isChecked()) {

                    tip = (price * (tip/100));
                    //price = price + (price * (tip/100));
                    price = price + tip;



                }else if (splitCostButton.isChecked()) {

                    double people = Double.parseDouble(numPeople.getText().toString());
                    price = price + (price * (tip/100));
                    totalSplit.setText(String.format("%.2f" , (price / people)));


                }

                totalPrice.setText(String.format("%.2f" , price));
                totalTip.setText(String.format("%.2f" , tip));
                //totalSplit.setText(String.format("%.2f" , (price / people)));

            }
        });



    }
}