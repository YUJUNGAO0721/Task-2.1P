package com.yujun.unitconverter;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private Spinner units_spinner;
    private ImageView length_btn;
    private ImageView temp_btn;
    private ImageView weight_btn;
    private EditText input_edit_text;
    private TextView first_value_text, first_value_desc_text, second_value_text,
            second_value_desc_text, third_value_text, third_value_desc_text;

    private static final DecimalFormat decimalFormat = new DecimalFormat("0.00");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //fetch views from layout
        units_spinner = findViewById(R.id.units_spinner);
        length_btn = findViewById(R.id.length_btn);
        temp_btn = findViewById(R.id.temp_btn);
        weight_btn = findViewById(R.id.weight_btn);
        input_edit_text = findViewById(R.id.input_edit_text);

        first_value_text = findViewById(R.id.first_value_text);
        first_value_desc_text = findViewById(R.id.first_value_description_text);
        second_value_text = findViewById(R.id.second_value_text);
        second_value_desc_text = findViewById(R.id.second_value_description_text);
        third_value_text = findViewById(R.id.third_value_text);
        third_value_desc_text = findViewById(R.id.third_value_description_text);

        ArrayAdapter<CharSequence> unitsAdapter = ArrayAdapter.createFromResource(
                this, R.array.units,
                android.R.layout.simple_spinner_dropdown_item);
        unitsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        units_spinner.setAdapter(unitsAdapter);

        // length onclick
        length_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selection = units_spinner.getSelectedItem().toString();
                if (selection.equals("Metre")) {
                    String input_value = input_edit_text.getText().toString();
                    if(!input_value.isEmpty()){
                        double value = Double.parseDouble(input_value);
                        double[] convertedValues = getConvertedLengthValues(value);

                        first_value_text.setText(decimalFormat.format(convertedValues[0]));
                        first_value_text.setVisibility(View.VISIBLE);
                        first_value_desc_text.setVisibility(View.VISIBLE);

                        second_value_text.setText(decimalFormat.format(convertedValues[1]));
                        second_value_text.setVisibility(View.VISIBLE);
                        second_value_desc_text.setVisibility(View.VISIBLE);

                        third_value_text.setText(decimalFormat.format(convertedValues[2]));
                        third_value_text.setVisibility(View.VISIBLE);
                        third_value_desc_text.setVisibility(View.VISIBLE);

                    }
                    else{
                        setAllValueFieldsInvisible();
                        input_edit_text.setError("Enter a value");
                    }

                } else {
                    setAllValueFieldsInvisible();
                    Toast.makeText(getApplicationContext(),"Please select the correct conversion icon",Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Temp onclick
        temp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selection = units_spinner.getSelectedItem().toString();
                if (selection.equals("Celsius")) {
                    String input_value = input_edit_text.getText().toString();
                    if(!input_value.isEmpty()){
                        double value = Double.parseDouble(input_value);
                        double[] convertedValues = getConvertedTempValues(value);

                        first_value_text.setText(decimalFormat.format(convertedValues[0]));
                        first_value_text.setVisibility(View.VISIBLE);
                        first_value_desc_text.setText(R.string.fahrenheit);
                        first_value_desc_text.setVisibility(View.VISIBLE);

                        second_value_text.setText(decimalFormat.format(convertedValues[1]));
                        second_value_text.setVisibility(View.VISIBLE);
                        second_value_desc_text.setText(R.string.kelvin);
                        second_value_desc_text.setVisibility(View.VISIBLE);

                        // set third value and description as invisible
                        third_value_text.setVisibility(View.INVISIBLE);
                        third_value_desc_text.setVisibility(View.INVISIBLE);

                    }
                    else{
                        setAllValueFieldsInvisible();
                        input_edit_text.setError("Enter a value");
                    }
                } else {
                    setAllValueFieldsInvisible();
                    Toast.makeText(getApplicationContext(),"Please select the correct conversion icon",Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Weight onclick
        weight_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selection = units_spinner.getSelectedItem().toString();
                if (selection.equals("Kilograms")) {
                    String input_value = input_edit_text.getText().toString();
                    if(!input_value.isEmpty()){
                        double value = Double.parseDouble(input_value);
                        double[] convertedValues = getConvertedWeightValues(value);

                        first_value_text.setText(decimalFormat.format(convertedValues[0]));
                        first_value_text.setVisibility(View.VISIBLE);
                        first_value_desc_text.setText(R.string.gram);
                        first_value_desc_text.setVisibility(View.VISIBLE);

                        second_value_text.setText(decimalFormat.format(convertedValues[1]));
                        second_value_text.setVisibility(View.VISIBLE);
                        second_value_desc_text.setText(R.string.ounce);
                        second_value_desc_text.setVisibility(View.VISIBLE);

                        third_value_text.setText(decimalFormat.format(convertedValues[2]));
                        third_value_text.setVisibility(View.VISIBLE);
                        third_value_desc_text.setText(R.string.pound);
                        third_value_desc_text.setVisibility(View.VISIBLE);

                    }
                    else{
                        setAllValueFieldsInvisible();
                        input_edit_text.setError("Enter a value");
                    }
                } else {
                    setAllValueFieldsInvisible();
                    Toast.makeText(getApplicationContext(),"Please select the correct conversion icon",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    //Length Conversions
    private double [] getConvertedLengthValues(double value){
        double[] centimetre_foot_inch = new double[3];

        centimetre_foot_inch[0] = value * 100;
        centimetre_foot_inch[1] = value * 3.28084;
        centimetre_foot_inch[2] = value * 39.3701;

        return centimetre_foot_inch;
    }
    //Temperature Conversions
    private double [] getConvertedTempValues(double value){
        double[] fahrenheit_kelvin = new double[2];

        fahrenheit_kelvin[0] = (value * 1.8)+32.0;
        fahrenheit_kelvin[1] = value + 273.15;


        return fahrenheit_kelvin;
    }

    private double[] getConvertedWeightValues(double value){
        double [] gram_ounce_pound = new double[3];

        gram_ounce_pound[0] = value * 1000;
        gram_ounce_pound[1] = value * 35.274;
        gram_ounce_pound[2] = value * 2.20462;

        return gram_ounce_pound;
    }

    private void setAllValueFieldsInvisible(){
        first_value_text.setVisibility(View.INVISIBLE);
        first_value_desc_text.setVisibility(View.INVISIBLE);
        second_value_text.setVisibility(View.INVISIBLE);
        second_value_desc_text.setVisibility(View.INVISIBLE);
        third_value_text.setVisibility(View.INVISIBLE);
        third_value_desc_text.setVisibility(View.INVISIBLE);

    }
}