package com.Creation.App;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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

@SuppressWarnings("ALL")
public class CreateUser extends AppCompatActivity {
    EditText mEstbname, mEmail, mPassword, mPhone, mGstNo;
    Button mRegisterBtn;

    FirebaseAuth fAuth;
    ProgressBar progressBar;
    FirebaseFirestore fStore;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        mEstbname = findViewById(R.id.create_Company_Name);
        mEmail = findViewById(R.id.create_Email_Id);
        mGstNo = findViewById(R.id.create_GSTIN);
        mPassword = findViewById(R.id.create_Password);
        mPhone = findViewById(R.id.create_Phone_number);
        mRegisterBtn = findViewById(R.id.RegisterBtn);


        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);
        fStore = FirebaseFirestore.getInstance();


        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final String email = mEmail.getText().toString().trim();
                final String password = mPassword.getText().toString().trim();
                final String phone = mPhone.getText().toString();
                final String Estblishment = mEstbname.getText().toString();
                final String GSTIN = mGstNo.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    mEmail.setError("Email is Required");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    mPassword.setError("Password is Required");
                    return;
                }

                if (TextUtils.isEmpty(phone)) {
                    mPhone.setError("Phone Number is Required");
                    return;
                }
                if (phone.length()!=10) {
                    mPhone.setError("Phone Number is not valid");
                    return;
                }

                if (password.length() < 6) {
                    mPassword.setError("Password must be more than 6 character");
                    return;
                }

                if (TextUtils.isEmpty(Estblishment)) {
                    mEstbname.setError("Establishment Name is Required");
                    return;
                }

                if (TextUtils.isEmpty(GSTIN)){
                    mGstNo.setError("GSTIN Number is Required");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                // register the user in Firebase

                LayoutInflater factory = LayoutInflater.from(CreateUser.this);
                View textEntryView = factory.inflate(R.layout.create_user_confirm, null);

                final TextView view1 = (TextView) textEntryView.findViewById(R.id.confirm_Estblish);
                final TextView view2 = (TextView) textEntryView.findViewById(R.id.confirm_GST);
                final TextView view3 = (TextView) textEntryView.findViewById(R.id.confirm_Email);
                final TextView view4 = (TextView) textEntryView.findViewById(R.id.confirm_Phone);

                view1 .setText(mEstbname.getText());
                view2.setText(mGstNo.getText());
                view3.setText(mEmail.getText());
                view4.setText(mPhone.getText());

                AlertDialog.Builder alert = new AlertDialog.Builder(CreateUser.this);
                alert.setView(textEntryView).setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    //send Verification Link

                                    FirebaseUser userverify = fAuth.getCurrentUser();
                                    //noinspection ConstantConditions
                                    userverify.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Toast.makeText(CreateUser.this, "Email Verification Link is Send to your Email.", Toast.LENGTH_SHORT).show();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.d("TAG", "onFailure: Email Verification link is not sent"+ e.getMessage());
                                        }
                                    });


                                    Toast.makeText(CreateUser.this, "User Created", Toast.LENGTH_SHORT).show();
                                    userId = Objects.requireNonNull(fAuth.getCurrentUser()).getUid();
                                    DocumentReference documentReference = fStore.collection("Users Detail").document(userId);
                                    Map<String, Object> user = new HashMap<>();
                                    user.put("Establisment Name", Estblishment);
                                    user.put("Phone Number", phone);
                                    user.put("Email Id", email);
                                    user.put("GST No", GSTIN);
                                    documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Log.d("TAG", "onSuccess: user Profile is Created for" + userId);
                                        }
                                    });
                                    startActivity(new Intent(getApplicationContext(), Login.class));


                                } else {
                                    Toast.makeText(CreateUser.this, "Error !" + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                                    progressBar.setVisibility(View.GONE);
                                }
                            }
                        });

                    }
                }).setNegativeButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                alert.show();
                //just c

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