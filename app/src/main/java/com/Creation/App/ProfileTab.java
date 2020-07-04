package com.Creation.App;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

@SuppressWarnings("ConstantConditions")
public class ProfileTab extends AppCompatActivity {
    TextView fullName,phone,email,companyName;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String UserId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_tab);
        fullName = findViewById(R.id.profileName);
        phone = findViewById(R.id.profilePhone);
        email =findViewById(R.id.profileEmail);
        companyName = findViewById(R.id.profileCompanyName);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        UserId = fAuth.getCurrentUser().getUid();
        FirebaseUser user = fAuth.getCurrentUser();


        DocumentReference documentReference = fStore.collection("Users Detail").document(UserId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
           fullName.setText(documentSnapshot.getString("Full Name"));
           phone.setText(documentSnapshot.getString("Phone Number"));
           email.setText(documentSnapshot.getString("Email Id"));
           companyName.setText(documentSnapshot.getString("Company Name"));

            }
        });






    }


}