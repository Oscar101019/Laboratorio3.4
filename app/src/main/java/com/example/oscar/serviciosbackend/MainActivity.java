package com.example.oscar.serviciosbackend;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    Button btnSingnin,btnRegister;
    EditText editTextEmail,editTextPass;

    FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSingnin = (Button) findViewById(R.id.signin_button);
        btnRegister = (Button) findViewById(R.id.register_button);
        editTextEmail = findViewById(R.id.login_email);
        editTextPass = findViewById(R.id.login_password);

        btnSingnin.setOnClickListener((view)->{
            String emailini=editTextEmail.getText().toString();
            String passini=editTextPass.getText().toString();
            iniciarsesion(emailini,passini);


        });

        btnRegister.setOnClickListener((view)->{
            String emailini=editTextEmail.getText().toString();
            String passini=editTextPass.getText().toString();
            registrar(emailini,passini);
        });

        mAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                    Log.i("SESION","Sesion inicia con email: "+ user.getEmail());
                }else{
                    Log.i("SESION","Sesion cerrada");
                }
            }
        };
    }

    private void iniciarsesion(String email,String pass){
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Log.i("SESION","sesion iniciada");
                    Toast.makeText(MainActivity.this,"Sesión iniciada correctamente con "+ email,Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),BaseActivity.class);
                    intent.putExtra("Email",email);
                    startActivity(intent);
                    editTextEmail.setText("");
                    editTextPass.setText("");
                }else{
                    Log.i("SESION",task.getException().getMessage()+"");
                    Toast.makeText(MainActivity.this,"Error! Sesión no iniciada",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void registrar(String email,String pass){
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Log.i("SESION","usuario creado correctamente");
                    Toast.makeText(MainActivity.this,"Registrado correctamente",Toast.LENGTH_SHORT).show();
                    editTextEmail.setText("");
                    editTextPass.setText("");

                }else{
                    Log.i("SESION",task.getException().getMessage()+"");
                    Toast.makeText(MainActivity.this,"Error! No registrado",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mAuthListener != null){
            FirebaseAuth.getInstance().removeAuthStateListener(mAuthListener);
        }
    }
}

