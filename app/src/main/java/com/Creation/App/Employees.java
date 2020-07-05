package com.Creation.App;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Employees extends AppCompatActivity {
    FirebaseAuth fAuth;

    FirebaseFirestore fStore;
    String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employees);
        fAuth = FirebaseAuth.getInstance();

        fStore = FirebaseFirestore.getInstance();
        Button Monthly=findViewById(R.id.btn_monthly);
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


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LayoutInflater factory = LayoutInflater.from(Employees.this);

//text_entry is an Layout XML file containing two text field to display in alert dialog
                final View textEntryView = factory.inflate(R.layout.text_entry, null);

                final EditText input1 = (EditText) textEntryView.findViewById(R.id.EditText1);
                final EditText input2 = (EditText) textEntryView.findViewById(R.id.EditText2);


                input1.setText(" ", TextView.BufferType.EDITABLE);
                input2.setText(" ", TextView.BufferType.EDITABLE);

                final AlertDialog.Builder alert = new AlertDialog.Builder(Employees.this);
                alert.setView(textEntryView).setPositiveButton("Save",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                final String name=input1.getText().toString();

                                String rf_id=input2.getText().toString();

                                userId = Objects.requireNonNull(fAuth.getCurrentUser()).getUid();
                                DocumentReference documentReference = fStore.collection("Users Detail").document(userId).collection("Employee List").document(rf_id);
                                Map<String, Object> user = new HashMap<>();
                                user.put("Full Name", name);

                                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Log.d("TAG", "onSuccess: user Profile is Created for" + userId);
                                        Toast.makeText(getApplicationContext(), "Id Created for"+name, Toast.LENGTH_SHORT).show();
                                    }
                                });

                                /* User clicked OK so do some stuff */

                            }
                        }).setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                /*
                                 * User clicked cancel so do some stuff
                                 */
                            }
                        });
                alert.show();
            }
        });

    }
}