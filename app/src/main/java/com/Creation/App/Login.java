package com.Creation.App;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class Login extends AppCompatActivity {
    EditText mEmail,mPassword;
    Button mLoginBtn;
    Button mCreateUserBtn;
    Button mResetBtn;
    ProgressBar progressBar;
    TextView textResetPassword;
    FirebaseAuth fAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmail = findViewById(R.id.loginEmailId);
        mPassword = findViewById(R.id.loginPassword);
        mLoginBtn = findViewById(R.id.LoginBtn);
        mCreateUserBtn = findViewById(R.id.CreateUserBtn);
        textResetPassword = findViewById(R.id.Resetbtn);

        progressBar = findViewById(R.id.progressBar3);
        fAuth = FirebaseAuth.getInstance();


        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email is Required");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    mPassword.setError("Password is Required");
                    return;
                }


                if(password.length() < 6){
                    mPassword.setError("Password must be more than 6 character");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                // authenticate the user



                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                     if(task.isSuccessful()){
                         Toast.makeText(Login.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                         startActivity(new Intent(getApplicationContext(),MainActivity.class));
                     }else{
                         Toast.makeText(Login.this, "Error !"+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                         progressBar.setVisibility(View.GONE);
                     }

                    }
                });
            }
        });






        Button CreateUserBtn = findViewById(R.id.CreateUserBtn);
        CreateUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CreateUser.class);
                startActivity(intent);
                finish();
            }
        });


                textResetPassword.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        final EditText input = new EditText(getApplicationContext());
                        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(Login.this, R.style.myDialog));
                               builder.setTitle("Forget Password")
                                .setMessage("Enter Your Email to Reset the Password.")
                                .setView(input)
                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        Editable value = input.getText();

                                        FirebaseAuth auth = FirebaseAuth.getInstance();
                                        String emailaddress =String.valueOf(input.getText());
                                        auth.sendPasswordResetEmail(emailaddress)
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()) {
                                                            //    Log.d(TAG, "Email sent.");
                                                            Toast.makeText(getApplicationContext(), "Check Your Email", Toast.LENGTH_SHORT).show();
                                                        } else {
                                                            Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                });



                                    }
                                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                // Do nothing.
                            }
                        }).show();

                                // Do something with value!




            }
        });




    }
}