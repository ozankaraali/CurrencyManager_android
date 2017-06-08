package com.ozankaraali.Currency_Manager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText eT = (EditText) findViewById(R.id.moneyToConverted);
        final EditText eC = (EditText) findViewById(R.id.convertedMoney);

        final Spinner sT = (Spinner) findViewById(R.id.spinnerTo);

        ArrayList<String> currencies = null;
        try {
            currencies = MainConversion.currencies();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(currencies);
        ArrayAdapter<String> adapterT = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, currencies);
        sT.setAdapter(adapterT);


        ArrayList<String> currenciesC = currencies;
        //currenciesC.remove(sT.getSelectedItem().toString());

        final Spinner sC = (Spinner) findViewById(R.id.spinnerConverted);


        ArrayAdapter<String> adapterC = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, currenciesC);
        sC.setAdapter(adapterC);

        eT.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String T =sT.getSelectedItem().toString();
                String C =sC.getSelectedItem().toString();
                double amount;
                if(eT.getText().toString().matches("")){amount = 0;}
                else{amount = Double.parseDouble(eT.getText().toString());}
                double calc = 0;
                if(T.equals(C)){
                    eC.setText(String.valueOf(amount));}
                else {
                    try {
                        calc = (MainConversion.getRate(T, C) * amount);
                        eC.setText(String.valueOf(calc));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        /*sT.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String T =sT.getSelectedItem().toString();
                String C =sC.getSelectedItem().toString();
                double amount;
                if(eT.getText().toString().matches("")){amount = 0;}
                else{amount = Double.parseDouble(eT.getText().toString());}
                double calc = 0;
                try {
                    calc = (MainConversion.getRate(T,C)*amount);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                eC.setText(String.valueOf(calc));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sC.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String T =sT.getSelectedItem().toString();
                String C =sC.getSelectedItem().toString();
                double amount;
                if(eT.getText().toString().matches("")){amount = 0;}
                else{amount = Double.parseDouble(eT.getText().toString());}
                double calc = 0;
                try {
                    calc = (MainConversion.getRate(T,C)*amount);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                eC.setText(String.valueOf(calc));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/
    }

}
