package com.example.ahsan.projects.Adapters;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ahsan.projects.Helper.Projects;
import com.example.ahsan.projects.Helper.Requirement;
import com.example.ahsan.projects.Helper.Session;
import com.example.ahsan.projects.R;

import java.util.List;

/**
 * Created by AHSAN on 5/26/2017.
 */

public class RequirementListAdapter extends RecyclerView.Adapter<RequirementListAdapter.MyViewHolder> {

    private Context mContext;
    private List<Requirement> requirements;
    private Session session;

    LruCache<Integer,Bitmap> lruCache;

    public RequirementListAdapter(Context mContext, List<Requirement> requirements) {
        this.mContext = mContext;
        this.requirements = requirements;
        session = new Session(mContext);
        final int memClass = ((ActivityManager) mContext.getSystemService(
                Context.ACTIVITY_SERVICE)).getMemoryClass();

        int maxKb = memClass * 1024;
        int limitKb = maxKb / 8; // 1/8th of total ram
        lruCache = new LruCache<>(limitKb);

        Bitmap icon = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.ahsan);
        Bitmap icon2 = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.hisham);
        Bitmap icon3 = BitmapFactory.decodeResource(mContext.getResources(),R.drawable.fahad);

        lruCache.put(1,icon);
        lruCache.put(2,icon2);
        lruCache.put(3,icon3);



    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_requirement, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        Requirement requirement = requirements.get(position);

        int user_id = requirement.getUser_id();

        holder.note.setText(requirement.getNote());
        holder.note.setTag(requirement.getId());

        Bitmap icon = lruCache.get(user_id);

        holder.iv.setImageBitmap(icon);



    }

    @Override
    public int getItemCount() {
        return requirements.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView note;
        public ImageView iv;

        public MyViewHolder(View view) {
            super(view);
            note = (TextView) view.findViewById(R.id.note);
            iv = (ImageView) view.findViewById(R.id.icon);
        }
    }



}
