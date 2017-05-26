package com.example.ahsan.projects.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.ahsan.projects.Adapters.Project_listAdapter;
import com.example.ahsan.projects.AppConfig;
import com.example.ahsan.projects.AppController;
import com.example.ahsan.projects.Helper.Projects;
import com.example.ahsan.projects.Helper.RecyclerItemClickListener;
import com.example.ahsan.projects.Helper.Session;
import com.example.ahsan.projects.R;
import com.example.ahsan.projects.Webservices.ProjectsList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProjectList extends AppCompatActivity {


    private RecyclerView projects;
    private SwipeRefreshLayout srl;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_list);

        projects = (RecyclerView) findViewById(R.id.projects);
        srl = (SwipeRefreshLayout) findViewById(R.id.projectrefresh);
        actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("ProjectsList");
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        projects.setLayoutManager(mLayoutManager);
        projects.setItemAnimator(new DefaultItemAnimator());
        projects.addOnItemTouchListener(
                new RecyclerItemClickListener(this, projects ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        //
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );

        ProjectsList pl = new ProjectsList(this,projects);
        pl.execute();

    }



}
