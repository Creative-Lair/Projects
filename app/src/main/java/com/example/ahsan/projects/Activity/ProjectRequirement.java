package com.example.ahsan.projects.Activity;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.ahsan.projects.Helper.RecyclerItemClickListener;
import com.example.ahsan.projects.Helper.Session;
import com.example.ahsan.projects.R;
import com.example.ahsan.projects.Webservices.RequirementList;

public class ProjectRequirement extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private Session session;
    private RecyclerView requirements;
    private SwipeRefreshLayout srl;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_requirement);

        session = new Session(this);
        requirements = (RecyclerView) findViewById(R.id.requirements);
        srl = (SwipeRefreshLayout) findViewById(R.id.requirementreferesh);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle(session.getProjectName());
        srl.setOnRefreshListener(this);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        requirements.setLayoutManager(mLayoutManager);
        requirements.setItemAnimator(new DefaultItemAnimator());
        requirements.addOnItemTouchListener(
                new RecyclerItemClickListener(this, requirements ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {




                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );

        int id = session.getProject();

        if(id != -1){
            RequirementList rl = new RequirementList(this,requirements);
            rl.execute();

        } else {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }


    }

    @Override
    public void onRefresh() {
        srl.setRefreshing(false);
    }
}
