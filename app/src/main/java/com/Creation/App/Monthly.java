package com.Creation.App;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

@SuppressWarnings("ALL")
public class Monthly extends AppCompatActivity {

    FirebaseAuth fAuth;
    String UAN;
    FirebaseFirestore fStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly);
        fAuth= FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();


        Spinner mySpinner = (Spinner) findViewById(R.id.spinner);

            ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(Monthly.this,
                    android.R.layout.simple_expandable_list_item_1, getResources().getStringArray(R.array.Desginations));
            myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            mySpinner.setAdapter(myAdapter);






        final Button Monthly=findViewById(R.id.btn_monthly);
        Button Annual=findViewById(R.id.btn_annual);
        Button Employee=findViewById(R.id.btn_employees);
        Button Profile=findViewById(R.id.btn_profile);
        Monthly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Monthly.class);
                startActivity(intent);
                finish();
            }
        });
        Annual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Annual.class);
                startActivity(intent);
                finish();
            }
        });
        Employee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Employees.class);
                startActivity(intent);
                finish();
            }
        });
        Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Profile.class);
                startActivity(intent);
                finish();
            }
        });

    }
}