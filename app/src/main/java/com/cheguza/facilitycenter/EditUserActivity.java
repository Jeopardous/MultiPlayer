package com.cheguza.facilitycenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cheguza.facilitycenter.DataBase.MyDataBaseHelper;
import com.cheguza.facilitycenter.Home.HomeActivity;

public class EditUserActivity extends AppCompatActivity {

    MyDataBaseHelper myDataBaseHelper;
    EditText fname,lname,username;
    Button mSaveDetails;
    String PhoneNo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complete_profile_dialogbox);

        PhoneNo=getIntent().getStringExtra("usrPhn");

        myDataBaseHelper=new MyDataBaseHelper(EditUserActivity.this);
        fname=findViewById(R.id.first_name);
        lname=findViewById(R.id.last_name);
        username=findViewById(R.id.username);
        mSaveDetails=findViewById(R.id.save_user_details_btn);

        addData();
    }
    private void addData()
    {
        mSaveDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted =myDataBaseHelper.insertData(fname.getText().toString()
                        ,lname.getText().toString(),username.getText().toString(),PhoneNo);

                if(isInserted==true)
                {
                    Toast.makeText(getApplicationContext(),"Data Instered ",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(EditUserActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Data Not Instered ",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
