package com.example.ahsan.projects.Webservices;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.ahsan.projects.Activity.ProjectList;
import com.example.ahsan.projects.Activity.ProjectRequirement;
import com.example.ahsan.projects.Adapters.RequirementListAdapter;
import com.example.ahsan.projects.AppConfig;
import com.example.ahsan.projects.AppController;
import com.example.ahsan.projects.Helper.Requirement;
import com.example.ahsan.projects.Helper.Session;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by AHSAN on 5/27/2017.
 */

public class AddRequirement extends AsyncTask<String,String,String> {

    private String name;
    private Session session;
    private Context context;
    private ArrayList<Requirement> requirements;
    private RequirementListAdapter adapter;
    private RecyclerView recyclerView;

    public AddRequirement(String name, Context context, RecyclerView recyclerView,RequirementListAdapter adapter, ArrayList<Requirement> requirements) {
        this.name = name;
        this.context =context;
        this.adapter = adapter;
        this.requirements = requirements;
        session = new Session(context);
        this.recyclerView = recyclerView;
    }

    @Override
    protected String doInBackground(String... params) {

        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.AppUrl + "addrequirement.php", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jObj1 = new JSONObject(response);
                    JSONArray jsonArray = jObj1.getJSONArray("response");
                    JSONObject jObj = jsonArray.getJSONObject(0);

                    int id = requirements.size();
                    int p_id = session.getProject();
                    int u_id = session.getId();
                    String note = name;

                    Requirement requirement = new Requirement(id,u_id,p_id,note);

                    requirements.add(requirement);
                    adapter.notifyDataSetChanged();
                    recyclerView.scrollToPosition(requirements.size()-1);
    //                RequirementList rl = new RequirementList(context,recyclerView);
    //                rl.execute();


                }
                catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                    showMessage( "Json error: " + e.getMessage());
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                showMessage(error.getMessage()+  " Something went wrong !!!");
                //    hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("requirement", name);
                params.put("user_id", "" + session.getId());
                params.put("project_id", "" + session.getProject());
                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, "Login");
        return null;
    }

    public void showMessage (String str){
        Toast.makeText(context, str, Toast.LENGTH_LONG).show();
    }


}
