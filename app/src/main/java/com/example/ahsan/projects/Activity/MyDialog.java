package com.example.ahsan.projects.Activity;
import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahsan.projects.R;
import com.example.ahsan.projects.Webservices.CreateProject;

import java.util.zip.Inflater;

public class MyDialog extends DialogFragment implements View.OnClickListener {


    private LayoutInflater inflater;
    private View view;
    private Button btn_create , btn_cancel;
    private EditText name;
    private Context context;

    public MyDialog(Context context) {
        super();
        this.context = context;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.popup_dialog , null);

        btn_cancel = (Button) view.findViewById(R.id.cancel);
        btn_create = (Button) view.findViewById(R.id.create);
        name = (EditText) view.findViewById(R.id.name);

        btn_create.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);

        return builder.create();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id){
            case R.id.cancel:
                    dismiss();
                break;

            case R.id.create:

                if(!name.getText().toString().trim().equals("")){
                    CreateProject cp = new CreateProject(name.getText().toString().trim(), context);
                    cp.execute();
                    dismiss();

                }else {
                    Toast.makeText(context , "Please give a name of your project", Toast.LENGTH_SHORT).show();
                }

                break;
        }


    }

}
