package com.example.oscar.serviciosbackend;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class BaseActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Alumno> lista;

    AdapterAlumnos adapterAlumnos;

    Button btnañadir;

    TextView txtusuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        btnañadir=findViewById(R.id.btnagregar);
        btnañadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),PruebaActivity.class);
                startActivity(intent);
            }
        });

        txtusuario=findViewById(R.id.txtemailusu);
        txtusuario.setText(this.getIntent().getStringExtra("Email"));

        recyclerView=findViewById(R.id.recycleralumno);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        lista = new ArrayList<>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        adapterAlumnos = new AdapterAlumnos(lista);
        recyclerView.setAdapter(adapterAlumnos);

        final DatabaseReference myRef= database.getReference(FirebaseReferencias.BASE_REFERENCE);
        myRef.child(FirebaseReferencias.ALUMNO_REFERENCE).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                try {
                    String vinombre = dataSnapshot.child("nombre").getValue().toString();
                    String vinocontrol = dataSnapshot.child("nocontrol").getValue().toString();
                    Alumno alumnos = new Alumno(vinombre, vinocontrol);
                    lista.add(alumnos);
                    Toast.makeText(BaseActivity.this,"Lista de Alumnos",Toast.LENGTH_SHORT).show();
                    adapterAlumnos.notifyDataSetChanged();

                }catch (NullPointerException e){}
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        /*database.getReference().getRoot().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //lista.removeAll(lista);
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Alumno alumno = snapshot.getValue(Alumno.class);
                    lista.add(alumno);
                }
                adapterAlumnos.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/

        /*mDatabase= FirebaseDatabase.getInstance().getReference();
        mDatabase.child("alumno").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                try {
                    String nombre = dataSnapshot.child("nombre").getValue().toString();
                    int nocontrol = Integer.parseInt(dataSnapshot.child("nocontrol").getValue().toString());
                    Alumno aux = new Alumno(nombre, nocontrol);
                    datos.add(aux);
                    adapter.notifyDataSetChanged();
                }catch(NullPointerException e){}
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/

    }
}
