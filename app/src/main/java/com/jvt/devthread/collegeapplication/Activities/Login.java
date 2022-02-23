package com.jvt.devthread.collegeapplication.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jvt.devthread.collegeapplication.R;
import com.jvt.devthread.collegeapplication.databinding.ActivityLoginBinding;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Login extends AppCompatActivity {
    private ActivityLoginBinding binding;
    FirebaseAuth firebaseAuth;
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    Handler handler = new Handler(Looper.getMainLooper());
    DatabaseReference databaseReference;
    String userId, password, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        binding.login.setOnClickListener(this::loginUser);
        setContentView(view);
    }

    private void loginUser(View view) {
        userId = binding.userid.getText().toString();
        password = binding.password.getText().toString();
        if (userId.isEmpty()){
            binding.userid.requestFocus();
            binding.userid.setError("Registration Number!");
        }else if (password.isEmpty()){
            binding.password.requestFocus();
            binding.password.setError("Password!");
        }else {
            databaseReference.child("StudentsRegistered").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.hasChild(userId)){
                        email = snapshot.child(userId).getValue().toString();
                        authenticateUser(email,password);
                    }else {
                        Toast.makeText(Login.this, "User does not exists. Please check", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    private void authenticateUser(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
            if (!task.isSuccessful()){
                Toast.makeText(this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
            }else {
                if (firebaseAuth.getCurrentUser() != null){
                    startActivity(new Intent(this,MainActivity.class));
                    Toast.makeText(this, "Logged in successfully.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null){
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}