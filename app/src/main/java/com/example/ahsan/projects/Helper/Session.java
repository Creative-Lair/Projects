package com.example.ahsan.projects.Helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by AHSAN on 5/26/2017.
 */

public class Session {

    private static final String PREF_NAME = "Project";
    private static final int PRIVATE_MODE = 0;
    private static final String TAG = "Session";
    private static final String KEY_IS_LOGGEDIN = "login";
    private static final String KEY_NAME = "name";
    private static final String KEY_PIC = "pic";
    private static final String KEY_ID = "id";


    private Context _context;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    public Session(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setLogin(boolean b){
        editor.putBoolean(KEY_IS_LOGGEDIN, b);
        editor.commit();

        Log.d(TAG, "User login session modified!");
    }

    public void setName(String n){
        editor.putString(KEY_NAME , n);
        editor.commit();

        Log.d(TAG, "User Name modified!");
    }

    public void setPic(String p){
        editor.putString(KEY_PIC , p);
        editor.commit();

        Log.d(TAG, "User Pic modified!");

    }

    public void setId(int i){
        editor.putInt(KEY_ID,i);
        editor.commit();

        Log.d(TAG, "User Id modified!");

    }

    public String getName(){
        return pref.getString(KEY_NAME, "ahsan");
    }

    public boolean getLogin(){
        return pref.getBoolean(KEY_IS_LOGGEDIN, false);
    }

    public String getPic(){
        return pref.getString(KEY_PIC, "default.jpg");
    }

    public int getId(){
        return pref.getInt(KEY_ID, 1);
    }

    public User getUser(){
         String usr = pref.getString(KEY_NAME, "ahsan");
         int id = pref.getInt(KEY_ID, 1);
         String pic = pref.getString(KEY_PIC,"default.jpg");

        User user = new User(usr,id,pic);

        return user;
    }
}
