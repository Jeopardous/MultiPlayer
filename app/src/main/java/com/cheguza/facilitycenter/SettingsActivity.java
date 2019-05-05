package com.cheguza.facilitycenter;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.cheguza.facilitycenter.DataBase.MyDataBaseHelper;

public class SettingsActivity extends AppCompatActivity {

    Button button;
    ProgressBar progressBar;
    MyDataBaseHelper myDataBaseHelper;
    String phoneNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        myDataBaseHelper=new MyDataBaseHelper(SettingsActivity.this);
        progressBar=findViewById(R.id.signOutProgressBar);
        progressBar.setVisibility(View.GONE);
        phoneNo=getIntent().getStringExtra("usrPhn");
        button=findViewById(R.id.signOut_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);

                AlertDialog.Builder builder=new AlertDialog.Builder(SettingsActivity.this);
                builder.setTitle("Sign Out");
                builder.setMessage("Are you sure????");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Integer deleteRow=myDataBaseHelper.deleteData(phoneNo);
                        Intent intent=new Intent(SettingsActivity.this,SelectUserActivity.class);
                        intent.putExtra("deleteUser","User");
                        startActivity(intent);

                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        progressBar.setVisibility(View.GONE);
                    }
                });
                builder.create();
                builder.show();
                progressBar.setVisibility(View.GONE);
            }

        });

    }
}
