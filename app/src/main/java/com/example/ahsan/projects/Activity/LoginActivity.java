package com.example.ahsan.projects.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ahsan.projects.Helper.Session;
import com.example.ahsan.projects.R;

import com.example.ahsan.projects.Webservices.Login;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText username;
    private EditText password;
    private Button btn_login;
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        btn_login = (Button) findViewById(R.id.login);

        btn_login.setOnClickListener(this);

        session = new Session(this);

        if(session.getLogin()){
            Intent intent = new Intent(this, ProjectList.class);
            startActivity(intent);
            finish();
        }

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch(id){
            case R.id.login:
                String usr = username.getText().toString().toLowerCase().trim();
                String pass = password.getText().toString().trim();

                Login login = new Login(usr,pass,this);
                login.execute(usr,pass);
                break;
        }
    }
}
