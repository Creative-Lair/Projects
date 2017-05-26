package com.example.ahsan.projects.Webservices;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.ahsan.projects.Activity.ProjectList;
import com.example.ahsan.projects.AppConfig;
import com.example.ahsan.projects.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import com.example.ahsan.projects.Helper.Session;

public class Login extends AsyncTask<String,String,String> {

    final String TAG = "Login";
    private String usr;
    private String pass;
    private Context context;
    private Session session;

    public Login(String usr, String pass, Context context) {
        this.usr = usr;
        this.pass = pass;
        this.context = context;
        session = new Session(context);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
            StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.AppUrl + "index.php", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Login Response: " + response);

                try {
                    JSONObject jObj1 = new JSONObject(response);
                    JSONArray jsonArray = jObj1.getJSONArray("response");
                    JSONObject jObj = jsonArray.getJSONObject(0);

                    boolean error = jObj.getBoolean("error");


                    if (!error) {

                        int userid = jObj.getInt("userid");
                        String username = jObj.getString("name");
                        String pic = jObj.getString("pic");

                        session.setLogin(true);
                        session.setId(userid);
                        session.setName(username);
                        session.setPic(pic);

                        Intent intent = new Intent(context, ProjectList.class);
                        context.startActivity(intent);
                    }
                    else
                    {
                        String errorMsg = jObj.getString("error_msg");
                        showMessage(errorMsg);
                    }
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
            //    hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", usr);
                params.put("password", pass);
                params.put("login","true");

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
        Toast.makeText(context, str, Toast.LENGTH_LONG).show();
    }
}
