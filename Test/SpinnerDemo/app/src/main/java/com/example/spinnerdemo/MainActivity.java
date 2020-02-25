package com.example.spinnerdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Spinner mSpinner;
    private CheckedTextView mChecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSpinner = findViewById(R.id.spinner);

//        List<String> list = new ArrayList<>();
//        list.add("春");
//        list.add("夏");
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_spinner_dropdown_item,
//                list);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this,
                R.array.plants_array,
                R.layout.spinner_textview);

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        mSpinner.setAdapter(arrayAdapter);

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String itemString = mSpinner.getSelectedItem().toString();
                Toast.makeText(MainActivity.this, itemString, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(MainActivity.this, "onNothingSelected", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
