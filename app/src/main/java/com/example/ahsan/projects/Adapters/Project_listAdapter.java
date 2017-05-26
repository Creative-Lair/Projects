package com.example.ahsan.projects.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.util.LruCache;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ahsan.projects.Helper.Projects;
import com.example.ahsan.projects.Helper.Session;
import com.example.ahsan.projects.R;

import java.util.List;

/**
 * Created by AHSAN on 5/26/2017.
 */
public class Project_listAdapter extends RecyclerView.Adapter<Project_listAdapter.MyViewHolder> {

    private Context mContext;
    private List<Projects> project;
    private Session session;

    public Project_listAdapter(Context mContext, List<Projects> project) {
        this.mContext = mContext;
        this.project = project;
        session = new Session(mContext);

        System.out.println(project);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_project, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Projects projects = project.get(position);

        int id = projects.getAdmin_id();

        holder.name.setText(projects.getName());
        holder.name.setTag(projects.getId());

        Log.d("bind" , projects.getName());
        Log.d("bind" , "" + projects.getId());


        Bitmap icon = null;

        if(id == 1)
            icon = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.ahsan);
        if(id == 2)
            icon = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.hisham);
        if(id == 3)
            icon = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.fahad);

        holder.iv.setImageBitmap(icon);


    }

    @Override
    public int getItemCount() {
        return project.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ImageView iv;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            iv = (ImageView) view.findViewById(R.id.icon);

        }
    }



}
