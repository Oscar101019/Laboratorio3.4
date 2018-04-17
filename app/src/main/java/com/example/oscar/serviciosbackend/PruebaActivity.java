package com.example.oscar.serviciosbackend;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PruebaActivity extends AppCompatActivity {

    Button btnañadir;
    EditText editaddnombre,editaddnocontorl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba);

        btnañadir=findViewById(R.id.buttonañadir);
        editaddnombre=findViewById(R.id.txtaddnombre);
        editaddnocontorl=findViewById(R.id.txtaddnocontrol);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef= database.getReference(FirebaseReferencias.BASE_REFERENCE);

        btnañadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Alumno alumno = new Alumno(editaddnombre.getText().toString(),editaddnocontorl.getText().toString());
                myRef.child(FirebaseReferencias.ALUMNO_REFERENCE).push().setValue(alumno);

                editaddnombre.setText(" ");
                editaddnocontorl.setText(" ");

                Toast.makeText(PruebaActivity.this,"Agregado correctamente",Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(),BaseActivity.class);
                startActivity(intent);
            }
        });


    }
}
