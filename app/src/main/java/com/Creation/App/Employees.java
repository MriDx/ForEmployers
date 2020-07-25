package com.Creation.App;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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
    String userId, UAN_id;
    TextView employeeRetrive;
    List<String> UAN_i, name, Deg, phone;
    String UserId, UAN;
    String[][] data = new String[4][100];
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

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
                final View textEntryView = factory.inflate(R.layout.text_entry, null);


                final EditText inputname = (EditText) textEntryView.findViewById(R.id.Editemplname);
                final EditText inputUAN = (EditText) textEntryView.findViewById(R.id.EdittUAN);
                final EditText inputdeg = (EditText) textEntryView.findViewById(R.id.Editempldeg);
                final EditText inputphone = (EditText) textEntryView.findViewById(R.id.Editemplphone);
                final Spinner mySpinner = (Spinner) textEntryView.findViewById(R.id.spinner);


                ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(Employees.this,
                        android.R.layout.simple_expandable_list_item_1, getResources().getStringArray(R.array.Desginations));
                myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mySpinner.setAdapter(myAdapter);

                final AlertDialog.Builder alert = new AlertDialog.Builder(Employees.this);
                alert.setView(textEntryView).setPositiveButton("Save",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                String name = inputname.getText().toString().trim();
                                String UAN_id = inputUAN.getText().toString();
                                String Deg = inputdeg.getText().toString();
                                String phone = inputphone.getText().toString();

                                if (TextUtils.isEmpty(name)) {
                                    inputname.setError("Mandatory");
                                    return;
                                }


                                userId = Objects.requireNonNull(fAuth.getCurrentUser()).getUid();
                                DocumentReference documentReference = fStore.collection("Users Detail").document(userId).collection("Employee List").document(UAN_id);
                                Map<String, Object> user = new HashMap<>();
                                user.put("Full Name", name);
                                user.put("UAN", UAN_id);
                                user.put("Desgination", Deg);
                                user.put("Phone", phone);

                                final String finalName = name;
                                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(getApplicationContext(), finalName + " is added.", Toast.LENGTH_SHORT).show();
                                        work();
                                    }
                                });


                                DocumentReference documentReference1 = fStore.collection("Employees List").document(UAN_id);
                                Map<String, Object> user1 = new HashMap<>();
                                user1.put("Full Name", name);
                                user1.put("UAN", UAN_id);
                                user1.put("Desgination", Deg);
                                user1.put("Phone", phone);
                                documentReference1.set(user1);
                                name = null;
                                UAN_id = null;

                            }
                        }).setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {

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
                    UAN_i = new ArrayList<>();
                    name = new ArrayList<>();
                    Deg = new ArrayList<>();
                    phone = new ArrayList<>();
                    for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                        name.add(document.getString("Full Name"));
                        UAN_i.add(document.getString("UAN"));
                        Deg.add(document.getString("Desgination"));
                        phone.add(document.getString("Phone"));

                    }

                    mAdapter = new MyRecyclerViewAdapter(getDataSet(), Employees.this);
                    mRecyclerView.setAdapter(mAdapter);
                    Log.d("TAG", UAN_i.toString());

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


        for (int index = 0; index < name.size(); index++) {
            DataObject obj1 = new DataObject(name.get(index), UAN_i.get(index));
            results.add(index, obj1);
            data[0][index] = name.get(index);
            data[1][index] = UAN_i.get(index);
            //Here the error is showing that this line is not valid.
            data[2][index] = Deg.get(index);
            data[3][index] = phone.get(index);
        }
        return results;
    }

    @Override
    public void onItemClick(final int position) {
        Log.d("ITEM CLICKED", "Clicked an item: " + position);
        //Toast.makeText(this, "Name="+ data[0][position]+","+" RF Id="+data[1][position], Toast.LENGTH_SHORT).show();

        final LayoutInflater factory = LayoutInflater.from(Employees.this);
        final View textEntryView = factory.inflate(R.layout.activity_employee_retrive, null);
        TextView textView = (TextView) textEntryView.findViewById(R.id.employee_retrive_name);
        textView.setText(data[0][position]);
        final TextView textView2 = (TextView) textEntryView.findViewById(R.id.employee_retrive_UAN);
        textView2.setText(data[1][position]);
        TextView textView3 = (TextView) textEntryView.findViewById(R.id.employee_retrive_Deg);
        textView3.setText(data[2][position]);
        TextView textView4 = (TextView) textEntryView.findViewById(R.id.employee_retrive_phone);
        textView4.setText(data[3][position]);

        final AlertDialog.Builder alert = new AlertDialog.Builder(Employees.this);
        alert.setView(textEntryView).setPositiveButton("Delete",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int whichButton) {

                        Task<Void> documentReference = fStore.collection("Users Detail/" + userId + "/Employee List").document(data[1][position]).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                                Toast.makeText(Employees.this, "Your Data is successfull Delete", Toast.LENGTH_SHORT).show();
                                work();
                            }
                        });

                    }
                }).setNegativeButton("Del",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int whichButton) {
                        Toast.makeText(Employees.this, "Profile Deteted", Toast.LENGTH_SHORT).show();

                    }
                });
        alert.show();


    }
}