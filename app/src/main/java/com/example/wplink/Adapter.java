package com.example.wplink;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private final ArrayList<String> data;
    private final LayoutInflater inflater;
    private Context context;

    Adapter(Context context, ArrayList<String> data) {
        this.inflater = LayoutInflater.from(context);
        this.data = data;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.lista, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int pos = position * 2;
        String name = data.get(pos);
        String link = data.get(pos+1);

        holder.textview1.setText(name);
        holder.btn1.setOnClickListener(v -> {
            this.context.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse(link)));
        });
    }

    @Override
    public int getItemCount() {
        return data.size() / 2;
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textview1;
        Button btn1;
        ViewHolder(View itemView) {
            super(itemView);
            textview1 = itemView.findViewById(R.id.textview1);
            btn1 = itemView.findViewById(R.id.btn1);
        }
    }


}
