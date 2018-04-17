package com.example.oscar.serviciosbackend;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by oscar on 16/04/18.
 */

public class AdapterAlumnos extends RecyclerView.Adapter<AdapterAlumnos.AlumnoViewHolder> {

    List<Alumno> alumnos;

    public AdapterAlumnos(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    @Override
    public AlumnoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item,parent,false);
        AlumnoViewHolder alumnoViewHolder = new AlumnoViewHolder(v);
        return alumnoViewHolder;
    }

    @Override
    public void onBindViewHolder(AlumnoViewHolder holder, int position) {

        holder.textViewNombre.setText(alumnos.get(position).getNombre());
        holder.textViewNocontrol.setText(alumnos.get(position).getNocontrol());

    }

    @Override
    public int getItemCount() {
        return alumnos.size();
    }

    public class AlumnoViewHolder extends RecyclerView.ViewHolder{

        TextView textViewNombre,textViewNocontrol;

        public AlumnoViewHolder(View itemView) {
            super(itemView);
            textViewNombre=itemView.findViewById(R.id.txtnombre);
            textViewNocontrol=itemView.findViewById(R.id.txtnocontrol);
        }
    }
}
