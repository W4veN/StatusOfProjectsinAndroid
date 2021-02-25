package com.example.projects;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList project_id, project_type, project_name, project_description;
   // LinearLayout mainLayout;

    CustomAdapter(Activity activity, Context context, ArrayList project_id, ArrayList project_type, ArrayList project_name, ArrayList project_description){
        this.activity = activity;
        this.context = context;
        this.project_id = project_id;
        this.project_type = project_type;
        this.project_name = project_name;
        this.project_description = project_description;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.project_id_text.setText(String.valueOf(project_id.get(position)));
        holder.project_type_text.setText(String.valueOf(project_type.get(position)));
        holder.project_name_text.setText(String.valueOf(project_name.get(position)));
        holder.project_description_text.setText(String.valueOf(project_description.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(project_id.get(position)));
                intent.putExtra("type", String.valueOf(project_type.get(position)));
                intent.putExtra("name", String.valueOf(project_name.get(position)));
                intent.putExtra("description", String.valueOf(project_description.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return project_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public LinearLayout mainLayout;
        TextView project_id_text, project_type_text, project_name_text, project_description_text;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            project_id_text = itemView.findViewById(R.id.project_id_text);
            project_type_text = itemView.findViewById(R.id.project_type_text);
            project_name_text = itemView.findViewById(R.id.project_name_text);
            project_description_text = itemView.findViewById(R.id.project_description_text);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
