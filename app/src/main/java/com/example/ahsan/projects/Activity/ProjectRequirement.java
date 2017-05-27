package com.example.ahsan.projects.Activity;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ahsan.projects.Adapters.RequirementListAdapter;
import com.example.ahsan.projects.Helper.RecyclerItemClickListener;
import com.example.ahsan.projects.Helper.Requirement;
import com.example.ahsan.projects.Helper.Session;
import com.example.ahsan.projects.R;
import com.example.ahsan.projects.Webservices.AddRequirement;
import com.example.ahsan.projects.Webservices.CreateProject;
import com.example.ahsan.projects.Webservices.RequirementList;

import java.util.ArrayList;

public class ProjectRequirement extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {

    private Session session;
    private RecyclerView requirements;
    private SwipeRefreshLayout srl;
    private ActionBar actionBar;
    private Button btn_add;
    private EditText editText;
    private ArrayList<Requirement> requirementList;
    private RequirementListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_requirement);
        requirementList = new ArrayList<>();


        btn_add = (Button) findViewById(R.id.add);
        btn_add.setOnClickListener(this);
        editText = (EditText) findViewById(R.id.requirement);
        session = new Session(this);
        requirements = (RecyclerView) findViewById(R.id.requirements);
        srl = (SwipeRefreshLayout) findViewById(R.id.requirementreferesh);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle(session.getProjectName());
        srl.setOnRefreshListener(this);
        adapter = new RequirementListAdapter(this,requirementList);
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

        requirements.setAdapter(adapter);

        if(id != -1){
            RequirementList rl = new RequirementList(this,requirements,adapter,requirementList);
            rl.execute();

        } else {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id){
            case android.R.id.home:
                finish();
                break;
        }



        return true;
    }

    @Override
    public void onRefresh() {
        RequirementList rl = new RequirementList(this,requirements,adapter,requirementList);
        rl.execute();

        srl.setRefreshing(false);
    }

    @Override
    public void onClick(View v) {
        int id  = v.getId();

        switch (id){
            case R.id.add:

                if(!editText.getText().toString().equals("")){

                    AddRequirement ar = new AddRequirement(editText.getText().toString(),this,requirements,adapter,requirementList);
                    ar.execute();

                    editText.setText("");



                } else{

                }

                break;
        }
    }
}
