package com.example.ahsan.projects.Webservices;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.ahsan.projects.Adapters.Project_listAdapter;
import com.example.ahsan.projects.Adapters.RequirementListAdapter;
import com.example.ahsan.projects.AppConfig;
import com.example.ahsan.projects.AppController;
import com.example.ahsan.projects.Helper.Projects;
import com.example.ahsan.projects.Helper.Requirement;
import com.example.ahsan.projects.Helper.Session;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by AHSAN on 5/26/2017.
 */

public class RequirementList extends AsyncTask<String, String, String> {

    private Context context;
    private RecyclerView requirementsList;
    private ArrayList<Requirement> requirements;
    private ProgressDialog progressDialog;
    private Session session;
    private final String TAG = "REQUIREMENT";

    public RequirementList(Context context, RecyclerView requirementsList) {
        this.context = context;
        this.requirementsList = requirementsList;
        requirements = new ArrayList<>();
        progressDialog = new ProgressDialog(context);
        session = new Session(context);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog.setMessage("Fetching requirements");
        progressDialog.setCancelable(false);
        progressDialog.show();

    }


    @Override
    protected String doInBackground(String... params) {
        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.AppUrl + "requirement.php", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Requirement Response: " + response);
                progressDialog.hide();

                try {
                    JSONObject jObj1 = new JSONObject(response);
                    JSONArray jsonArray = jObj1.getJSONArray("response");

                    for(int i=0;i<jsonArray.length();i++) {
                        JSONObject jObj = jsonArray.getJSONObject(i);
                        int id = jObj.getInt("id");
                        int user_id = jObj.getInt("user_id");
                        int project_id = jObj.getInt("project_id");
                        String note = jObj.getString("note");


                        Requirement requirement = new Requirement(id,user_id,project_id,note);


                        requirements.add(requirement);

                        System.out.println(requirements.size());

                    }

                    if(progressDialog.isShowing())
                        progressDialog.hide();


                    RequirementListAdapter adapter = new RequirementListAdapter(context, requirements);
                    requirementsList.setAdapter(adapter);



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
                Log.e(TAG, "Login Error: " + error.getMessage());
                showMessage(error.getMessage()+  " Something went wrong !!!");
                progressDialog.hide();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<>();

                params.put("id", "" + session.getProject());
                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, "Login");


        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    public void showMessage(String str){
        Toast.makeText(context, str , Toast.LENGTH_LONG).show();
    }

}

