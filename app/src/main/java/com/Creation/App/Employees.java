package com.Creation.App;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;

public class Employees extends AppCompatActivity {

    private RecyclerView mfirestore_list;
    private FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    String UserId;
    private FirestoreRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employees);
        Button Monthly=findViewById(R.id.btn_monthly);
        Button Annual=findViewById(R.id.btn_annual);
        Button Employee=findViewById(R.id.btn_employees);
        Button Profile=findViewById(R.id.btn_profile);

        mfirestore_list = findViewById(R.id.recyclerView);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        UserId = firebaseAuth.getCurrentUser().getUid();


        //Query
        Query query = firebaseFirestore.collectionGroup("EmployeeList").whereEqualTo("User Details","name");

        //Recycler Option
        FirestoreRecyclerOptions<EmployeeModel> options = new FirestoreRecyclerOptions.Builder<EmployeeModel>()
                .setQuery(query, EmployeeModel.class)
                .build();


         adapter = new FirestoreRecyclerAdapter<EmployeeModel, EmployeeViewHolder>(options) {
            @NonNull
            @Override
            public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_employee_signal, parent, false);
                return new EmployeeViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position, @NonNull EmployeeModel model) {
                holder.list_name.setText(model.getName());

            }
        };

         mfirestore_list.setHasFixedSize(true);
         mfirestore_list.setLayoutManager(new LinearLayoutManager(this));
         mfirestore_list.setAdapter(adapter);














        //Buttons
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

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
}