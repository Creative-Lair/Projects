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
import com.example.ahsan.projects.AppConfig;
import com.example.ahsan.projects.AppController;
import com.example.ahsan.projects.Helper.Projects;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by AHSAN on 5/26/2017.
 */

public class ProjectsList extends AsyncTask<String,String,ArrayList<Projects>> {

    private ProgressDialog progressDialog;
    private Context context;
    private ArrayList<Projects> project_list;
    private RecyclerView projects;

    private final static String TAG = "ProjectsList";

    public ProjectsList(Context context, RecyclerView projects) {
        this.context = context;
        project_list = new ArrayList<>();
        this.projects = projects;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading ProjectsList");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    protected ArrayList<Projects> doInBackground(String... params) {
        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.AppUrl + "project.php", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Login Response: " + response);
                progressDialog.hide();

                try {
                    JSONObject jObj1 = new JSONObject(response);
                    JSONArray jsonArray = jObj1.getJSONArray("response");

                    for(int i=0;i<jsonArray.length();i++) {
                        JSONObject jObj = jsonArray.getJSONObject(i);
                        int id = jObj.getInt("id");
                        int admin_id = jObj.getInt("admin_id");
                        String name = jObj.getString("name");


                        Projects project = new Projects(name,admin_id,id);


                        project_list.add(project);

                        System.out.println(project_list.size());

                    }

                    Project_listAdapter adapter = new Project_listAdapter(context, project_list);
                    projects.setAdapter(adapter);



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
                Map<String, String> params = new HashMap<String, String>();

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, "Login");
        return project_list;
    }

    @Override
    protected void onPostExecute(ArrayList<Projects> s) {
        super.onPostExecute(s);

        if (progressDialog.isShowing()) {
            progressDialog.hide();
        }


//            System.out.println(s.get(0).getAdmin_id());


    }

    public void showMessage(String str){
        Toast.makeText(context, str , Toast.LENGTH_LONG).show();
    }
}
