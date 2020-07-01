package com.Creation.App;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CreateUser extends AppCompatActivity {
    EditText mfullName,mEmail,mPassword,mPhone,mcompanyName;
    Button mRegisterBtn;
    Button malreadRegisteredBtn;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    FirebaseFirestore fStore;
    String userId;
    String n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        mfullName = findViewById(R.id.fullName);
        mEmail = findViewById(R.id.textEmailId);
        mcompanyName = findViewById(R.id.companyName);
        mPassword = findViewById(R.id.editPassword);
        mPhone = findViewById(R.id.editPhone);
        mRegisterBtn = findViewById(R.id.RegisterBtn);


        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);
        fStore = FirebaseFirestore.getInstance();



        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                final String phone = mPhone.getText().toString();
                final String fullName = mfullName.getText().toString();
                final String companyName = mcompanyName.getText().toString();

                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email is Required");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    mPassword.setError("Password is Required");
                    return;
                }

                if(TextUtils.isEmpty(phone)){
                    mPhone.setError("Phone Number is Required");
                    return;
                }
                if(phone.length() >= 11){
                    mPhone.setError("Phone Number should be equal to 10 character");
                    return;
                }

                if(password.length() < 6){
                    mPassword.setError("Password must be more than 6 character");
                    return;
                }

                if(TextUtils.isEmpty(companyName)){
                    mcompanyName.setError("Company Name is mandatory");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                // register the user in Firebase

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            // send verification link

                            FirebaseUser fuser = fAuth.getCurrentUser();
                            fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(CreateUser.this, "Verification Link is Send to your Email", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d("TAG", "onFailure: Email not sent" + e.getMessage());
                                }
                            });




                            Toast.makeText(CreateUser.this, "User Created", Toast.LENGTH_SHORT).show();
                            userId = Objects.requireNonNull(fAuth.getCurrentUser()).getUid();
                            DocumentReference documentReference = fStore.collection("Users Detail").document(userId);
                            Map<String,Object> user = new HashMap<>();
                            user.put ("Full Name",fullName);
                            user.put ("Phone Number",phone);
                            user.put ("Email Id",email);
                            user.put ("Company Name",companyName);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d("TAG", "onSuccess: user Profile is Created for"+ userId);
                                }
                            });
                            startActivity(new Intent(getApplicationContext(),Login.class));


                        }else {
                            Toast.makeText(CreateUser.this, "Error !"+ Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });

            }
        });

        Button alreadyRegisteredBtn = findViewById(R.id.alreadyRegisteredBtn);
        alreadyRegisteredBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });
    }
}