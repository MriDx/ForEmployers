package com.Creation.App;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@SuppressWarnings("ALL")
public class Employees extends AppCompatActivity implements MyRecyclerViewAdapter.OnListItemClick {
    FirebaseAuth fAuth;

    FirebaseFirestore fStore;
    String userId;
    TextView employeeRetrive;
    List<String> rf_id, name;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    String UserId;
    String[] data = new String[100];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employees);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        employeeRetrive = findViewById(R.id.employee_retrive_name);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);


        fAuth = FirebaseAuth.getInstance();

        fStore = FirebaseFirestore.getInstance();
        Button Monthly = findViewById(R.id.btn_monthly);
        Button Annual = findViewById(R.id.btn_annual);
        Button Employee = findViewById(R.id.btn_employees);
        Button Profile = findViewById(R.id.btn_profile);

        userId = Objects.requireNonNull(fAuth.getCurrentUser()).getUid();

        work();


        Monthly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Monthly.class);
                startActivity(intent);
                finish();
            }
        });
        Annual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Annual.class);
                startActivity(intent);
                finish();
            }
        });
        Employee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Employees.class);
                startActivity(intent);
                finish();
            }
        });
        Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Profile.class);
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
                                final String name = input1.getText().toString();

                                String rf_id = input2.getText().toString();

                                userId = Objects.requireNonNull(fAuth.getCurrentUser()).getUid();
                                DocumentReference documentReference = fStore.collection("Users Detail").document(userId).collection("Employee List").document(rf_id);
                                Map<String, Object> user = new HashMap<>();
                                user.put("Full Name", name);

                                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Log.d("TAG", "onSuccess: user Profile is Created for" + userId);
                                        Toast.makeText(getApplicationContext(), "Id Created for" + name, Toast.LENGTH_SHORT).show();
                               work();
                                    }
                                });



                                DocumentReference documentReference1 = fStore.collection("Users Detail1").document(userId).collection("Employee List").document(rf_id);
                                Map<String, Object> user1 = new HashMap<>();
                                user1.put("Full Name", name);

                                documentReference1.set(user1).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Log.d("TAG", "onSuccess: user Profile is Created for" + userId);
                                        Toast.makeText(getApplicationContext(), "Id Created for" + name, Toast.LENGTH_SHORT).show();
                                        work();
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

    private void work() {

        FirebaseFirestore.getInstance().collection("Users Detail/" + userId + "/Employee List").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    rf_id = new ArrayList<>();
                    name = new ArrayList<>();
                    for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                        name.add(document.getString("Full Name"));
                        rf_id.add(document.getId());
                    }

                    mAdapter = new MyRecyclerViewAdapter(getDataSet(), Employees.this);
                    mRecyclerView.setAdapter(mAdapter);
                    Log.d("TAG", rf_id.toString());

                  //  Toast.makeText(Employees.this, rf_id.toString(), Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("TAG", "Error getting documents: ", task.getException());
                    Toast.makeText(Employees.this, "Error getting documents", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private ArrayList<DataObject> getDataSet() {
        ArrayList results = new ArrayList<DataObject>();

        DataObject obja = new DataObject("Name", "Rf id");
        results.add(0, obja);
        data[0] = "Title";
        for (int index = 0; index < rf_id.size(); index++) {
            DataObject obj = new DataObject(name.get(index), rf_id.get(index));
            results.add(index + 1, obj);
            data[index + 1] = name.get(index);
        }
        return results;
    }

    @Override
    public void onItemClick(int position) {
        Log.d("ITEM CLICKED", "Clicked an item: " + position);

        Toast.makeText(this, data[position], Toast.LENGTH_SHORT).show();


        /**   LayoutInflater factory = LayoutInflater.from(Employees.this);
         final View textEntryView = factory.inflate(R.layout.activity_employee_retrive, null);

         final AlertDialog.Builder alert = new AlertDialog.Builder(Employees.this);
         alert.setView(textEntryView).setPositiveButton("Save",
         new DialogInterface.OnClickListener() {
         public void onClick(DialogInterface dialog,
         int whichButton) {

         }
         }).setNegativeButton("Cancel",
         new DialogInterface.OnClickListener() {
         public void onClick(DialogInterface dialog,
         int whichButton) {
         }
         });
         alert.show();
         **/

    }
}